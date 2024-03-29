package br.com.fugisawa.itipasswordvalidationrest.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("weakValidation")
public class WeakPasswordValidationService implements PasswordValidationService {

    private final br.com.fugisawa.itipasswordvalidationrest.domain.PasswordValidator passwordValidator;

    public WeakPasswordValidationService() {
        this.passwordValidator = br.com.fugisawa.itipasswordvalidationrest.domain.PasswordValidator.builder()
                .withMinLength(4)
                .withNoWhiteSpaces()
                .build();
    }

    @Override
    public boolean validate(String password) {
        return this.passwordValidator.validate(password);
    }
}
