package stud.ntnu.no.krisefikser.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * DTO for authentication request.
 * <p>
 * Contains user credentials for login.
 * </p>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Schema(description = "Request object containing user credentials for authentication.")
public class AuthRequest {
  /**
   * The first name of the user attempting to log in.
   */
  @NotBlank(message = "firstname cannot be blank")
  @Schema(description = "The firstname of the user attempting to log in", example = "john")
  private String firstName;

  /**
   * The last name of the user attempting to log in.
   */
  @NotBlank(message = "lastname cannot be blank")
  @Schema(description = "The lastname of the user attempting to log in", example = "doe")
  private String lastName;

  /**
   * The username of the user attempting to log in.
   */
  @NotBlank(message = "email cannot be blank")
  @Schema(description = "The email of the user attempting to log in", example = "johndoe@example.com")
  private String email;

  /**
   * The password of the user attempting to log in.
   */
  @NotBlank(message = "Password cannot be blank")
  @Schema(description = "The password of the user attempting to log in", example = "password123")
  private String password;
}
