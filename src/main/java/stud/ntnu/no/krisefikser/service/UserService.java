package stud.ntnu.no.krisefikser.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import stud.ntnu.no.krisefikser.dto.AuthRequest;
import stud.ntnu.no.krisefikser.exception.CustomErrorMessage;
import stud.ntnu.no.krisefikser.exception.customExceptions.EntityAlreadyExistsException;
import stud.ntnu.no.krisefikser.repository.UserRepository;

/**
 * Service class for handling user operations such as authentication,
 * registration, profile updates, and favorite listings management.
 */
@Service
@RequiredArgsConstructor
public class UserService {
  private static final Logger logger = LogManager.getLogger(UserService.class);
  private final UserRepository userRepository;
  /**
   * Registers a new user with the provided details.
   *
   * @param registerRequest the registration request containing user details
   * @return a message indicating the registration status
   */
  public String register(AuthRequest registerRequest) {
    if (userRepository.existsByEmail(registerRequest.getEmail())) {
      logger.error("Email '{}' is already taken", registerRequest.getEmail());
      throw new EntityAlreadyExistsException(CustomErrorMessage.USERNAME_ALREADY_EXISTS);
    }
    //TODO keep implementing
    return null;
  }

  // Other service methods can be added here
}
