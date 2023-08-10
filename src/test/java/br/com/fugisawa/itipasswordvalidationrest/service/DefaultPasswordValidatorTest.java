package br.com.fugisawa.itipasswordvalidationrest.service;

import br.com.fugisawa.itipasswordvalidationrest.domain.PasswordValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class DefaultPasswordValidatorTest {

    @Mock
    private PasswordValidator passwordValidator;

    private DefaultPasswordValidator defaultPasswordValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        defaultPasswordValidator = new DefaultPasswordValidator();
    }

    @Test
    void validate_ValidPassword_ReturnsTrue() {
        String validPassword = "$Qwertyuiopasdfghjklz123";
        when(passwordValidator.validate(validPassword)).thenReturn(true);

        boolean result = defaultPasswordValidator.validate(validPassword);

        assertTrue(result);
    }

    @Test
    void validate_InvalidPassword_ReturnsFalse() {
        String invalidPassword = "WeakPassword";
        when(passwordValidator.validate(invalidPassword)).thenReturn(false);

        boolean result = defaultPasswordValidator.validate(invalidPassword);

        assertFalse(result);
    }
}
