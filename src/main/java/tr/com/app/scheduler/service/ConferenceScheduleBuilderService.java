package tr.com.app.scheduler.service;

import tr.com.app.scheduler.controller.dto.ConferenceSchedule;
import tr.com.app.scheduler.model.Presentation;

import java.util.List;

public interface ConferenceScheduleBuilderService {
    boolean isFinish();

    ConferenceScheduleBuilderService init(List<Presentation> presentationList);

    ConferenceScheduleBuilderService startEvent();

    ConferenceScheduleBuilderService endEvent();

    ConferenceScheduleBuilderService startNetworking();

    ConferenceScheduleBuilderService startLunch();

    ConferenceScheduleBuilderService endLunch();

    ConferenceScheduleBuilderService createEventsFromStartToLunch();

    ConferenceScheduleBuilderService createEventsFromLunchToNetwork();

    ConferenceScheduleBuilderService createEventsFromNetworkToEnd();

    ConferenceScheduleBuilderService createScheduleStyle();

    ConferenceScheduleBuilderService createRoomStyle();

    ConferenceScheduleBuilderService createTimeStyle();

    ConferenceSchedule build();
}
