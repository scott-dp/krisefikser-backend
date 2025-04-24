package stud.ntnu.no.krisefikser.dtos.event;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import stud.ntnu.no.krisefikser.entities.EventSeverity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "DTO for creating or updating an event, including title, description, " +
    "geometry, event type ID, and severity.")
public class EventRequest {

  @NotBlank(message = "Title must not be blank")
  @Schema(description = "Title of the event", example = "Earthquake in Oslo")
  private String title;

  @NotBlank(message = "Description must not be blank")
  @Schema(description = "Detailed description of the event",
      example = "A magnitude 5.0 earthquake was reported in Oslo.")
  private String description;

  @NotBlank(message = "Geometry must not be blank")
  @Schema(description = "Geometry of the event in GeoJSON format",
      example = "{\"type\": \"Point\", \"coordinates\": [10.3951, 63.4305]}")
  private String geometryGeoJson;

  @NotNull(message = "Event type ID must not be null")
  @Schema(description = "ID of the event type", example = "1")
  private Long eventTypeId;

  @NotNull(message = "Severity must not be null")
  @Schema(description = "Severity of the event", example = "DANGER")
  private EventSeverity severity;
}
