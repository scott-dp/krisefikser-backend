package stud.ntnu.no.krisefikser.dto.mappers;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.geojson.GeoJsonReader;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import stud.ntnu.no.krisefikser.entities.Event;
import stud.ntnu.no.krisefikser.dto.EventRequest;
import stud.ntnu.no.krisefikser.dto.EventResponse;
import stud.ntnu.no.krisefikser.entities.EventType;
import stud.ntnu.no.krisefikser.exception.CustomErrorMessage;
import stud.ntnu.no.krisefikser.exception.customExceptions.InvalidGeoJsonException;

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
   * @param eventType the {@link EventType} associated with the event
   * @return the corresponding {@link Event} entity
   * @throws InvalidGeoJsonException if the GeoJSON string is invalid
   */
  public Event toEntity(EventRequest request, EventType eventType) {
    logger.debug("Mapping EventRequest to Event entity for event type '{}'",
        () -> eventType.getName() + " (ID: " + eventType.getId() + ")");

    Event event = new Event()
        .setTitle(request.getTitle())
        .setDescription(request.getDescription())
        .setSeverity(request.getSeverity())
        .setType(eventType);

    try {
      GeoJsonReader reader = new GeoJsonReader();
      Geometry geometry = reader.read(request.getGeometryGeoJson());
      geometry.setSRID(4326);
      event.setGeometry(geometry);
    } catch (Exception e) {
      logger.error("Invalid GeoJSON format: {}", request.getGeometryGeoJson(), e);
      throw new InvalidGeoJsonException(CustomErrorMessage.GEOJSON_NOT_VALID);
    }

    logger.info("Mapped EventRequest to Event entity: {}", event.getId());
    return event;
  }
}
