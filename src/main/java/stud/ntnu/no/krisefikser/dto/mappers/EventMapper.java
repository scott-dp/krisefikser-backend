package stud.ntnu.no.krisefikser.dto.mappers;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.geojson.GeoJsonReader;
import org.springframework.stereotype.Service;
import stud.ntnu.no.krisefikser.entities.Event;
import stud.ntnu.no.krisefikser.dto.EventRequest;
import stud.ntnu.no.krisefikser.dto.EventResponse;
import stud.ntnu.no.krisefikser.entities.EventType;

/**
 * Service for mapping between {@link Event} entities and their corresponding DTOs.
 * <p>
 *  Converts {@link EventRequest} objects into {@link Event} entities,
 *  and {@link Event} entities into {@link EventResponse} DTOs for API responses.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class EventMapper {

  private static final Logger logger = LogManager.getLogger(EventMapper.class);




  /**
   * Converts a {@link EventRequest} DTO to a {@link Event} entity.
   *
   * @param request the {@link EventRequest} DTO containing the event data
   * @return the corresponding {@link Event} entity
   */
  public Event toEntity(EventRequest dto, EventType eventType) {
    Event event = new Event()
        .setTitle(dto.getTitle())
        .setDescription(dto.getDescription())
        .setSeverity(dto.getSeverity())
        .setType(eventType);

    try {
      GeoJsonReader reader = new GeoJsonReader();
      Geometry geometry = reader.read(dto.getGeometryGeoJson());
      geometry.setSRID(4326);
      event.setGeometry(geometry);
    } catch (Exception e) {
      throw new RuntimeException("Invalid GeoJSON input", e);
    }

    logger.info("Mapped EventRequest to Event entity: {}", event.getId());
    return event;
  }
}
