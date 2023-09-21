package br.com.fugisawa.itipasswordvalidationrest.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("(Unit) PasswordValidator")
class PasswordValidationServiceControllerTest {

    private static PasswordValidator validator;

    @BeforeAll
    static void setup() {
        validator = PasswordValidator.builder()
                .withMinLength(8)
                .withMaxLength(24)
                .withSpecialChar(PasswordValidator.DEFAULT_SPECIAL_CHARACTERS)
                .withDigit()
                .withUpperCase()
                .withLowerCase()
                .withNoRepeatedChars()
                .withNoWhiteSpaces()
                .build();
    }

    @Test
    @DisplayName("PasswordValidator.validate(String) should return false for invalid passwords.")
    void validate_invalidPassword_shouldBeFalse() {
        assertFalse(validator.validate("")); // null
        assertFalse(validator.validate("$Ab1")); // length < 8
        assertFalse(validator.validate("$Abcdee1")); // repeated char
        assertFalse(validator.validate("$abcdef1")); // no uppercase
        assertFalse(validator.validate("$ABCDEF1")); // no lowercase
        assertFalse(validator.validate("$Abcde 1")); // white space
        assertFalse(validator.validate("$Qwertyuiopasdfghjklz1234")); // length > 24
    }

    @Test
    @DisplayName("PasswordValidator.validate(String) should return true for valid passwords.")
    void validate_validPassword_shouldBeTrue() {
        assertTrue(validator.validate("$Abcdef1"));
        assertTrue(validator.validate("$Qwertyuiopasdfghjklz123"));
    }

    @Test
    @DisplayName("PasswordValidator.withPredicate() should allow using custom predicates.")
    void validate_withCustomPredicate_validPassword_shouldBeTrue() {
        Predicate<String> pred = s -> s.contains("L");
        PasswordValidator val = PasswordValidator.builder().withPredicate(pred).build();
        assertTrue(val.validate("Linux"));
    }
}