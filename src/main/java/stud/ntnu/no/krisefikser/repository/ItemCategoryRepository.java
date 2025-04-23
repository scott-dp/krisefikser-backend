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

}
