package stud.ntnu.no.krisefikser.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

/**
 * Entity representing a quantity of a specific item stored in a storage.
 * <p>
 * This is a join between a storage and an item, with metadata such as
 * quantity, expiration date, and date added.
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
     * The storage this item belongs to.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storage_id", nullable = false)
    private Storage storage;

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
