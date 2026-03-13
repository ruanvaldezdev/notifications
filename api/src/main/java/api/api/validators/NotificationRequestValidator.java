package api.api.validators;
import api.api.dto.NotificationRequest;
import api.api.persistence.ValidRecipient;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotificationRequestValidator implements ConstraintValidator<ValidRecipient, NotificationRequest> {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String PHONE_REGEX = "^\\+?[1-9]\\d{1,14}$"; // Padrão internacional E.164

    @Override
public boolean isValid(NotificationRequest value, ConstraintValidatorContext context) {
    if (value == null || value.channel() == null || value.recipient() == null) return true;

    boolean isValid = switch (value.channel()) {
        case EMAIL -> value.recipient().matches(EMAIL_REGEX);
        case SMS -> value.recipient().matches(PHONE_REGEX);
        default -> true;
    };

    if (!isValid) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
               .addPropertyNode("recipient") // Associa o erro ao campo recipient
               .addConstraintViolation();
    }
    return isValid;
}

}
