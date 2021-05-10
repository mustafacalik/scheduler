package tr.com.app.scheduler.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import tr.com.app.scheduler.controller.PresentationType;
import tr.com.app.scheduler.controller.dto.PresentationForm;

@Component
public class PresentationValidator implements Validator {

    private static final String TIME_AS_MINUTE = "time";

    @Override
    public boolean supports(Class<?> aClass) {
        return PresentationForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        PresentationForm presentationForm = (PresentationForm) o;

        if(presentationForm.getTime() == null && Boolean.FALSE.equals(presentationForm.getLightning())){
            errors.rejectValue(TIME_AS_MINUTE, "presentationForm.timeAndLightning.notEmpty", null, null);
        }

        if (Boolean.TRUE.equals(presentationForm.getLightning()) && presentationForm.getTime() != null) {
            errors.rejectValue(TIME_AS_MINUTE, "presentationForm.timeOrLightning.empty", null, null);
        }

        if (PresentationType.NETWORKING == presentationForm.getType() && Boolean.FALSE.equals(presentationForm.getLightning()) && presentationForm.getTime() > 60) {
            errors.rejectValue(TIME_AS_MINUTE, "presentationForm.timeAsMinute.60", null, null);
        }

    }
}
