package stud.ntnu.no.krisefikser.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * Entity representing a real-world event, such as a natural disaster or security incident.
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "EVENT")
public class Event {

  /**
   * Unique identifier for the event.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private long id;

  /**
   * Title of the event. For example, "Earthquake in Oslo".
   */
  @Column(name = "TITLE", nullable = false)
  private String title;

  /**
   * Description of the event. For example, "An earthquake with a magnitude of 5.0 occurred in Oslo".
   */
  @Column(name = "DESCRIPTION", nullable = false)
  private String description;

  /**
   * Severity of the event. For example, "Safe", "Warning", "Danger".
   */
  @Column(name = "SEVERITY", nullable = false)
  @Enumerated(EnumType.STRING)
  private EventSeverity severity;

  /**
   * Date and time when the event was created.
   */
  @Column(name = "CREATED_AT", nullable = false)
  private Date createdAt;

  /**
   * Date and time when the event was last updated.
   */
  @Column(name = "UPDATED_AT", nullable = false)
  private Date updatedAt;

  /**
   * GeoJSON representation of the event's geometry.
   */
  @Lob
  @Column(name = "GEOMETRY_GEOJSON", columnDefinition = "LONGTEXT")
  private String geometryGeoJson;

  /**
   * The type of the event. For example, "Natural Disaster", "Aerial Assault", etc.
   */
  @ManyToOne(optional = false)
  @JoinColumn(name = "EVENT_TYPE_ID", referencedColumnName = "ID", nullable = false)
  private EventType type;
}
