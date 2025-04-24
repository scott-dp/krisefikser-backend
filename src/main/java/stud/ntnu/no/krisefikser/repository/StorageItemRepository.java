package stud.ntnu.no.krisefikser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import stud.ntnu.no.krisefikser.entities.StorageItem;

/**
 * Repository interface for managing StorageItem entities.
 * <p>
 * This interface extends JpaRepository to provide CRUD operations.
 * </p>
 */
@Repository
public interface StorageItemRepository extends JpaRepository<StorageItem, Long> {

}
