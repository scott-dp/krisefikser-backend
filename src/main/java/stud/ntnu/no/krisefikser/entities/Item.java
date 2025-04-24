package stud.ntnu.no.krisefikser.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * Entity representing a predefined item in the system.
 * <p>
 * Items have a name, belong to a category, and define units and recommended
 * quantities.
 * </p>
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "item")
public class Item {

  /**
   * Unique identifier for the item.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Name of the item.
   */
  @Column(nullable = false)
  private String name;

  /**
   * The category this item belongs to.
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", nullable = false)
  private ItemCategory category;

  /**
   * The unit this item is measured in (e.g., liters, kilograms).
   */
  @Column(nullable = false)
  private String unit;

  /**
   * Recommended quantity per person.
   */
  @Column(name = "recommended_amount_per_person", nullable = false)
  private Double recommendedAmountPerPerson;
}
