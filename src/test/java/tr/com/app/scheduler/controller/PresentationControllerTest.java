package tr.com.app.scheduler.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tr.com.app.scheduler.service.PresentationService;
import tr.com.app.scheduler.service.ScheduleService;
import tr.com.app.scheduler.validation.PresentationValidator;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class PresentationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PresentationService presentationService;

    @MockBean
    private ScheduleService scheduleService;

    @SpyBean
    private PresentationValidator presentationValidator;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new PresentationController(presentationService, scheduleService, presentationValidator)).build();
    }


    @Test
    void main_WhenCalled_GetPresentationListAndReturnSuccesfullyToIndex() throws Exception {
        this.mockMvc
            .perform(get("/"))
                .andExpect(view().name("index"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(presentationService, times(1)).getAllPresentationDto();
    }

    @Test
    void schedule_WhenCalled_CreateScheduleAndReturnSuccesfullyToSchedule() throws Exception {
        this.mockMvc
                .perform(get("/schedule"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(scheduleService, times(1)).createSchedule();
    }

    @Test
    void reset_WhenCalled_DeleteAllPresentationsAndReturnSuccesfullyToIndex() throws Exception {
        this.mockMvc
                .perform(get("/reset"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());

        verify(presentationService, times(1)).deletePresentation();
    }

    @Test
    void add_WhenCalledWithTrueParameters_AddPresentationAndReturnSuccessfullyToMainIndex() throws Exception {
        this.mockMvc
                .perform(post("/add").content(buildUrlEncodedFormEntity("name", "abcde12345", "time", "5", "type", PresentationType.NORMAL.name()))
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(view().name("redirect:/"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());

        verify(presentationService, times(1)).addPresentation(Mockito.any());
    }

    @Test
    void add_WhenCalledWithTypeAsNetworkingAndTimeIsHigherThan60Minutes_ReturnToIndexForError() throws Exception {
        this.mockMvc
                .perform(post("/add").content(buildUrlEncodedFormEntity("name", "abcde12345", "time", "61", "type", PresentationType.NETWORKING.name(), "lightning", "false"))
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(view().name("index"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void add_WhenCalledWithBothLightningAndTimeParameters_ReturnToIndexForError() throws Exception {
        this.mockMvc
                .perform(post("/add").content(buildUrlEncodedFormEntity("name", "abcde12345", "time", "5", "type", PresentationType.NORMAL.name(), "lightning", "true"))
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(view().name("index"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void add_WhenCalledWithBothLightningAndTimeParametersEmpty_ReturnToIndexForError() throws Exception {
        this.mockMvc
                .perform(post("/add").content(buildUrlEncodedFormEntity("name", "abcde12345", "type", PresentationType.NORMAL.name()))
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(view().name("index"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }



    @ParameterizedTest
    @MethodSource("addParameters")
    void add_WhenCalledWithWrongParameters_ReturnToIndexForError(String key, String value) throws Exception {
        this.mockMvc
                .perform(post("/add").content(buildUrlEncodedFormEntity(key, value))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(view().name("index"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private static Stream<Arguments> addParameters() {
        return Stream.of(
                Arguments.of("name", "m"),
                Arguments.of("name", ""),
                Arguments.of("time", "1"),
                Arguments.of("time", "500")
        );
    }



    private String buildUrlEncodedFormEntity(String... params) {
        if( (params.length % 2) > 0 ) {
            throw new IllegalArgumentException("Need to give an even number of parameters");
        }
        StringBuilder result = new StringBuilder();
        for (int i=0; i<params.length; i+=2) {
            if( i > 0 ) {
                result.append('&');
            }
            try {
                result.
                        append(URLEncoder.encode(params[i], StandardCharsets.UTF_8.name())).
                        append('=').
                        append(URLEncoder.encode(params[i+1], StandardCharsets.UTF_8.name()));
            }
            catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        return result.toString();
    }


}
