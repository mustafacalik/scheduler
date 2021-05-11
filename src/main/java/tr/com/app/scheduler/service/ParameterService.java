package tr.com.app.scheduler.service;

import tr.com.app.scheduler.controller.ParameterType;

/**
 * The interface Parameter service.
 */
public interface ParameterService {

    /**
     * Gets parameter.
     *
     * @param parameterType the parameter type
     * @return the parameter
     */
    String getParameter(ParameterType parameterType);
}
