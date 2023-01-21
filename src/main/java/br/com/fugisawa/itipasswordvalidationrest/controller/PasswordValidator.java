package br.com.fugisawa.itipasswordvalidationrest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("password")
public class PasswordValidator {

    private br.com.fugisawa.itipasswordvalidationrest.service.PasswordValidator passwordValidator;

    public PasswordValidator(br.com.fugisawa.itipasswordvalidationrest.service.PasswordValidator passwordValidator) {
        this.passwordValidator = passwordValidator;
    }

    @Operation(summary = "Checks if a password is valid for ITI digital services.")
    @Parameter(in = ParameterIn.PATH, name = "password", description = "The password to be checked.")
    @ApiResponse(responseCode = "200", description = "Returns true if password is valid. Otherwise returns false.")
    @GetMapping("/validate/{password}")
    @ResponseBody
    public Boolean validatePassword(@PathVariable(name = "password") String password) {
        return passwordValidator.validate(password);
    }

}
