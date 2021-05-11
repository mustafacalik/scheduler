package tr.com.app.scheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import tr.com.app.scheduler.controller.dto.PresentationForm;
import tr.com.app.scheduler.service.PresentationService;
import tr.com.app.scheduler.service.ScheduleService;
import tr.com.app.scheduler.validation.PresentationValidator;

import javax.validation.Valid;

/**
 * The type Presentation controller.
 */
@Controller
public class PresentationController {

    private PresentationService presentationService;
    private ScheduleService scheduleService;
    private PresentationValidator presentationValidator;

    /**
     * Instantiates a new Presentation controller.
     *
     * @param presentationService   the presentation service
     * @param scheduleService       the schedule service
     * @param presentationValidator the presentation validator
     */
    @Autowired
    public PresentationController(PresentationService presentationService, ScheduleService scheduleService, PresentationValidator presentationValidator) {
        this.presentationService = presentationService;
        this.scheduleService = scheduleService;
        this.presentationValidator = presentationValidator;
    }

    /**
     * Main string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("presentationList", presentationService.getAllPresentationDto());
        model.addAttribute("presentationForm",new PresentationForm());
        return "index";
    }


    /**
     * Show program string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/schedule")
    public String showProgram(Model model) {
        model.addAttribute("schedule",scheduleService.createSchedule());
        return "schedulePage";
    }


    /**
     * Add string.
     *
     * @param presentationForm the presentation form
     * @param errors           the errors
     * @param model            the model
     * @return the string
     */
    @PostMapping("/add")
    public String add(@Valid PresentationForm presentationForm, BindingResult errors, Model model) {
        presentationValidator.validate(presentationForm, errors);
        if(errors.hasErrors()){
            model.addAttribute("presentationList", presentationService.getAllPresentationDto());
            model.addAttribute("presentationForm", presentationForm);
            return "index";
        }
        presentationService.addPresentation(presentationForm);
        return "redirect:/";
    }

    /**
     * Reset string.
     *
     * @return the string
     */
    @GetMapping("/reset")
    public String reset() {
        presentationService.deletePresentation();
        return "redirect:/";
    }


}
