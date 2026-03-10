package api.api.config;

import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

public class AuthenticatedUser {

   public static UUID getAuthenticatedUserId() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    if (auth == null || !auth.isAuthenticated()) {
        throw new RuntimeException("user não autorizado a realizar transação");
    }

    // Spring Security OAuth2 coloca um objeto Jwt no Principal
    if (auth.getPrincipal() instanceof Jwt jwt) {
        // Extrai a String do "sub" e converte para UUID
        return UUID.fromString(jwt.getSubject());
    }

    throw new RuntimeException("Falha ao obter UUID: Principal não é um JWT válido.");
}

}