package stud.ntnu.no.krisefikser.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import stud.ntnu.no.krisefikser.dto.LoginRequest;
import stud.ntnu.no.krisefikser.dto.RegisterRequest;
import stud.ntnu.no.krisefikser.entities.User;
import stud.ntnu.no.krisefikser.entities.VerificationToken;
import stud.ntnu.no.krisefikser.repository.VerificationTokenRepository;
import stud.ntnu.no.krisefikser.service.UserService;

/**
 * Controller for handling authentication-related endpoints.
 *
 * <p>Provides endpoints for user registration and authentication.</p>
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentications", description = "Endpoints for user registration and login")
public class AuthController {
  private static final Logger logger = LogManager.getLogger(AuthController.class);
  private final UserService userService;
  private final VerificationTokenRepository tokenRepository;


  /**
   * Register a new user.
   *
   * @param registerRequest the registration request containing user details.
   * @return a response entity with the registration status.
   */
  @Operation(summary = "Register new user", description = "Creates a new user account based on provided details")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User registered successfully"),
      @ApiResponse(responseCode = "409", description = "User with the given email already exists")
  })
  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody @Validated RegisterRequest registerRequest) {
    logger.info("Auth: Attempting to register user with email '{}'", registerRequest.getEmail());
    userService.register(registerRequest);
    logger.info("Auth: User registered successfully with email '{}'", registerRequest.getEmail());
    return ResponseEntity.ok("User registered successfully, please check your email to verify your account");
  }

  /**
   * Authenticate and return JWT token as cookie.
   *
   * @param loginRequest the login request containing email and password
   * @param response    the HTTP response to set the cookie
   * @return a response entity with the authentication status and JWT token as a cookie
   */
  @Operation(summary = "Login", description = "Authenticates user credentials and returns a JWT token as cookie if valid")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Login successful"),
      @ApiResponse(responseCode = "401", description = "Invalid email or password"),
      @ApiResponse(responseCode = "403", description = "User is not enabled"),
      @ApiResponse(responseCode = "500", description = "Invalid signing key for signing JWT token")
  })
  @PostMapping("/login")
  public ResponseEntity<String> authenticate(@RequestBody @Validated LoginRequest loginRequest, HttpServletResponse response) {
    logger.info("Auth: Authenticating user with username '{}'", loginRequest.getEmail());
    ResponseCookie cookie = userService.authenticateAndGetCookie(loginRequest);
    logger.debug("Auth: JWT token created for username '{}': {}", loginRequest.getEmail(), cookie.getValue());
    response.addHeader("Set-Cookie", cookie.toString());
    logger.info("Auth: JWT token set as cookie for username '{}'", loginRequest.getEmail());
    return ResponseEntity.ok("Login successful");
  }

  /**
   * Verify the email address using the provided token.
   *
   * @param token the verification token
   * @return a response entity with the verification status
   */
  @Operation(summary = "Verify email", description = "Verifies the email address using the provided token")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Email verified successfully"),
      @ApiResponse(responseCode = "404", description = "Invalid token")
  })
  @GetMapping("/verify")
  public ResponseEntity<String> verifyEmail(@RequestParam("token") String token) {
    userService.enableUser(token);

    return ResponseEntity.ok("Email verified successfully!");
  }
}
