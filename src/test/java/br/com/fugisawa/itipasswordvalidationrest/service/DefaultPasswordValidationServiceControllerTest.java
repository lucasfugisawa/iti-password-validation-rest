package br.com.fugisawa.itipasswordvalidationrest.service;

import br.com.fugisawa.itipasswordvalidationrest.domain.PasswordValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class DefaultPasswordValidationServiceControllerTest {

    @Mock
    private PasswordValidator passwordValidator;

    private DefaultPasswordValidationService defaultPasswordValidationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        defaultPasswordValidationService = new DefaultPasswordValidationService();
    }

    @Test
    @DisplayName("DefaultPasswordValidator.validate(String) should return true for valid passwords.")
    void validate_ValidPassword_ReturnsTrue() {
        String validPassword = "$Qwertyuiopasdfghjklz123";
        when(passwordValidator.validate(validPassword)).thenReturn(true);

        boolean result = defaultPasswordValidationService.validate(validPassword);

        assertTrue(result);
    }

    @Test
    @DisplayName("DefaultPasswordValidator.validate(String) should return false for invalid passwords.")
    void validate_InvalidPassword_ReturnsFalse() {
        String invalidPassword = "WeakPassword";
        when(passwordValidator.validate(invalidPassword)).thenReturn(false);

        boolean result = defaultPasswordValidationService.validate(invalidPassword);

        assertFalse(result);
    }
}
