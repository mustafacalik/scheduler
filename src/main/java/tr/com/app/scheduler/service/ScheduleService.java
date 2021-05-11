package tr.com.app.scheduler.service;

import tr.com.app.scheduler.controller.dto.ConferenceSchedule;

/**
 * The interface Schedule service.
 */
public interface ScheduleService {

    /**
     * Create schedule conference schedule.
     *
     * @return the conference schedule
     */
    ConferenceSchedule createSchedule();

}
