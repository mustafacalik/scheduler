package tr.com.app.scheduler.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tr.com.app.scheduler.model.Presentation;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class KnapsackScheduleStrategyImplTest {

    private KnapsackScheduleStrategyImpl knapsackScheduleStrategy = new KnapsackScheduleStrategyImpl();

    @Test
    void getSubListByMaximumTotalWeight_WhenGivenList_ReturnSubListAccordingToMaxTotalWeightParameter(){
        Presentation presentation1 = new Presentation();
        presentation1.setTimeAsMinute(10);

        Presentation presentation2 = new Presentation();
        presentation2.setTimeAsMinute(20);

        Presentation presentation3 = new Presentation();
        presentation3.setTimeAsMinute(30);

        List<Presentation> subPresentationList = knapsackScheduleStrategy.getSubListByMaximumTotalWeight(Arrays.asList(presentation1, presentation2, presentation3), 15);

        assertEquals(1, subPresentationList.size());
        assertEquals(10, subPresentationList.get(0).getTimeAsMinute());
    }

}
