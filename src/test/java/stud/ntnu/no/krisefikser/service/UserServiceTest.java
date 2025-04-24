package stud.ntnu.no.krisefikser.service;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import stud.ntnu.no.krisefikser.dtos.auth.LoginRequest;
import stud.ntnu.no.krisefikser.dtos.auth.RegisterRequest;
import stud.ntnu.no.krisefikser.entities.User;
import stud.ntnu.no.krisefikser.exception.customExceptions.EntityAlreadyExistsException;
import stud.ntnu.no.krisefikser.exception.customExceptions.UnauthorizedOperationException;
import stud.ntnu.no.krisefikser.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {
  @Autowired
  private UserService userService;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;
  User user;

  @BeforeEach
  public void setUp() {
    userRepository.deleteAll();

    user = new User()
        .setEmail("test@gmail.com")
        .setFirstName("Test")
        .setLastName("User")
        .setPassword(passwordEncoder.encode("password123"))
        .setEnabled(true);
    //initialize the user repository with an enabled test user
    userRepository.save(user);
  }

  @Test
  public void testAuthenticate() {
    LoginRequest loginRequest = new LoginRequest().setEmail("test@gmail.com").setPassword("password123");

    String token = userService.authenticate(loginRequest);
    assertNotNull(token, "Token should not be null");
    assertNotNull(userService.authenticateAndGetCookie(loginRequest), "Cookie should not be null");
    user.setEnabled(false);
    userRepository.save(user);
    assertThrows(DisabledException.class, () -> userService.authenticate(loginRequest));

    assertThrows(
        AuthenticationException.class, () -> userService.authenticate(new LoginRequest()
            .setEmail("doesntexist@gmail.com")
            .setPassword("password123")));

  }
}
