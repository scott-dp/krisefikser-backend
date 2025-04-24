package stud.ntnu.no.krisefikser.repository;

import java.util.List;

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

  /**
   * Finds all storage items for a given storage and item category.
   *
   * @param storageId  the ID of the storage
   * @param categoryId the ID of the item category
   * @return a list of StorageItem entities matching the criteria
   */
  List<StorageItem> findByStorageAndCategory(Long storageId, Long categoryId);
}
