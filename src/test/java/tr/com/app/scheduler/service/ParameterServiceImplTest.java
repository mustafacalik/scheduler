package tr.com.app.scheduler.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tr.com.app.scheduler.controller.ParameterType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ParameterServiceImplTest{

    @InjectMocks
    private ParameterServiceImpl parameterService;

    @Mock
    private Environment environment;

    @Test
    void getParameter_WhenGivenParameterType_GetItFromEnvironment(){
        when(environment.getProperty(ParameterType.EVENT_START.getKey())).thenReturn("09:00");

        assertEquals("09:00", parameterService.getParameter(ParameterType.EVENT_START));
    }


}
