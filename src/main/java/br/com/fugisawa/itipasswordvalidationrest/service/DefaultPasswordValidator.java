package br.com.fugisawa.itipasswordvalidationrest.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"strongValidation", "default"})
public class DefaultPasswordValidator implements PasswordValidator {

    private final br.com.fugisawa.itipasswordvalidationrest.domain.PasswordValidator passwordValidator;

    public DefaultPasswordValidator() {
        this.passwordValidator = br.com.fugisawa.itipasswordvalidationrest.domain.PasswordValidator.builder()
                .withMinLength(9)
                .withSpecialChar(br.com.fugisawa.itipasswordvalidationrest.domain.PasswordValidator.DEFAULT_SPECIAL_CHARACTERS)
                .withDigit()
                .withUpperCase()
                .withLowerCase()
                .withNoRepeatedChars()
                .withNoWhiteSpaces()
                .build();
    }

    @Override
    public boolean validate(String password) {
        return this.passwordValidator.validate(password);
    }
}
