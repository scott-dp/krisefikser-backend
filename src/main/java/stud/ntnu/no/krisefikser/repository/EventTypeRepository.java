package stud.ntnu.no.krisefikser.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import stud.ntnu.no.krisefikser.entities.EventType;

/**
 * Repository interface for managing {@link EventType} entities.
 * <p>
 * Provides CRUD operations and custom queries for event types.
 * </p>
 */
public interface EventTypeRepository extends JpaRepository<EventType, Long> {

  /**
   * Find all event types by their ID.
   *
   * @param id the ID of the event type
   * @return a list of event types with the specified ID
   */
  List<EventType> findEventTypeById(Long id);

  /**
   * Find all event types by their name.
   *
   * @param name the name of the event type
   * @return a list of event types with the specified name
   */
  List<EventType> findEventTypeByName(String name);

}
