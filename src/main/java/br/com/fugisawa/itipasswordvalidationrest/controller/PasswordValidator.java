package br.com.fugisawa.itipasswordvalidationrest.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("password")
public class PasswordValidator {

    private br.com.fugisawa.itipasswordvalidationrest.service.PasswordValidator passwordValidator;

    public PasswordValidator(br.com.fugisawa.itipasswordvalidationrest.service.PasswordValidator passwordValidator) {
        this.passwordValidator = passwordValidator;
    }

    @GetMapping("/validate/{password}")
    @ResponseBody
    public Boolean validatePassword(@PathVariable(name = "password") String password) {
        return passwordValidator.validate(password);
    }

}
