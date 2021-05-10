package tr.com.app.scheduler.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import tr.com.app.scheduler.BaseTest;
import tr.com.app.scheduler.controller.PresentationType;
import tr.com.app.scheduler.model.Presentation;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

public class ScheduleServiceImplTest extends BaseTest {

    @InjectMocks
    private ScheduleServiceImpl scheduleService;

    @Mock
    private PresentationService presentationService;

    @Mock
    private ConferenceScheduleBuilderService conferenceScheduleBuilderService;


    @Test
    public void createSchedule_WhenCalled_RunSubProcessesStepByStep(){
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
        when(conferenceScheduleBuilderService.createScheduleStyle()).thenReturn(conferenceScheduleBuilderService);
        when(conferenceScheduleBuilderService.createRoomStyle()).thenReturn(conferenceScheduleBuilderService);
        when(conferenceScheduleBuilderService.createTimeStyle()).thenReturn(conferenceScheduleBuilderService);

//        doAnswer(new Answer() {
//            public Object answer(InvocationOnMock invocation) {
//                when(conferenceScheduleBuilderService.isFinish()).thenReturn(true);
//                return null;
//            }})
//                .when(conferenceScheduleBuilderService).endEvent();

        scheduleService.createSchedule();
    }


}
