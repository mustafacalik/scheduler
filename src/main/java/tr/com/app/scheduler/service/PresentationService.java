package tr.com.app.scheduler.service;

import org.springframework.transaction.annotation.Transactional;
import tr.com.app.scheduler.controller.dto.PresentationDto;
import tr.com.app.scheduler.controller.dto.PresentationForm;
import tr.com.app.scheduler.model.Presentation;

import javax.validation.Valid;
import java.util.List;

public interface PresentationService {
    void addPresentation(@Valid PresentationForm presentationForm);

    List<PresentationDto> getAllPresentationDto();

    @Transactional(readOnly = true)
    List<Presentation> getAllPresentation();

    @Transactional
    void deletePresentation();
}
