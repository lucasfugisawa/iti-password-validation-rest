package br.com.fugisawa.itipasswordvalidationrest.controller;

import br.com.fugisawa.itipasswordvalidationrest.service.PasswordValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

class PasswordValidatorTest {

    @Mock
    private PasswordValidator passwordValidator;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        var passwordValidatorController = new br.com.fugisawa.itipasswordvalidationrest.controller.PasswordValidator(passwordValidator);
        mockMvc = MockMvcBuilders.standaloneSetup(passwordValidatorController).build();
    }

    @Test
    void validatePassword_ValidPassword_ReturnsTrue() throws Exception {
        String validPassword = "StrongPassword123!";
        when(passwordValidator.validate(validPassword)).thenReturn(true);

        mockMvc.perform(get("/password/validate/{password}", validPassword)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void validatePassword_InvalidPassword_ReturnsFalse() throws Exception {
        String invalidPassword = "WeakPassword";
        when(passwordValidator.validate(invalidPassword)).thenReturn(false);

        mockMvc.perform(get("/password/validate/{password}", invalidPassword)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }
}
