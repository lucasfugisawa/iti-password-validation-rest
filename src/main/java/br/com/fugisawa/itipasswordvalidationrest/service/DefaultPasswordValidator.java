package br.com.fugisawa.itipasswordvalidationrest.service;

import org.springframework.stereotype.Service;

@Service
public class DefaultPasswordValidator implements PasswordValidator {

    private br.com.fugisawa.itipasswordvalidationrest.domain.PasswordValidator passwordValidator;

    public DefaultPasswordValidator() {
        this.passwordValidator = br.com.fugisawa.itipasswordvalidationrest.domain.PasswordValidator.builder()
                .withMinLength(9)
                .withSpecialChar(br.com.fugisawa.itipasswordvalidationrest.domain.PasswordValidator.DEFAULT_SPECIAL_CHARACTERS)
                .withDigit()
                .withUpperCase()
                .withLowerCase()
                .withNoRepeatedChars()
                .build();
    }

    @Override
    public boolean validate(String password) {
        return this.passwordValidator.validate(password);
    }
}
