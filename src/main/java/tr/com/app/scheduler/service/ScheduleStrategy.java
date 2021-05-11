package tr.com.app.scheduler.service;

import tr.com.app.scheduler.model.Presentation;

import java.util.List;

/**
 * The interface Schedule strategy.
 */
public interface ScheduleStrategy {

    /**
     * Gets sub list by maximum total weight.
     *
     * @param list        the list
     * @param totalWeight the total weight
     * @return the sub list by maximum total weight
     */
    List<Presentation> getSubListByMaximumTotalWeight(List<Presentation> list, int totalWeight);

}
