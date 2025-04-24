package stud.ntnu.no.krisefikser.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import stud.ntnu.no.krisefikser.config.JWTUtil;
import stud.ntnu.no.krisefikser.dto.LoginRequest;
import stud.ntnu.no.krisefikser.dto.RegisterRequest;
import stud.ntnu.no.krisefikser.entities.User;
import stud.ntnu.no.krisefikser.exception.CustomErrorMessage;
import stud.ntnu.no.krisefikser.exception.customExceptions.EntityAlreadyExistsException;
import stud.ntnu.no.krisefikser.exception.customExceptions.UnauthorizedOperationException;
import stud.ntnu.no.krisefikser.repository.UserRepository;

import java.time.Duration;
import java.util.UUID;

/**
 * Service class for handling user operations such as authentication,
 * registration, profile updates, and favorite listings management.
 */
@Service
@RequiredArgsConstructor
public class UserService {
  private static final Logger logger = LogManager.getLogger(UserService.class);
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final CustomUserDetailsService customUserDetailsService;
  private final JWTUtil jwtUtil;
  /**
   * Registers a new user with the provided details.
   *
   * @param registerRequest the registration request containing user details
   * @return a message indicating the registration status
   */
  public String register(RegisterRequest registerRequest) {
    if (userRepository.existsByEmail(registerRequest.getEmail())) {
      logger.error("Email '{}' is already taken", registerRequest.getEmail());
      throw new EntityAlreadyExistsException(CustomErrorMessage.EMAIL_NOT_FOUND);
    }

    User user = new User()
        .setEmail(registerRequest.getEmail())
        .setPassword(passwordEncoder.encode(registerRequest.getPassword())); //enabled is set automaticalliy to false
    //TODO set roles if needed
    userRepository.save(user);
    //Now make a verification token for enabling user
    String token = UUID.randomUUID().toString();
    return null;
  }

  /**
   * Authenticates a user and returns a JWT token as a secure cookie.
   *
   * @param request the authentication request
   * @return the response cookie containing the JWT token
   */
  public ResponseCookie authenticateAndGetCookie(LoginRequest request) {
    String token = authenticate(request);

    return ResponseCookie.from("auth-token", token)
        .httpOnly(true)
        //TODO add this when https is configured '.secure(true)'
        .sameSite("None")
        .path("/")
        .maxAge(10 * 365 * 24 * 60 * 60)
        .build();
  }

  /**
   * Authenticates a user and generates a JWT token.
   *
   * @param request the login request
   * @return the authentication response with token
   */
  public String authenticate(LoginRequest request) {
    logger.info("Authenticating user '{}'", request.getEmail());

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
    );

    UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getEmail());
    if (!userDetails.isEnabled()) {
      logger.error("User '{}' is not enabled", request.getEmail());
      throw new UnauthorizedOperationException(CustomErrorMessage.USER_NOT_ENABLED);
    }
    String token = jwtUtil.generateToken(userDetails);

    logger.info("JWT token successfully generated for user '{}'", request.getEmail());

    return token;
  }
}
