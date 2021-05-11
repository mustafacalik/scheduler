package tr.com.app.scheduler.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tr.com.app.scheduler.controller.ParameterType;
import tr.com.app.scheduler.controller.PresentationType;
import tr.com.app.scheduler.controller.dto.ConferenceSchedule;
import tr.com.app.scheduler.model.Presentation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ConferenceScheduleBuilderServiceImplTest {

    @InjectMocks
    private ConferenceScheduleBuilderServiceImpl conferenceScheduleBuilderService;

    @Mock
    private ParameterService parameterService;

    @Mock
    private ScheduleStrategy scheduleStrategy;

    @BeforeEach
    void testInit() {
        when(parameterService.getParameter(ParameterType.EVENT_START)).thenReturn("09:00");
        when(parameterService.getParameter(ParameterType.EVENT_END)).thenReturn("17:00");
        when(parameterService.getParameter(ParameterType.LUNCH_START)).thenReturn("12:00");
        when(parameterService.getParameter(ParameterType.LUNCH_END)).thenReturn("13:00");
        when(parameterService.getParameter(ParameterType.NETWORK_EVENT_START)).thenReturn("16:00");
        when(parameterService.getParameter(ParameterType.NETWORK_EVENT_END)).thenReturn("17:00");
    }


    @Test
    void build_WhenCalled_NewConferenceObjectIsCreated() {
        ConferenceSchedule conferenceSchedule = conferenceScheduleBuilderService.build();

        assertNotNull(conferenceSchedule);
    }

    @Test
    void createEventsFromNetworkToEnd_WhenCalled_PrepareSubListWhichTimeIsBetweenNetrokToEnd(){
        List<Presentation> presentationList = new ArrayList<>();
        Presentation presentation1 = new Presentation();
        presentation1.setTimeAsMinute(10);
        presentation1.setType(PresentationType.NORMAL);
        Presentation presentation2 = new Presentation();
        presentation2.setType(PresentationType.NETWORKING);
        presentation2.setTimeAsMinute(20);
        Presentation presentation3 = new Presentation();
        presentation3.setType(PresentationType.NORMAL);
        presentation3.setTimeAsMinute(20);
        presentationList.add(presentation1);
        presentationList.add(presentation2);
        presentationList.add(presentation3);

        conferenceScheduleBuilderService.init(presentationList);
        conferenceScheduleBuilderService.createEventsFromNetworkToEnd();

        verify(scheduleStrategy).getSubListByMaximumTotalWeight(Arrays.asList(presentation1), 60);
    }

    @Test
    void createEventsFromLunchToNetwork_WhenCalled_PrepareSubListWhichTimeIsBetweenLunchToNetwork(){
        List<Presentation> presentationList = new ArrayList<>();
        Presentation presentation1 = new Presentation();
        presentation1.setTimeAsMinute(10);
        presentation1.setType(PresentationType.NORMAL);
        Presentation presentation2 = new Presentation();
        presentation2.setType(PresentationType.NETWORKING);
        presentation2.setTimeAsMinute(20);
        Presentation presentation3 = new Presentation();
        presentation3.setType(PresentationType.NORMAL);
        presentation3.setTimeAsMinute(20);
        presentationList.add(presentation1);
        presentationList.add(presentation2);
        presentationList.add(presentation3);

        conferenceScheduleBuilderService.init(presentationList);
        conferenceScheduleBuilderService.createEventsFromLunchToNetwork();

        verify(scheduleStrategy).getSubListByMaximumTotalWeight(Arrays.asList(presentation1), 180);
    }

    @Test
    void createEventsFromStartToLunch_WhenCalled_PrepareSubListWhichTimeIsBetweenStartToLunch(){
        List<Presentation> presentationList = new ArrayList<>();
        Presentation presentation1 = new Presentation();
        presentation1.setTimeAsMinute(10);
        presentation1.setType(PresentationType.NORMAL);
        Presentation presentation2 = new Presentation();
        presentation2.setType(PresentationType.NETWORKING);
        presentation2.setTimeAsMinute(20);
        Presentation presentation3 = new Presentation();
        presentation3.setType(PresentationType.NORMAL);
        presentation3.setTimeAsMinute(20);
        presentationList.add(presentation1);
        presentationList.add(presentation2);
        presentationList.add(presentation3);

        conferenceScheduleBuilderService.init(presentationList);
        conferenceScheduleBuilderService.createEventsFromStartToLunch();

        verify(scheduleStrategy).getSubListByMaximumTotalWeight(Arrays.asList(presentation2), 180);
    }

}
