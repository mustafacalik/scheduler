package tr.com.app.scheduler.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.core.env.Environment;
import tr.com.app.scheduler.BaseTest;
import tr.com.app.scheduler.controller.ParameterType;

import static org.mockito.Mockito.when;

public class ParameterServiceImplTest extends BaseTest {

    @InjectMocks
    private ParameterServiceImpl parameterService;

    @Mock
    private Environment environment;

    @Test
    public void getParameter_WhenGivenParameterType_GetItFromEnvironment(){
        when(environment.getProperty(ParameterType.EVENT_START.getKey())).thenReturn("09:00");

        Assertions.assertEquals("09:00", parameterService.getParameter(ParameterType.EVENT_START));
    }


}
