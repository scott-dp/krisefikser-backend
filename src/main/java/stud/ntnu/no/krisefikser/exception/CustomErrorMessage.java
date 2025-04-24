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
  USER_NOT_ENABLED(403, "User is not enabled."),
  /**
   * Error when the request email is not found.
   */
  EMAIL_NOT_FOUND(404, "Email not found."),

  /**
   * Error when creating a duplicate ItemCategory.
   */
  ITEM_CATEGORY_ALREADY_EXISTS(409, "ItemCategory already exists."),

  /**
   * Error when the verification token isn't found.
   */
  TOKEN_NOT_FOUND(404, "Token not found."),

  // --- Generic ---
  /**
   * Error when an internal server error occurs.
   */
  INTERNAL_SERVER_ERROR(500, "An internal server error occurred."),

  /**
   * Event not found.
   */
  EVENT_NOT_FOUND(404, "Event not found."),

  /**
   * Event type not found.
   */
  EVENT_TYPE_NOT_FOUND(404, "Event type not found."),

  /**
   * Event type already exists.
   */
  EVENT_TYPE_ALREADY_EXISTS(409, "Event type already exists."),

  /**
   * Error when GeoJson is not valid.
   */
  GEOJSON_NOT_VALID(400, "GeoJson data is not valid."),;

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
