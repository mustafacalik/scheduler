package tr.com.app.scheduler.controller.dto;

import lombok.Getter;
import lombok.Setter;
import tr.com.app.scheduler.controller.PresentationType;

@Getter
@Setter
public class PresentationDto {
    private Long id;
    private Integer room;
    private String name;
    private PresentationType type;
    private PresentationTime time;
    private String htmlClass;
    private String htmlStyle;
}
