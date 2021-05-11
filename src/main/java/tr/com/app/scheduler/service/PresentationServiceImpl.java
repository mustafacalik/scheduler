package tr.com.app.scheduler.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.app.scheduler.controller.dto.PresentationDto;
import tr.com.app.scheduler.controller.dto.PresentationForm;
import tr.com.app.scheduler.controller.dto.PresentationTime;
import tr.com.app.scheduler.model.Presentation;
import tr.com.app.scheduler.repository.PresentationRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Presentation service.
 */
@Service
public class PresentationServiceImpl implements PresentationService{

    private ModelMapper modelMapper;
    private PresentationRepository presentationRepository;

    /**
     * Instantiates a new Presentation service.
     *
     * @param modelMapper            the model mapper
     * @param presentationRepository the presentation repository
     */
    @Autowired
    public PresentationServiceImpl(ModelMapper modelMapper, PresentationRepository presentationRepository) {
        this.modelMapper = modelMapper;
        this.presentationRepository = presentationRepository;
    }

    @Override
    @Transactional
    public void addPresentation(PresentationForm presentationForm){
        Presentation presentation = modelMapper.map(presentationForm, Presentation.class);
        presentation.setTimeAsMinute(presentationForm.getTime());
        if(Boolean.TRUE.equals(presentationForm.getLightning())){
            presentation.setTimeAsMinute(5);
        }
        presentationRepository.save(presentation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PresentationDto> getAllPresentationDto(){
        return presentationRepository.findAllBy().map(presentation -> {
            PresentationDto presentationDto = modelMapper.map(presentation, PresentationDto.class);
            PresentationTime presentationTime = new PresentationTime();
            presentationTime.setTimeAsMinute(presentation.getTimeAsMinute());
            presentationDto.setTime(presentationTime);
            return presentationDto;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Presentation> getAllPresentation(){
        return presentationRepository.findAllBy().collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deletePresentation(){
        presentationRepository.deleteAll();
    }


}
