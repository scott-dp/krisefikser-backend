package stud.ntnu.no.krisefikser.dtos.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "DTO for creating or updating an EventType. Contains name, and description.")
public class EventTypeRequest {

  @NotBlank(message = "Name must not be blank")
  @Schema(description = "Name of the event type", example = "Natural Disaster")
  private String name;

  @NotBlank(message = "Description must not be blank")
  @Schema(description = "Description of the event type", example = "Covers natural hazards like " +
      "earthquakes or floods.")
  private String description;
}
