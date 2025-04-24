package stud.ntnu.no.krisefikser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import stud.ntnu.no.krisefikser.entities.ItemCategory;

/**
 * Repository interface for managing ItemCategory entities.
 * <p>
 * This interface extends JpaRepository to provide CRUD operations.
 * </p>
 */
@Repository
public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long> {
  
  /**
   * Checks if an item category exists with the given name, ignoring case.
   *
   * @param name the name of the item category to check
   * @return {@code true} if an item category with the given name exists, otherwise {@code false}
   */
  boolean existsByNameIgnoreCase(String name);

}
