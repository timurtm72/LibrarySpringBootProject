package ru.maxima.libraryspringbootproject.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.maxima.libraryspringbootproject.dto.PersonDTO;
import ru.maxima.libraryspringbootproject.exception_person.PersonNotRegisteredException;
import ru.maxima.libraryspringbootproject.jwt_util.Utils;
import ru.maxima.libraryspringbootproject.model.LoginCredentials;
import ru.maxima.libraryspringbootproject.model.Person;
import ru.maxima.libraryspringbootproject.security.JWTUtil;
import ru.maxima.libraryspringbootproject.service.RegistrationService;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class AuthRestController {

    private JWTUtil jwtUtil;
    private PasswordEncoder passwordEncoder;
    private RegistrationService authRestService;
    private Utils util;
    @Autowired
    public AuthRestController(JWTUtil jwtUtil, PasswordEncoder passwordEncoder, RegistrationService authRestService, Utils util) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.authRestService = authRestService;
        this.util = util;
    }
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginCredentials body){
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.getName(), body.getPassword());

            SecurityContextHolder.getContext().setAuthentication(authInputToken);

            String token = jwtUtil.generateToken(body.getName());

            return Collections.singletonMap("jwt-token", token);
        }catch (AuthenticationException authExc){
            throw new RuntimeException("Invalid Login Credentials");
        }
    }
    @PostMapping("/register")
    public ResponseEntity<PersonDTO> register(@RequestBody @Valid Person person,
                                              BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            StringBuilder bld = new StringBuilder();
            bindingResult.getFieldErrors().forEach(error -> {
                bld.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            });
            throw new PersonNotRegisteredException(bld.toString());
        }

        Person newPerson = authRestService.saveRegisteredPerson(person);
        return ResponseEntity.accepted().body(util.convertToPersonDTO(newPerson));
    }
}
