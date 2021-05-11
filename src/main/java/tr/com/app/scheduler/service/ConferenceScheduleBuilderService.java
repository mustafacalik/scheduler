package tr.com.app.scheduler.service;

import tr.com.app.scheduler.controller.dto.ConferenceSchedule;
import tr.com.app.scheduler.model.Presentation;

import java.util.List;

/**
 * The interface Conference schedule builder service.
 */
public interface ConferenceScheduleBuilderService {
    /**
     * Is finish boolean.
     *
     * @return the boolean
     */
    boolean isFinish();

    /**
     * Init conference schedule builder service.
     *
     * @param presentationList the presentation list
     * @return the conference schedule builder service
     */
    ConferenceScheduleBuilderService init(List<Presentation> presentationList);

    /**
     * Start event conference schedule builder service.
     *
     * @return the conference schedule builder service
     */
    ConferenceScheduleBuilderService startEvent();

    /**
     * End event conference schedule builder service.
     *
     * @return the conference schedule builder service
     */
    ConferenceScheduleBuilderService endEvent();

    /**
     * Start networking conference schedule builder service.
     *
     * @return the conference schedule builder service
     */
    ConferenceScheduleBuilderService startNetworking();

    /**
     * Start lunch conference schedule builder service.
     *
     * @return the conference schedule builder service
     */
    ConferenceScheduleBuilderService startLunch();

    /**
     * End lunch conference schedule builder service.
     *
     * @return the conference schedule builder service
     */
    ConferenceScheduleBuilderService endLunch();

    /**
     * Create events from start to lunch conference schedule builder service.
     *
     * @return the conference schedule builder service
     */
    ConferenceScheduleBuilderService createEventsFromStartToLunch();

    /**
     * Create events from lunch to network conference schedule builder service.
     *
     * @return the conference schedule builder service
     */
    ConferenceScheduleBuilderService createEventsFromLunchToNetwork();

    /**
     * Create events from network to end conference schedule builder service.
     *
     * @return the conference schedule builder service
     */
    ConferenceScheduleBuilderService createEventsFromNetworkToEnd();

    /**
     * Build conference schedule.
     *
     * @return the conference schedule
     */
    ConferenceSchedule build();
}
