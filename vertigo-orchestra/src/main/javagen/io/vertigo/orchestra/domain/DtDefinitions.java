package io.vertigo.orchestra.domain;

import java.util.Arrays;
import java.util.Iterator;

import io.vertigo.dynamo.domain.metamodel.DtFieldName;
import io.vertigo.lang.Generated;

/**
 * This class is automatically generated.
 * DO NOT EDIT THIS FILE DIRECTLY.
 */
@Generated
public final class DtDefinitions implements Iterable<Class<?>> {

	/**
	 * Enumération des DtDefinitions.
	 */
	public enum Definitions {
		/** Objet de données OJobCron. */
		OJobCron(io.vertigo.orchestra.domain.schedule.OJobCron.class),
		/** Objet de données OJobEvent. */
		OJobEvent(io.vertigo.orchestra.domain.event.OJobEvent.class),
		/** Objet de données OJobExec. */
		OJobExec(io.vertigo.orchestra.domain.run.OJobExec.class),
		/** Objet de données OJobLog. */
		OJobLog(io.vertigo.orchestra.domain.event.OJobLog.class),
		/** Objet de données OJobModel. */
		OJobModel(io.vertigo.orchestra.domain.model.OJobModel.class),
		/** Objet de données OJobRun. */
		OJobRun(io.vertigo.orchestra.domain.run.OJobRun.class),
		/** Objet de données OJobSchedule. */
		OJobSchedule(io.vertigo.orchestra.domain.schedule.OJobSchedule.class)		;

		private final Class<?> clazz;

		private Definitions(final Class<?> clazz) {
			this.clazz = clazz;
		}

		/** 
		 * Classe associée.
		 * @return Class d'implémentation de l'objet 
		 */
		public Class<?> getDtClass() {
			return clazz;
		}
	}

	/**
	 * Enumération des champs de OJobCron.
	 */
	public enum OJobCronFields implements DtFieldName<io.vertigo.orchestra.domain.schedule.OJobCron> {
		/** Propriété 'id'. */
		JCR_ID,
		/** Propriété 'cron expression'. */
		CRON_EXPRESSION,
		/** Propriété 'init params as JSON'. */
		PARAMS,
		/** Propriété 'JobModel'. */
		JMO_ID	}

	/**
	 * Enumération des champs de OJobEvent.
	 */
	public enum OJobEventFields implements DtFieldName<io.vertigo.orchestra.domain.event.OJobEvent> {
		/** Propriété 'Id d'une trace d'execution d'un job'. */
		JEVT_ID,
		/** Propriété 'Status général d'execution'. */
		JOB_NAME,
		/** Propriété 'Status général d'execution'. */
		STATUS,
		/** Propriété 'Code d'erreur fonctionel de l'execution'. */
		REASON,
		/** Propriété 'Date de début d'execution'. */
		START_DATE,
		/** Propriété 'Date de fin d'execution'. */
		END_DATE,
		/** Propriété 'Implémentation effective de l'execution'. */
		CLASS_ENGINE,
		/** Propriété 'Workspace d'entrée de l'execution'. */
		WORKSPACE_IN,
		/** Propriété 'Workspace de sortie de l'execution'. */
		WORKSPACE_OUT,
		/** Propriété 'Id du noeud'. */
		NOD_ID	}

	/**
	 * Enumération des champs de OJobExec.
	 */
	public enum OJobExecFields implements DtFieldName<io.vertigo.orchestra.domain.run.OJobExec> {
		/** Propriété 'Id'. */
		JOB_ID,
		/** Propriété 'Exec UUID'. */
		JOB_EXEC_UUID,
		/** Propriété 'Start exec date'. */
		START_EXEC_DATE,
		/** Propriété 'Max date Max execution (start + timeout)'. */
		MAX_EXEC_DATE,
		/** Propriété 'Job Name'. */
		JOB_NAME,
		/** Propriété 'Node Id'. */
		NODE_ID	}

	/**
	 * Enumération des champs de OJobLog.
	 */
	public enum OJobLogFields implements DtFieldName<io.vertigo.orchestra.domain.event.OJobLog> {
		/** Propriété 'Id d'une trace d'execution d'un job'. */
		JLO_ID,
		/** Propriété 'Date de la trace'. */
		DATE_TRACE,
		/** Propriété 'Niveau de la trace'. */
		LEVEL,
		/** Propriété 'Type de trace'. */
		TYPE_EXEC_CD,
		/** Propriété 'Message'. */
		MESSAGE,
		/** Propriété 'Paramètre'. */
		PARAMETRE,
		/** Propriété 'Stacktrace d'erreur'. */
		ERREUR,
		/** Propriété 'JobExecution'. */
		PRO_ID	}

	/**
	 * Enumération des champs de OJobModel.
	 */
	public enum OJobModelFields implements DtFieldName<io.vertigo.orchestra.domain.model.OJobModel> {
		/** Propriété 'Id'. */
		JMO_ID,
		/** Propriété 'Name'. */
		JOB_NAME,
		/** Propriété 'Description'. */
		DESC,
		/** Propriété 'Class name of the Job'. */
		JOB_ENGINE_CLASS_NAME,
		/** Propriété 'Max retry limit'. */
		MAX_RETRY,
		/** Propriété 'Max delay in seconds of a run from its scheduled date'. */
		RUN_MAX_DELAY,
		/** Propriété 'Timeout in seconds of a single execution'. */
		EXEC_TIMEOUT,
		/** Propriété 'Creation date'. */
		CREATION_DATE,
		/** Propriété 'Active/Inactive'. */
		ACTIVE	}

	/**
	 * Enumération des champs de OJobRun.
	 */
	public enum OJobRunFields implements DtFieldName<io.vertigo.orchestra.domain.run.OJobRun> {
		/** Propriété 'Id'. */
		JOB_ID,
		/** Propriété 'Exec UUID'. */
		JOB_EXEC_UUID,
		/** Propriété 'Exec status'. */
		STATUS,
		/** Propriété 'Current try'. */
		CURRENT_TRY,
		/** Propriété 'Max date of the run'. */
		MAX_DATE,
		/** Propriété 'Max retry'. */
		MAX_RETRY	}

	/**
	 * Enumération des champs de OJobSchedule.
	 */
	public enum OJobScheduleFields implements DtFieldName<io.vertigo.orchestra.domain.schedule.OJobSchedule> {
		/** Propriété 'id'. */
		JSC_ID,
		/** Propriété 'schedule date'. */
		SCHEDULE_DATE,
		/** Propriété 'init params as JSON'. */
		PARAMS,
		/** Propriété 'JobModel'. */
		JMO_ID	}

	/** {@inheritDoc} */
	@Override
	public Iterator<Class<?>> iterator() {
		return new Iterator<Class<?>>() {
			private Iterator<Definitions> it = Arrays.asList(Definitions.values()).iterator();

			/** {@inheritDoc} */
			@Override
			public boolean hasNext() {
				return it.hasNext();
			}

			/** {@inheritDoc} */
			@Override
			public Class<?> next() {
				return it.next().getDtClass();
			}
		};
	}
}
