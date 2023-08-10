package br.com.fugisawa.itipasswordvalidationrest.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

public class PasswordValidator {

    public static final CharSequence DEFAULT_SPECIAL_CHARACTERS = "!@#$%^&*()-+";

    public static class DefaultPredicates {
        public static final Predicate<String> HAS_UPPERCASE = (password) -> password.matches(".*[A-Z].*");
        public static final Predicate<String> HAS_LOWERCASE = (password) -> password.matches(".*[a-z].*");
        public static final Predicate<String> HAS_DIGIT = (password) -> password.matches(".*[0-9].*");
        public static final Predicate<String> NO_REPEATED_CHARS = (password) -> !password.matches(".*(.).*\\1.*");
        public static final Predicate<String> NO_WHITE_SPACES = (password) -> !password.matches(".*\\s.*");
    }

    private Set<Predicate<String>> predicates = new HashSet<>();

    private PasswordValidator() {
        super();
    }

    public static PasswordValidatorBuilder builder() {
        return new PasswordValidatorBuilder();
    }

    public static class PasswordValidatorBuilder {

        private final Set<Predicate<String>> predicates = new HashSet<>();

        public PasswordValidatorBuilder withPredicate(Predicate<String> predicate) {
            if (predicate != null) {
                this.predicates.add(predicate);
            }
            return this;
        }

        public PasswordValidatorBuilder withMinLength(int minLength) {
            Predicate<String> p = s -> s.length() >= minLength;
            this.predicates.add(p);
            return this;
        }

        public PasswordValidatorBuilder withMaxLength(int maxLength) {
            Predicate<String> p = s -> s.length() <= maxLength;
            this.predicates.add(p);
            return this;
        }

        public PasswordValidatorBuilder withSpecialChar(CharSequence specialChars) {
            CharSequence finalSpecialChars = (specialChars != null) ? specialChars : DEFAULT_SPECIAL_CHARACTERS;
            Predicate<String> p = s -> s.matches(".*[" + finalSpecialChars + "].*");
            this.predicates.add(p);
            return this;
        }

        public PasswordValidatorBuilder withUpperCase() {
            this.predicates.add(DefaultPredicates.HAS_UPPERCASE);
            return this;
        }

        public PasswordValidatorBuilder withLowerCase() {
            this.predicates.add(DefaultPredicates.HAS_LOWERCASE);
            return this;
        }

        public PasswordValidatorBuilder withDigit() {
            this.predicates.add(DefaultPredicates.HAS_DIGIT);
            return this;
        }

        public PasswordValidatorBuilder withNoRepeatedChars() {
            this.predicates.add(DefaultPredicates.NO_REPEATED_CHARS);
            return this;
        }

        public PasswordValidatorBuilder withNoWhiteSpaces() {
            this.predicates.add(DefaultPredicates.NO_WHITE_SPACES);
            return this;
        }

        public PasswordValidator build() {
            PasswordValidator passwordValidator = new PasswordValidator();
            passwordValidator.predicates = this.predicates;
            return passwordValidator;
        }
    }

    public boolean validate(String password) {
        if (password == null) {
            return false;
        }

        for (Predicate<String> predicate : predicates) {
            if (!predicate.test(password)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return "PasswordValidator{" +
                "predicates=" + predicates +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PasswordValidator that = (PasswordValidator) o;

        return Objects.equals(predicates, that.predicates);
    }

    @Override
    public int hashCode() {
        return predicates != null ? predicates.hashCode() : 0;
    }

}