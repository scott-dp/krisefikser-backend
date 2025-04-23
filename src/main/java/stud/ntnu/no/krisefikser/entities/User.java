package stud.ntnu.no.krisefikser.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * Entity representing a user in the system.
 * <p>
 * Each user belongs to a household and has credentials for authentication.
 * </p>
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "user")
public class User {

  /**
   * Unique identifier for the user.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  /**
   * Username used for login.
   */
  @Column(nullable = false, unique = true)
  private String username;

  /**
   * Hashed password for authentication.
   */
  @Column(nullable = false)
  private String passwordHash;

  /**
   * The household this user is associated with.
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "household_id")
  private Household household;
}
