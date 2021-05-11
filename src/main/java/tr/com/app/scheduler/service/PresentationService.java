package tr.com.app.scheduler.service;

import org.springframework.transaction.annotation.Transactional;
import tr.com.app.scheduler.controller.dto.PresentationDto;
import tr.com.app.scheduler.controller.dto.PresentationForm;
import tr.com.app.scheduler.model.Presentation;

import javax.validation.Valid;
import java.util.List;

/**
 * The interface Presentation service.
 */
public interface PresentationService {
    /**
     * Add presentation.
     *
     * @param presentationForm the presentation form
     */
    void addPresentation(@Valid PresentationForm presentationForm);

    /**
     * Gets all presentation dto.
     *
     * @return the all presentation dto
     */
    List<PresentationDto> getAllPresentationDto();

    /**
     * Gets all presentation.
     *
     * @return the all presentation
     */
    @Transactional(readOnly = true)
    List<Presentation> getAllPresentation();

    /**
     * Delete presentation.
     */
    @Transactional
    void deletePresentation();
}
