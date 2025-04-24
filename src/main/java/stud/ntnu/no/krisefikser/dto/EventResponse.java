package stud.ntnu.no.krisefikser.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "DTO representing the response for an event, including metadata like " +
    "title, geometry, type name, and severity.")
public class EventResponse {

  @Schema(description = "ID of the event", example = "1")
  private Long id;

  @Schema(description = "Title of the event", example = "Earthquake in Oslo")
  private String title;

  @Schema(description = "Detailed description of the event", example = "A magnitude 5.0 earth-" +
      "quake was reported in Oslo.")
  private String description;

  @Schema(description = "Geometry of the event in GeoJSON format",
      example = "{\"type\": \"Point\", \"coordinates\": [10.3951, 63.4305]}")
  private String geometryGeoJson;

  @Schema(description = "Name of the event type", example = "Natural Disaster")
  private String eventTypeName;

  @Schema(description = "Severity of the event", example = "DANGER")
  private EventSeverity severity;
}
