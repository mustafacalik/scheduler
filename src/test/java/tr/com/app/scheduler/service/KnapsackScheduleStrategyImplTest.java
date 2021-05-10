package tr.com.app.scheduler.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tr.com.app.scheduler.model.Presentation;

import java.util.Arrays;
import java.util.List;

public class KnapsackScheduleStrategyImplTest {

    private KnapsackScheduleStrategyImpl knapsackScheduleStrategy = new KnapsackScheduleStrategyImpl();

    @Test
    public void getSubListByMaximumTotalWeight_WhenGivenList_ReturnSubListAccordingToMaxTotalWeightParameter(){
        Presentation presentation1 = new Presentation();
        presentation1.setTimeAsMinute(10);

        Presentation presentation2 = new Presentation();
        presentation2.setTimeAsMinute(20);

        Presentation presentation3 = new Presentation();
        presentation3.setTimeAsMinute(30);

        List<Presentation> subPresentationList = knapsackScheduleStrategy.getSubListByMaximumTotalWeight(Arrays.asList(presentation1, presentation2, presentation3), 15);

        Assertions.assertEquals(1, subPresentationList.size());
        Assertions.assertEquals(10, subPresentationList.get(0).getTimeAsMinute());
    }

}
