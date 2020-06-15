/**
 * vertigo - simple java starter
 *
 * Copyright (C) 2013-2019, vertigo-io, KleeGroup, direction.technique@kleegroup.com (http://www.kleegroup.com)
 * KleeGroup, Centre d'affaire la Boursidiere - BP 159 - 92357 Le Plessis Robinson Cedex - France
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.vertigo.orchestra.plugins.services.log.db;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Optional;

import javax.inject.Inject;

import org.eclipse.jetty.http.MimeTypes;

import io.vertigo.commons.transaction.Transactional;
import io.vertigo.core.lang.Assertion;
import io.vertigo.core.param.ParamManager;
import io.vertigo.datastore.filestore.FileManager;
import io.vertigo.datastore.filestore.model.InputStreamBuilder;
import io.vertigo.datastore.filestore.model.VFile;
import io.vertigo.orchestra.dao.execution.OActivityLogDAO;
import io.vertigo.orchestra.domain.execution.OActivityLog;
import io.vertigo.orchestra.impl.services.ProcessLoggerPlugin;

/**
 * Récupération des logs en BDD.
 * @author mlaroche
 *
 */
@Transactional
public class DbProcessLoggerPlugin implements ProcessLoggerPlugin {

	private static final String ROOT_DIRECTORY = "orchestra.root.directory";
	private static final String TECHNICAL_LOG_PREFIX = "technicalLog_";
	private static final String TECHNICAL_LOG_EXTENSION = ".log";

	@Inject
	private OActivityLogDAO activityLogDAO;
	@Inject
	private ParamManager paramManager;
	@Inject
	private FileManager fileManager;

	/** {@inheritDoc} */
	@Override
	public Optional<VFile> getLogFileForProcess(final Long processExecutionId) {
		Assertion.check().notNull(processExecutionId);
		// ---
		final Optional<OActivityLog> activityLog = activityLogDAO.getLogByPreId(processExecutionId);
		return getLogFileFromActivityLog(activityLog);
	}

	/** {@inheritDoc} */
	@Override
	public Optional<VFile> getActivityAttachment(final Long actityExecutionId) {
		Assertion.check().notNull(actityExecutionId);
		// ---
		final Optional<OActivityLog> activityLog = activityLogDAO.getActivityLogByAceId(actityExecutionId);
		return getLogFileFromActivityLog(activityLog);
	}

	/** {@inheritDoc} */
	@Override
	public Optional<VFile> getActivityLogFile(final Long actityExecutionId) {
		Assertion.check().notNull(actityExecutionId);
		// ---

		final Optional<OActivityLog> activityLog = activityLogDAO.getActivityLogByAceId(actityExecutionId);
		if (activityLog.isPresent()) {
			final byte[] stringByteArray = activityLog.get().getLog().getBytes(StandardCharsets.UTF_8);

			final InputStreamBuilder inputStreamBuilder = () -> new ByteArrayInputStream(stringByteArray);

			final String fileName = TECHNICAL_LOG_PREFIX + actityExecutionId + TECHNICAL_LOG_EXTENSION;
			final VFile file = fileManager.createFile(fileName, MimeTypes.getDefaultMimeByExtension(fileName), Instant.now(), stringByteArray.length, inputStreamBuilder);

			return Optional.<VFile> of(file);
		}
		return Optional.<VFile> empty();
	}

	private Optional<VFile> getLogFileFromActivityLog(final Optional<OActivityLog> activityLog) {
		Assertion.check().notNull(activityLog);
		// ---
		if (activityLog.isPresent()) {
			final File file = new File(paramManager.getParam(ROOT_DIRECTORY).getValueAsString() + activityLog.get().getAttachment());
			if (file.exists()) {
				return Optional.of(fileManager.createFile(file));
			}
			throw new IllegalArgumentException("Log File" + file.getAbsolutePath() + " not found");
		}
		return Optional.empty();
	}
}
