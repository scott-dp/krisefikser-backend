package stud.ntnu.no.krisefikser.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import stud.ntnu.no.krisefikser.exception.customExceptions.EmailNotFoundException;
import org.springframework.stereotype.Service;
import stud.ntnu.no.krisefikser.repository.UserRepository;

import static stud.ntnu.no.krisefikser.exception.CustomErrorMessage.EMAIL_NOT_FOUND;

/**
 * Service implementation for loading user details from the database.
 * <p>
 * Implements {@link UserDetailsService} to provide authentication functionality
 * required by Spring Security.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private static final Logger logger = LogManager.getLogger(CustomUserDetailsService.class);

  private final UserRepository userRepository;
  /**
   * Loads a user by their username.
   * <p>
   * This method is used by Spring Security during authentication.
   * If the user is not found, a {@link EmailNotFoundException} is thrown.
   * </p>
   *
   * @param email the email of the user to be loaded
   * @return the {@link UserDetails} of the authenticated user
   * @throws EmailNotFoundException if the user is not found in the database
   */
  @Override
  public UserDetails loadUserByUsername(String email) throws EmailNotFoundException {
    try {
      logger.info("Attempting to load user by email: {}", email);
      return userRepository.findByEmail(email)
          .orElseThrow(() -> {
            logger.warn("User not found with email: {}", email);
            return new EmailNotFoundException(EMAIL_NOT_FOUND);
          });
    } catch(Exception e) {
      logger.error("Error loading user by email: {}", email, e);
      throw new EmailNotFoundException(EMAIL_NOT_FOUND);
    }
  }
}
