package tr.com.app.scheduler.service;

import tr.com.app.scheduler.model.Presentation;

import java.util.List;

public interface ScheduleStrategy {

    List<Presentation> getSubListByMaximumTotalWeight(List<Presentation> list, int totalWeight);

}
