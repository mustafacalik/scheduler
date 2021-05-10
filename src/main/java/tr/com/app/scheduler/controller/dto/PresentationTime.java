package tr.com.app.scheduler.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PresentationTime {
    private Integer timeAsMinute;
    private String timeStart;
    private String timeEnd;
    private String formattedTime;
}
