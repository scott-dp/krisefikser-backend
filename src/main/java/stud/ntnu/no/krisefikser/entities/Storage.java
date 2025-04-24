package stud.ntnu.no.krisefikser.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Represents a storage owned by either a household (shared) or a specific user (personal).
 * <p>
 * If the user is null, the storage is considered shared across the household.
 * </p>
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "storage")
public class Storage {

    /**
     * Unique identifier for the storage.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The household that owns this storage.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "household_id", nullable = false)
    private Household household;

    /**
     * The user that owns this storage. Null if it's a shared household storage.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Name of the storage.
     */
    @Column(nullable = false)
    private String name;

    /**
     * The list of storage items contained in this storage.
     */
    @OneToMany(mappedBy = "storage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StorageItem> storageItems;
}
