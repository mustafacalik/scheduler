package tr.com.app.scheduler.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import tr.com.app.scheduler.controller.ParameterType;
import tr.com.app.scheduler.controller.PresentationType;
import tr.com.app.scheduler.controller.dto.ConferenceSchedule;
import tr.com.app.scheduler.controller.dto.PresentationDto;
import tr.com.app.scheduler.controller.dto.PresentationTime;
import tr.com.app.scheduler.model.Presentation;
import tr.com.app.scheduler.util.StringHelper;
import tr.com.app.scheduler.util.TimeHelper;
import tr.com.app.scheduler.util.ViewHelper;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The type Conference schedule builder service.
 */
@Slf4j
@RequestScope
@Service
public class ConferenceScheduleBuilderServiceImpl implements ConferenceScheduleBuilderService{

    private final Logger logger = LoggerFactory.getLogger(ConferenceScheduleBuilderServiceImpl.class);

    private Map<Long, Presentation> presentationMap = new HashMap<>();
    private List<PresentationDto> presentationDtoList = new LinkedList<>();

    private String eventStart;
    private String eventEnd;
    private String lunchStart;
    private String lunchEnd;
    private String networkEventStart;
    private String networkEventEnd;

    private LocalTime time;
    private int totalMinutes = 0;

    private int currentRoomNumber = 0;
    private Set<String> timeSet = new TreeSet<>();
    private ParameterService parameterService;
    private ScheduleStrategy scheduleStrategy;

    /**
     * Instantiates a new Conference schedule builder service.
     *
     * @param parameterService the parameter service
     * @param scheduleStrategy the schedule strategy
     */
    @Autowired
    public ConferenceScheduleBuilderServiceImpl(ParameterService parameterService, ScheduleStrategy scheduleStrategy) {
        this.parameterService = parameterService;
        this.scheduleStrategy = scheduleStrategy;
    }

    @Override
    public boolean isFinish(){
        return totalMinutes == 0;
    }

    @Override
    public ConferenceScheduleBuilderService init(List<Presentation> presentationList){
        presentationList.forEach(presentation -> {
            presentationMap.put(presentation.getId(), presentation);
            totalMinutes += presentation.getTimeAsMinute().intValue();
        });

        eventStart = parameterService.getParameter(ParameterType.EVENT_START);
        eventEnd = parameterService.getParameter(ParameterType.EVENT_END);
        lunchStart = parameterService.getParameter(ParameterType.LUNCH_START);
        lunchEnd = parameterService.getParameter(ParameterType.LUNCH_END);
        networkEventStart = parameterService.getParameter(ParameterType.NETWORK_EVENT_START);
        networkEventEnd = parameterService.getParameter(ParameterType.NETWORK_EVENT_END);
        return this;
    }

    @Override
    public ConferenceScheduleBuilderService startEvent(){
        currentRoomNumber++;
        time = LocalTime.parse(eventStart);
        timeSet.add(TimeHelper.timeFormat(eventStart));
        return this;
    }

    @Override
    public ConferenceScheduleBuilderService endEvent(){
        time = LocalTime.parse(eventEnd);
        timeSet.add(TimeHelper.timeFormat(eventEnd));
        return this;
    }

    @Override
    public ConferenceScheduleBuilderService startNetworking(){
        time = LocalTime.parse(networkEventStart);
        return this;
    }

    @Override
    public ConferenceScheduleBuilderService startLunch(){
        time = LocalTime.parse(lunchStart);
        Presentation presentation = new Presentation();
        presentation.setType(PresentationType.LUNCH);
        presentation.setTimeAsMinute(TimeHelper.getMinuteBetweenStartTimeAndEndTime(lunchStart, lunchEnd));
        presentation.setName("LUNCH");
        presentationDtoList.add(createPresentationDto(presentation, lunchStart, lunchEnd));
        timeSet.add(TimeHelper.timeFormat(lunchStart));
        return this;
    }

    @Override
    public ConferenceScheduleBuilderService endLunch(){
        time = LocalTime.parse(lunchEnd);
        timeSet.add(TimeHelper.timeFormat(lunchEnd));
        return this;
    }

    @Override
    public ConferenceScheduleBuilderService createEventsFromStartToLunch(){
        presentationDtoList.addAll(createSubPresentationDtoList(getRemainingNormalPresentationList(), TimeHelper.getMinuteBetweenStartTimeAndEndTime(eventStart, lunchStart)));
        return this;
    }

    @Override
    public ConferenceScheduleBuilderService createEventsFromLunchToNetwork() {
        presentationDtoList.addAll(createSubPresentationDtoList(getRemainingNormalPresentationList(), TimeHelper.getMinuteBetweenStartTimeAndEndTime(lunchEnd, networkEventStart)));
        return this;
    }

    @Override
    public ConferenceScheduleBuilderService createEventsFromNetworkToEnd(){
        presentationDtoList.addAll(createSubPresentationDtoList(getRemainingAllPresentationList(), TimeHelper.getMinuteBetweenStartTimeAndEndTime(networkEventStart, networkEventEnd)));
        return this;
    }


    @Override
    public ConferenceSchedule build() {
        ConferenceSchedule conferenceSchedule = new ConferenceSchedule(presentationDtoList);
        conferenceSchedule.setScheduleStyle(ViewHelper.createScheduleStyle(timeSet, currentRoomNumber));
        conferenceSchedule.setRoomStyle(ViewHelper.createRoomStyle(currentRoomNumber));
        conferenceSchedule.setTimeStyle(ViewHelper.createTimeStyle(timeSet));
        return conferenceSchedule;
    }

    private List<PresentationDto> createSubPresentationDtoList(List<Presentation> allPresentationList, int maxMinute){
        List<PresentationDto> subPresentionDtoList = new LinkedList<>();

        List<Presentation> presentationList = scheduleStrategy.getSubListByMaximumTotalWeight(allPresentationList, maxMinute) ;

        for(Presentation presentation : presentationList){
            String timeStart = time.toString();
            time = time.plus(presentation.getTimeAsMinute(), ChronoUnit.MINUTES);
            String timeEnd = time.toString();

            presentationMap.remove(presentation.getId());

            PresentationDto presentationDto = createPresentationDto(presentation, timeStart, timeEnd);
            subPresentionDtoList.add(presentationDto);

            if(log.isDebugEnabled()){
                logger.debug(StringHelper.stringAppend("Rooms ", currentRoomNumber, " ---> ", currentRoomNumber, presentationDto.getTime().getFormattedTime()));
            }

            totalMinutes-=presentation.getTimeAsMinute();

            timeSet.add(presentationDto.getTime().getTimeStart());
            timeSet.add(presentationDto.getTime().getTimeEnd());
        }
        return subPresentionDtoList;
    }

    private PresentationDto createPresentationDto(Presentation presentation, String timeStart, String timeEnd){
        PresentationDto presentationDto = new PresentationDto();
        presentationDto.setId(presentation.getId());
        presentationDto.setRoom(currentRoomNumber);
        presentationDto.setName(presentation.getName());
        presentationDto.setType(presentation.getType());
        PresentationTime presentationTime = new PresentationTime();
        presentationTime.setTimeAsMinute(presentation.getTimeAsMinute());
        presentationTime.setTimeStart(TimeHelper.timeFormat(timeStart));
        presentationTime.setTimeEnd(TimeHelper.timeFormat(timeEnd));
        presentationTime.setFormattedTime(timeStart + " " + timeEnd);
        presentationDto.setTime(presentationTime);
        presentationDto.setHtmlClass(StringHelper.stringAppend("session session-", presentation.getId(), " track-", currentRoomNumber));
        presentationDto.setHtmlStyle(StringHelper.stringAppend("grid-column: track-", currentRoomNumber,  "; grid-row: time-", presentationTime.getTimeStart(), " / time-", presentationTime.getTimeEnd()));
        return presentationDto;
    }

    private List<Presentation> getRemainingAllPresentationList(){
        return presentationMap.values().stream().collect(Collectors.toList());
    }

    private List<Presentation> getRemainingNormalPresentationList(){
        return presentationMap.values().stream().filter(presentation -> PresentationType.NORMAL == presentation.getType()).collect(Collectors.toList());
    }

}
