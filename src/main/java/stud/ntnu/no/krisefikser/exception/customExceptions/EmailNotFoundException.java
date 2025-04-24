package stud.ntnu.no.krisefikser.exception.customExceptions;

import lombok.Getter;
import stud.ntnu.no.krisefikser.exception.CustomErrorMessage;

@Getter
public class EmailNotFoundException extends RuntimeException {
  /**
   * Custom error message containing details about the exception.
   */
  private final CustomErrorMessage errorMessage;

  /**
   * Constructs a new {@code EditedValueUnchangedException} with the specified error message.
   *
   * @param errorMessage the detailed custom error message
   */
  public EmailNotFoundException(CustomErrorMessage errorMessage) {
    super(errorMessage.getMessage());
    this.errorMessage = errorMessage;
  }
}
