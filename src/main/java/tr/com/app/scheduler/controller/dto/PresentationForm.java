package tr.com.app.scheduler.controller.dto;

import lombok.Getter;
import lombok.Setter;
import tr.com.app.scheduler.controller.PresentationType;

import javax.validation.constraints.*;

@Getter
@Setter
public class PresentationForm {
    @NotNull(message = "{presentationForm.name.notNull}")
    @NotEmpty(message = "{presentationForm.name.notEmpty}")
    @Size(min = 5, max = 50, message = "{presentationForm.name.size}")
    private String name;

    @Min(value = 5, message = "{presentationForm.timeAsMinute.min}}")
    @Max(value = 180, message = "{presentationForm.timeAsMinute.max}")
    private Integer time;

    @NotNull(message = "{presentationForm.type.notNull}")
    private PresentationType type;

    private Boolean lightning = Boolean.FALSE;
}
