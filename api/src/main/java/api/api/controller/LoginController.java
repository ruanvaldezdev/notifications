package api.api.controller;
import java.time.Instant;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import  api.api.model.User;
import api.api.model.dto.LoginRequest;
import api.api.model.dto.LoginResponse;
import api.api.persistence.UserRepository;

@RestController
@RequestMapping("/auth")
public class LoginController {
    
    private final JwtEncoder jwtEncoder;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public LoginController(JwtEncoder jwtEncoder, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        // Authenticate user
        var user = userRepository.findByUsername(loginRequest.username());

        if(user.isEmpty() || !user.get().isLoginCorrect(loginRequest, passwordEncoder)) {
          throw new RuntimeException("Invalid username or password");
        }
        var now =Instant.now();
        var tempoExpiracao = 24*60L; // 1 hour in seconds

        var claims = JwtClaimsSet.builder()
            .subject(user.get().getId().toString())
            .issuer("backend")
            .issuedAt(now)
            .expiresAt(now.plusSeconds(tempoExpiracao))
            .build();

        var jwtToken = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();


        return ResponseEntity.ok(new LoginResponse(jwtToken, tempoExpiracao ));
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody LoginRequest registerRequest) {
        var userDb =userRepository.findByUsername(registerRequest.username());
        if(userDb.isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");}
        
    User user = new User();
    user.setUsername(registerRequest.username());
    user.setPassword( passwordEncoder.encode(registerRequest.password()));

    userRepository.save(user);

    return ResponseEntity.ok("User registered successfully");
    }


}
