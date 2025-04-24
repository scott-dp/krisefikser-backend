package stud.ntnu.no.krisefikser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import stud.ntnu.no.krisefikser.entities.Storage;


/**
 * Repository interface for managing Storage entities.
 * <p>
 * This interface extends JpaRepository to provide CRUD operations.
 * </p>
 */
@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {
 
}