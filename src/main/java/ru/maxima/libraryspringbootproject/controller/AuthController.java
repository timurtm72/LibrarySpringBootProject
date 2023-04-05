package ru.maxima.libraryspringbootproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.maxima.libraryspringbootproject.model.Person;
import ru.maxima.libraryspringbootproject.service.PeopleService;
import ru.maxima.libraryspringbootproject.service.RegistrationService;
import ru.maxima.libraryspringbootproject.validate.PersonValidator;
import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final PeopleService peopleService;
    private final PersonValidator validator;
    private final RegistrationService registrationService;
    @Autowired
    public AuthController(PeopleService peopleService, PersonValidator validator, RegistrationService registrationService) {
        this.peopleService = peopleService;
        this.validator = validator;
        this.registrationService = registrationService;
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        List<Person> people = peopleService.findAll();
        boolean emptyList = people.size() == 0;
        model.addAttribute("emptyList",emptyList);
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid Person person,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "auth/registration";
        }
        registrationService.saveRegisteredPerson(person);
        return "redirect:/auth/login";
    }
    @GetMapping("/logout")
    public String logOutPage() {
        return "logout";
    }
    @GetMapping("/makeadmin")
    public String showPeople(Model model) {
        if(peopleService.findRoleByName().equals("USER")){
            return "people";
        }
        Person person = new Person();
        model.addAttribute("person",person);
        model.addAttribute("peopleWithReg", peopleService.findAllWithMatchingPassword());
        model.addAttribute("people", peopleService.findAll());
        return "/auth/selectadmin";
    }

    @PostMapping("/addadmin")
    public String setAdminOrUser(@ModelAttribute("employeeForm") Person getPerson,
                           @RequestParam(value = "action", required = false) String action) {
        Person person = peopleService.findPerson(getPerson.getId());
        if(action.contains("Создать администратора")){
            person.setRole("ADMIN");
        }else if(action.contains("Отменить администратора")){
            person.setRole("USER");
        }

        peopleService.savePerson(person);
        return "redirect:/auth/makeadmin";
    }

}
