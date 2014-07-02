package io.vertigo.studio.plugins.reporting.task.metrics.performance;

import io.vertigo.dynamo.task.metamodel.TaskAttribute;
import io.vertigo.dynamo.task.metamodel.TaskDefinition;
import io.vertigo.dynamo.task.model.Task;
import io.vertigo.dynamo.task.model.TaskEngine;
import io.vertigo.dynamo.task.model.TaskResult;
import io.vertigo.dynamo.work.WorkItem;
import io.vertigo.dynamo.work.WorkManager;
import io.vertigo.dynamox.task.TaskEngineSelect;
import io.vertigo.kernel.lang.Assertion;
import io.vertigo.kernel.util.ClassUtil;
import io.vertigo.studio.reporting.MetricEngine;

/**
 * Plugin de calcul du temps d'exécution d'une requête.
 * 
 * @author tchassagnette
 */
public final class PerformanceMetricEngine implements MetricEngine<TaskDefinition, PerformanceMetric> {
	private final WorkManager workManager;

	/**
	 * Constructeur apr défaut.
	 * @param workManager Manager des works
	 */
	public PerformanceMetricEngine(final WorkManager workManager) {
		Assertion.checkNotNull(workManager);
		//---------------------------------------------------------------------
		this.workManager = workManager;
	}

	/** {@inheritDoc} */
	public PerformanceMetric execute(final TaskDefinition taskDefinition) {
		Assertion.checkNotNull(taskDefinition);
		//---------------------------------------------------------------------
		try {
			return doExecute(taskDefinition);
		} catch (final Throwable e) {
			//throw new RiException("Erreur du plugin perfs", e);
			return new PerformanceMetric(e);
		}

	}

	private PerformanceMetric doExecute(final TaskDefinition taskDefinition) {
		//System.out.println(">>>>" + currentTask.getEngineClass().getCanonicalName());
		if (TaskEngineSelect.class.isAssignableFrom(getTaskEngineClass(taskDefinition)) && !hasNotNullOutParams(taskDefinition)) {
			//	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>" + currentTask.getEngineClass().getCanonicalName());
			final TaskPopulator taskPopulator = new TaskPopulator(taskDefinition);
			final Task task = taskPopulator.populateTask();
			final long startTime = System.currentTimeMillis();
			WorkItem<TaskResult, Task> workItem = new WorkItem<TaskResult, Task>(task, taskDefinition.getTaskEngineProvider()); 
			workManager.process(workItem);
			//on n'utilise pas le resultat
			final long endTime = System.currentTimeMillis();
			final long executionTime = endTime - startTime;
			return new PerformanceMetric(executionTime);
		}
		//Le test n'a pas de sens. 
		return new PerformanceMetric();
	}

	private Class<? extends TaskEngine> getTaskEngineClass(final TaskDefinition taskDefinition) {
		return (Class<? extends TaskEngine>) ClassUtil.classForName(taskDefinition.getTaskEngineProvider().getName());
	}

	private static boolean hasNotNullOutParams(final TaskDefinition taskDefinition) {
		for (final TaskAttribute attribute : taskDefinition.getAttributes()) {
			if (!attribute.isIn() && attribute.isNotNull()) {
				return true;
			}
		}
		return false;
	}
}
