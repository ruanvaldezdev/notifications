package api.api.persistence;
import api.api.model.dto.NotificationRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotificationRequestValidator implements ConstraintValidator<ValidRecipient, NotificationRequest> {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String PHONE_REGEX = "^\\+?[1-9]\\d{1,14}$"; // Padrão internacional E.164

    @Override
    public boolean isValid(NotificationRequest value, ConstraintValidatorContext context) {
        if (value == null || value.channel() == null || value.recipient() == null) {
            return true; // Deixe o @NotNull cuidar disso
        }

        return switch (value.channel()) {
            case EMAIL -> value.recipient().matches(EMAIL_REGEX);
            case SMS -> value.recipient().matches(PHONE_REGEX);
            default -> true; // Push ou outros canais podem não ter padrão rígido
        };
    }
}
