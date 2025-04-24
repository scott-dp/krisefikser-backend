package stud.ntnu.no.krisefikser.exception;

import lombok.Getter;

/**
 * Enum representing custom error messages and their associated HTTP status codes.
 * <p>
 * Used throughout the application to provide consistent error handling and responses.
 * Each constant defines both a status code and a descriptive message.
 * </p>
 */
@Getter
public enum CustomErrorMessage {
  /**
   * Error when the request email is not found.
   */
  EMAIL_NOT_FOUND(404, "Email not found."),

  /**
   * Error when creating a duplicate ItemCategory.
   */
  ITEM_CATEGORY_ALREADY_EXISTS(409, "ItemCategory already exists."),
  
  // --- Generic ---
  /**
   * Error when an internal server error occurs.
   */
  INTERNAL_SERVER_ERROR(500, "An internal server error occurred.");

  /**
   * The HTTP status code associated with the error.
   */
  private final int status;

  /**
   * The descriptive error message.
   */
  private final String message;

  CustomErrorMessage(int status, String message) {
    this.status = status;
    this.message = message;
  }
}
