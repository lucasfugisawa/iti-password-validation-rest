package br.com.fugisawa.itipasswordvalidationrest.controller;

import br.com.fugisawa.itipasswordvalidationrest.service.PasswordValidationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("password")
public class PasswordValidatorController {

    private final PasswordValidationService passwordValidationService;

    public PasswordValidatorController(PasswordValidationService passwordValidationService) {
        this.passwordValidationService = passwordValidationService;
    }

    @Operation(summary = "Checks if a password is valid for ITI digital services.")
    @Parameter(in = ParameterIn.PATH, name = "password", description = "The password to be checked.")
    @ApiResponse(responseCode = "200", description = "Returns true if password is valid. Otherwise returns false.")
    @GetMapping("/validate/{password}")
    @ResponseBody
    public Boolean validatePassword(@PathVariable(name = "password") String password) {
        return passwordValidationService.validate(password);
    }

}
