package stud.ntnu.no.krisefikser.dtos.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "DTO for representing an EventType in API responses. " +
    "Contains name and description.")
public class EventTypeResponse {

  @Schema(description = "ID of the event type", example = "1")
  private Long id;

  @Schema(description = "Name of the event type", example = "Natural Disaster")
  private String name;

  @Schema(description = "Description of the event type", example = "Covers natural hazards like " +
      "earthquakes or floods.")
  private String description;
}
