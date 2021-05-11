package tr.com.app.scheduler.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * The type Global exception handler.
 */
@ControllerAdvice
@Slf4j
class GlobalExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Default error handler model and view.
     *
     * @param req the req
     * @param e   the e
     * @return the model and view
     */
    @ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e){
		logger.error("[URL] : {}", req.getRequestURL(), e);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("error");
		return mav;
	}

}
