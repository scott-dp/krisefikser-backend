package stud.ntnu.no.krisefikser.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stud.ntnu.no.krisefikser.dto.AuthRequest;
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


  /**
   * Register a new user.
   *
   * @param registerRequest the registration request containing user details.
   * @return a response entity with the registration status.
   */
  @Operation(summary = "Register new user", description = "Creates a new user account based on provided details")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User registered successfully"),
      @ApiResponse(responseCode = "409", description = "User with the given username already exists")
  })
  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody @Validated AuthRequest registerRequest) {
    logger.info("Auth: Attempting to register user with username '{}'", registerRequest.getUsername());
    String registerStatus = userService.register(registerRequest);
    logger.info("Auth: User registered successfully with username '{}'", registerRequest.getUsername());
    return ResponseEntity.ok(registerStatus);
  }
}
