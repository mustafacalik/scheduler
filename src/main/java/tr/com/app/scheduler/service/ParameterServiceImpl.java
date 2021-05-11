package tr.com.app.scheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import tr.com.app.scheduler.controller.ParameterType;

/**
 * The type Parameter service.
 */
@Service
public class ParameterServiceImpl implements tr.com.app.scheduler.service.ParameterService {

    private Environment environment;

    /**
     * Instantiates a new Parameter service.
     *
     * @param environment the environment
     */
    @Autowired
    public ParameterServiceImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    @Cacheable("parameters")
    public String getParameter(ParameterType parameterType){
        return environment.getProperty(parameterType.getKey());
    }

}
