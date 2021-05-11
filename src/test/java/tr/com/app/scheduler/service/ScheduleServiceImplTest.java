package tr.com.app.scheduler.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tr.com.app.scheduler.controller.PresentationType;
import tr.com.app.scheduler.model.Presentation;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ScheduleServiceImplTest{

    @InjectMocks
    private ScheduleServiceImpl scheduleService;

    @Mock
    private PresentationService presentationService;

    @Mock
    private ConferenceScheduleBuilderService conferenceScheduleBuilderService;


    @Test
    void createSchedule_WhenCalled_RunSubProcessesStepByStep(){
        List<Presentation> presentationList = new ArrayList<>();
        Presentation presentation = new Presentation();
        presentation.setName("abc");
        presentation.setType(PresentationType.NORMAL);
        presentation.setTimeAsMinute(10);
        presentationList.add(presentation);

        when(presentationService.getAllPresentation()).thenReturn(presentationList);
        when(conferenceScheduleBuilderService.startEvent()).thenReturn(conferenceScheduleBuilderService);
        when(conferenceScheduleBuilderService.startNetworking()).thenReturn(conferenceScheduleBuilderService);
        when(conferenceScheduleBuilderService.startLunch()).thenReturn(conferenceScheduleBuilderService);
        when(conferenceScheduleBuilderService.endLunch()).thenReturn(conferenceScheduleBuilderService);
        when(conferenceScheduleBuilderService.createEventsFromStartToLunch()).thenReturn(conferenceScheduleBuilderService);
        when(conferenceScheduleBuilderService.createEventsFromLunchToNetwork()).thenReturn(conferenceScheduleBuilderService);
        when(conferenceScheduleBuilderService.createEventsFromNetworkToEnd()).thenReturn(conferenceScheduleBuilderService);
        doAnswer(invocation -> {
            when(conferenceScheduleBuilderService.isFinish()).thenReturn(true);
            return null;
        }).when(conferenceScheduleBuilderService).endEvent();

        scheduleService.createSchedule();

        verify(conferenceScheduleBuilderService, times(1)).startEvent();
        verify(conferenceScheduleBuilderService, times(1)).createEventsFromStartToLunch();
        verify(conferenceScheduleBuilderService, times(1)).startLunch();
        verify(conferenceScheduleBuilderService, times(1)).endLunch();
        verify(conferenceScheduleBuilderService, times(1)).createEventsFromLunchToNetwork();
        verify(conferenceScheduleBuilderService, times(1)).startNetworking();
        verify(conferenceScheduleBuilderService, times(1)).createEventsFromNetworkToEnd();
        verify(conferenceScheduleBuilderService, times(1)).endEvent();
    }


}
