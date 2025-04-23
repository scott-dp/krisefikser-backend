package stud.ntnu.no.krisefikser.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a household in the system.
 * <p>
 * Each household has a unique identifier, a name, and a list of users
 * associated with it.
 * </p>
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "household")
public class Household {

  /**
   * Unique identifier for the household.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long householdId;

  /**
   * Name of the household.
   */
  @Column(nullable = false)
  private String name;

  /**
   * List of users associated with the household.
   */
  @OneToMany(mappedBy = "household", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<User> users = new ArrayList<>();
}
