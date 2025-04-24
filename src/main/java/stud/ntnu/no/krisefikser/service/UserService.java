package stud.ntnu.no.krisefikser.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import stud.ntnu.no.krisefikser.config.JWTUtil;
import stud.ntnu.no.krisefikser.dtos.auth.LoginRequest;
import stud.ntnu.no.krisefikser.dtos.auth.RegisterRequest;
import stud.ntnu.no.krisefikser.entities.User;
import stud.ntnu.no.krisefikser.entities.VerificationToken;
import stud.ntnu.no.krisefikser.exception.CustomErrorMessage;
import stud.ntnu.no.krisefikser.exception.customExceptions.AppEntityNotFoundException;
import stud.ntnu.no.krisefikser.exception.customExceptions.EntityAlreadyExistsException;
import stud.ntnu.no.krisefikser.exception.customExceptions.UnauthorizedOperationException;
import stud.ntnu.no.krisefikser.repository.UserRepository;
import stud.ntnu.no.krisefikser.repository.VerificationTokenRepository;
import org.springframework.mail.*;
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
  private final VerificationTokenRepository verificationTokenRepository;
  private final JavaMailSender mailSender;
  /**
   * Registers a new user with the provided details.
   *
   * @param registerRequest the registration request containing user details
   * @return a message indicating the registration status
   */
  public void register(RegisterRequest registerRequest) {
    if (userRepository.existsByEmail(registerRequest.getEmail())) {
      logger.error("Email '{}' is already taken", registerRequest.getEmail());
      throw new EntityAlreadyExistsException(CustomErrorMessage.EMAIL_NOT_FOUND);
    }
    logger.info("Registering user with email '{}'", registerRequest.getEmail());
    User user = new User()
        .setEmail(registerRequest.getEmail())
        .setFirstName(registerRequest.getFirstName())
        .setLastName(registerRequest.getLastName())
        .setPassword(passwordEncoder.encode(registerRequest.getPassword())); //enabled is set automaticalliy to false
    //TODO set roles if needed
    userRepository.save(user);
    logger.info("User with email '{}' registered successfully, the account is not yet enabled", registerRequest.getEmail());

    createVerificationTokenAndSendVerificationEmail(user);
  }

  /**
   * Creates a verification token for the user and sends a verification email.
   *
   * @param user the user to create a verification token for
   */
  private void createVerificationTokenAndSendVerificationEmail(User user) {
    logger.info("Creating verification token for user '{}'", user.getEmail());
    String token = UUID.randomUUID().toString();
    VerificationToken verificationToken = new VerificationToken().setToken(token).setUser(user);
    verificationTokenRepository.save(verificationToken);
    logger.info("Verification token created for user '{}'", user.getEmail());

    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(user.getEmail());
    message.setSubject("Complete Registration!");
    message.setText("To confirm your account, please click here: " +
        "http://localhost:5173/verify?token=" + token);

    mailSender.send(message);
    logger.info("Verification email sent to '{}'", user.getEmail());
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

  /**
   * Enables a user account using the provided verification token.
   *
   * @param token the verification token
   * @throws AppEntityNotFoundException if the token is not found
   */
  public void enableUser(String token) {
    logger.info("Enabling user with token '{}'", token);
    VerificationToken verificationToken = verificationTokenRepository.findByToken(token);

    if (verificationToken == null) {
      logger.error("Token '{}' not found", token);
      throw new AppEntityNotFoundException(CustomErrorMessage.TOKEN_NOT_FOUND);
    }

    User user = verificationToken.getUser();
    user.setEnabled(true);
    userRepository.save(user);
    verificationTokenRepository.delete(verificationToken);
    logger.info("User with token '{}' enabled successfully", token);
  }
}
