package tr.com.app.scheduler.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tr.com.app.scheduler.controller.dto.PresentationDto;
import tr.com.app.scheduler.controller.dto.PresentationForm;
import tr.com.app.scheduler.model.Presentation;
import tr.com.app.scheduler.repository.PresentationRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class PresentationServiceImplTest {

    @InjectMocks
    private PresentationServiceImpl presentationService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PresentationRepository presentationRepository;


    @Test
    void addPresentation_WhenCalled_CallRepositorySave(){
        Presentation presentation = new Presentation();
        when(modelMapper.map(any(), any())).thenReturn(presentation);

        presentationService.addPresentation(new PresentationForm());

        verify(presentationRepository, times(1)).save(presentation);
    }


    @Test
    void addPresentation_WhenItIsLightningPresentation_SetPresentationTime5Minutes(){
        Presentation presentation = new Presentation();
        when(modelMapper.map(any(), any())).thenReturn(presentation);

        PresentationForm presentationForm = new PresentationForm();
        presentationForm.setLightning(Boolean.TRUE);
        presentationService.addPresentation(presentationForm);

        assertEquals(5, presentation.getTimeAsMinute());
    }

    @Test
    void getAllPresentationDto_WhenCalled_ReturnDtosOfEntitUsingModelMapper(){
        List<Presentation> presentationList = new ArrayList<>();
        Presentation presentation1 = new Presentation();
        Presentation presentation2 = new Presentation();
        presentationList.add(presentation1);
        presentationList.add(presentation2);
        when(presentationRepository.findAllBy()).thenReturn(presentationList.stream());
        PresentationDto presentationDto = new PresentationDto();
        when(modelMapper.map(any(), any())).thenReturn(presentationDto);

        List<PresentationDto> presentationDtoList = presentationService.getAllPresentationDto();

        verify(modelMapper, times(presentationList.size())).map(any(), any());
        assertEquals(presentationList.size(), presentationDtoList.size());

    }

    @Test
    void getAllPresentation_WhenCalled_CallRepositoryFindAllBy(){
        presentationService.getAllPresentation();

        verify(presentationRepository, times(1)).findAllBy();
    }

    @Test
    void deletePresentation_WhenCalled_CallRepositoryDeleteAll(){
        presentationService.deletePresentation();

        verify(presentationRepository, times(1)).deleteAll();
    }


}
