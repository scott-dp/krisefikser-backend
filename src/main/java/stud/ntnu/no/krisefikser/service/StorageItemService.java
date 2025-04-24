package stud.ntnu.no.krisefikser.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import stud.ntnu.no.krisefikser.dtos.storageItem.StorageItemResponse;
import stud.ntnu.no.krisefikser.entities.StorageItem;
import stud.ntnu.no.krisefikser.dtos.mappers.StorageItemMapper;
import stud.ntnu.no.krisefikser.repository.StorageItemRepository;

/**
 * Service class for managing storage items.
 * <p>
 * This class provides methods to retrieve storage items.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class StorageItemService {

  private final StorageItemMapper storageItemMapper;
  private final StorageItemRepository storageItemRepository;

  /**
   * Retrieves all storage items for a given Storage Id and ItemCategory Id and
   * maps them to StorageItemResponse DTOs.
   * 
   * @param storageId  the ID of the storage
   * @param categoryId the ID of the item category
   * @return a list of StorageItemResponse DTOs
   */
  public List<StorageItemResponse> getStorageItemsByStorageAndCategory(Long storageId, Long categoryId) {
    List<StorageItem> storageItems = storageItemRepository.findByStorageAndCategory(storageId, categoryId);
    
    return storageItems.stream()
        .map(storageItemMapper::toDto)
        .toList();
  }

}
