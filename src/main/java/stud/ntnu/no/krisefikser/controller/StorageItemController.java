package stud.ntnu.no.krisefikser.controller;

import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import stud.ntnu.no.krisefikser.dtos.storageItem.StorageItemResponse;
import stud.ntnu.no.krisefikser.service.StorageItemService;

import java.util.List;

/**
 * Controller for handling storage item related operations.
 * <p>
 * Provides endpoints for managing storage items in the system.
 * </p>
 */
@RestController
@RequestMapping("/api/storageitems")
@RequiredArgsConstructor
@Tag(name = "Storage Items", description = "Endpoints for managing storage items")
public class StorageItemController {

  private final StorageItemService storageItemService;

  /**
   * Retrieves all storage items for a given Storage Id and ItemCategory Id and
   * maps them to StorageItemResponse DTOs.
   * 
   * @param storageId  the ID of the storage
   * @param categoryId the ID of the item category
   * @return a list of StorageItemResponse DTOs
   */
  @GetMapping("/{storageId}/{categoryId}")
  public List<StorageItemResponse> getStorageItemsByStorageAndCategory(@PathVariable Long storageId,
      @PathVariable Long categoryId) {
    return storageItemService.getStorageItemsByStorageAndCategory(storageId, categoryId);
  }

}
