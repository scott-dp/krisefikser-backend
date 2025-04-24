package stud.ntnu.no.krisefikser.dtos.mappers;

import org.springframework.stereotype.Component;
import stud.ntnu.no.krisefikser.dtos.storageItem.StorageItemResponse;
import stud.ntnu.no.krisefikser.entities.StorageItem;
import stud.ntnu.no.krisefikser.entities.Item;

/**
 * Mapper class for converting {@link StorageItem} entities to {@link StorageItemResponse} DTOs.
 * <p>
 * This class provides a method to convert a {@link StorageItem} entity to a {@link StorageItemResponse} DTO.
 * </p>
 */
@Component
public class StorageItemMapper {

  /**
   * Converts a StorageItem entity to a StorageItemResponse DTO.
   * 
   * @param storageItem the entity to be converted
   * @return the corresponding StorageItemResponse DTO
   */
  public StorageItemResponse toDto(StorageItem storageItem) {
    if (storageItem == null) {
      return null;
    }

    StorageItemResponse response = new StorageItemResponse();
    response.setId(storageItem.getId());
    response.setQuantity(storageItem.getQuantity());
    response.setExpirationDate(storageItem.getExpirationDate());
    response.setDateAdded(storageItem.getAddedDate());

    Item item = storageItem.getItem();
    if (item != null) {
      response.setName(item.getName());
      response.setUnit(item.getUnit());
      response.setRecommendedAmountPerPerson(item.getRecommendedAmountPerPerson());
      response.setCategoryId(item.getCategory().getId());
    }
    return response;
  }
}
