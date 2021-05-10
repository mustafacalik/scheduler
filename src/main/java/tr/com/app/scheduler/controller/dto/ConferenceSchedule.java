package tr.com.app.scheduler.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ConferenceSchedule {

    private String scheduleStyle;
    private List<String[]> roomStyle;
    private List<String[]> timeStyle;
    private List<PresentationDto> presentationDtoList;

    public ConferenceSchedule(List<PresentationDto> presentationDtoList) {
        this.presentationDtoList = presentationDtoList;
    }
}
