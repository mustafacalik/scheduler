package tr.com.app.scheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.app.scheduler.controller.dto.ConferenceSchedule;

@Service
public class ScheduleServiceImpl implements tr.com.app.scheduler.service.ScheduleService {

    private PresentationService presentationService;
    private ConferenceScheduleBuilderService conferenceScheduleBuilderService;

    @Autowired
    public ScheduleServiceImpl(PresentationService presentationService, ConferenceScheduleBuilderService conferenceScheduleBuilderService) {
        this.presentationService = presentationService;
        this.conferenceScheduleBuilderService = conferenceScheduleBuilderService;
    }

    @Override
    public ConferenceSchedule createSchedule() {
        conferenceScheduleBuilderService.init(presentationService.getAllPresentation());
        while (!conferenceScheduleBuilderService.isFinish()) {
            conferenceScheduleBuilderService
                    .startEvent()
                    .createEventsFromStartToLunch()
                    .startLunch()
                    .endLunch()
                    .createEventsFromLunchToNetwork()
                    .startNetworking()
                    .createEventsFromNetworkToEnd()
                    .endEvent();
        }
        return conferenceScheduleBuilderService.createScheduleStyle().createRoomStyle().createTimeStyle().build();
    }
}
