package io.vertigo.orchestra.dao.definition;

import javax.inject.Inject;

import io.vertigo.dynamo.impl.store.util.DAO;
import io.vertigo.dynamo.store.StoreManager;
import io.vertigo.dynamo.store.StoreServices;
import io.vertigo.dynamo.task.TaskManager;
import io.vertigo.orchestra.domain.definition.OJobModel;
import io.vertigo.lang.Generated;

/**
 * This class is automatically generated.
 * DO NOT EDIT THIS FILE DIRECTLY.
 */
 @Generated
public final class OJobModelDAO extends DAO<OJobModel, java.lang.Long> implements StoreServices {

	/**
	 * Contructeur.
	 * @param storeManager Manager de persistance
	 * @param taskManager Manager de Task
	 */
	@Inject
	public OJobModelDAO(final StoreManager storeManager, final TaskManager taskManager) {
		super(OJobModel.class, storeManager, taskManager);
	}

}
