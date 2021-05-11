package tr.com.app.scheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.app.scheduler.controller.dto.ConferenceSchedule;

/**
 * The type Schedule service.
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

    private PresentationService presentationService;
    private ConferenceScheduleBuilderService conferenceScheduleBuilderService;

    /**
     * Instantiates a new Schedule service.
     *
     * @param presentationService              the presentation service
     * @param conferenceScheduleBuilderService the conference schedule builder service
     */
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
        return conferenceScheduleBuilderService.build();
    }
}
