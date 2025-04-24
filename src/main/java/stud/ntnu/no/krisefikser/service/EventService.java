package stud.ntnu.no.krisefikser.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.geojson.GeoJsonReader;
import org.springframework.stereotype.Service;
import stud.ntnu.no.krisefikser.dto.EventRequest;
import stud.ntnu.no.krisefikser.dto.EventResponse;
import stud.ntnu.no.krisefikser.dto.mappers.EventMapper;
import stud.ntnu.no.krisefikser.entities.Event;
import stud.ntnu.no.krisefikser.entities.EventType;
import stud.ntnu.no.krisefikser.exception.CustomErrorMessage;
import stud.ntnu.no.krisefikser.exception.customExceptions.AppEntityNotFoundException;
import stud.ntnu.no.krisefikser.exception.customExceptions.InvalidGeoJsonException;
import stud.ntnu.no.krisefikser.repository.EventRepository;
import stud.ntnu.no.krisefikser.repository.EventTypeRepository;

/**
 * Service class for managing events.
 *
 * <p>
 * This class is responsible for handling event-related operations,
 * such as creating, updating, and deleting events.
 * It may also include methods for retrieving events based on various criteria.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class EventService {

  private static final Logger logger = LogManager.getLogger(EventService.class);
  private final EventRepository eventRepository;
  private final EventTypeRepository eventTypeRepository;
  private final EventMapper eventMapper;

  /**
   * Adds a new event to the system.
   *
   * <p>
   * This method takes an {@link EventRequest} object as input,
   * validates it somewhat (e.g., checking if the event type exists), and saves it.
   * </p>
   *
   * @param request the {@link EventRequest} object containing event details
   * @return the created {@link EventResponse} object
   */
  @Transactional
  public EventResponse addEvent(EventRequest request) {
    logger.info("Adding new event with title: {}", request.getTitle());

    EventType eventType = eventTypeRepository.findById(request.getEventTypeId())
        .orElseThrow(() -> new AppEntityNotFoundException(CustomErrorMessage.EVENT_TYPE_NOT_FOUND));

    Event event = eventMapper.toEntity(request, eventType);

    event = eventRepository.save(event);

    logger.info("Event added with ID: {}", event.getId());
    return eventMapper.toDto(event);
  }

  /**
   * Updates an existing event in the system.
   *
   * <p>
   * Updates the attributes of an event based on the provided {@link EventRequest} object.
   * This method validates the event ID and checks if the event type exists.
   * </p>
   *
   * @param eventId the ID of the event to be updated
   * @param request the {@link EventRequest} object containing updated event details
   * @return the updated {@link EventResponse} object
   */
  @Transactional
  public EventResponse updateEvent(Long eventId, EventRequest request) {
    logger.info("Updating event with ID: {}", eventId);

    Event event = eventRepository.findById(eventId)
        .orElseThrow(() -> new AppEntityNotFoundException(CustomErrorMessage.EVENT_NOT_FOUND));

    EventType eventType = eventTypeRepository.findById(request.getEventTypeId())
        .orElseThrow(() -> new AppEntityNotFoundException(CustomErrorMessage.EVENT_TYPE_NOT_FOUND));

    event.setTitle(request.getTitle())
    .setDescription(request.getDescription()).setSeverity(request.getSeverity()).setType(eventType);

    try {
      GeoJsonReader reader = new GeoJsonReader();
      Geometry geometry = reader.read(request.getGeometryGeoJson());
      geometry.setSRID(4326);
      event.setGeometry(geometry);
    } catch (Exception e) {
      logger.error("Invalid GeoJSON format: {}", request.getGeometryGeoJson(), e);
      throw new InvalidGeoJsonException(CustomErrorMessage.GEOJSON_NOT_VALID);
    }

    event = eventRepository.save(event);

    logger.info("Event updated with ID: {}", event.getId());
    return eventMapper.toDto(event);
  }

  /**
   * Deletes an event from the system.
   * <p>
   * Deletes an event from the database based on the provided event ID.
   * This method checks if the event exists before attempting to delete it.
   * </p>
   *
   * @param eventId the ID of the event to be deleted
   */
  @Transactional
  public void deleteEvent(Long eventId) {
    logger.info("Deleting event with ID: {}", eventId);

    Event event = eventRepository.findById(eventId)
        .orElseThrow(() -> new AppEntityNotFoundException(CustomErrorMessage.EVENT_NOT_FOUND));

    eventRepository.delete(event);

    logger.info("Event deleted with ID: {}", eventId);
  }
}
