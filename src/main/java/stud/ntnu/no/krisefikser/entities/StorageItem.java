package stud.ntnu.no.krisefikser.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

/**
 * Entity representing an item stored by a household or user.
 * <p>
 * Each storage item references a predefined item and includes quantity,
 * expiration date, and metadata about its ownership.
 * </p>
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "storage_item")
public class StorageItem {

  /**
   * Unique identifier for the storage item.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * The item this storage record refers to.
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id", nullable = false)
  private Item item;

  /**
   * The household that owns this storage item.
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "household_id")
  private Household household;

  /**
   * The user that added or is associated with this item (optional).
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  /**
   * Quantity of the item stored.
   */
  @Column(nullable = false)
  private double quantity;

  /**
   * Expiration date of the stored item.
   */
  @Temporal(TemporalType.DATE)
  private Date expirationDate;

  /**
   * Timestamp of when the item was added to storage.
   */
  @CreationTimestamp
  @Column(name = "added_date", updatable = false)
  private Date addedDate;
}
