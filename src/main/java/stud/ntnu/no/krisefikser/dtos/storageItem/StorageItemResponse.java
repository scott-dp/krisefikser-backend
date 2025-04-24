package stud.ntnu.no.krisefikser.dtos.storageItem;

import java.util.Date;
import lombok.*;

/**
 * DTO for representing a storage item response with details such as quantity,
 * expiration date, and recommended usage per person.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StorageItemResponse {

  /**
   * The unique identifier of the storage item.
   */
  private Long id;

  /**
   * The quantity of the storage item.
   */
  private double quantity;

  /**
   * The expiration date of the storage item.
   */
  private Date expirationDate;

  /** 
   * The date the item was added to storage. 
   */
  private Date dateAdded;

  /** 
   * The name of the storage item. 
   */
  private String name;

  /** 
   * The unit of measurement for the item (e.g., "kg", "liter"). 
   */
  private String unit;

  /** 
   * The recommended amount of the item per person. 
   */
  private double recommendedAmountPerPerson;

  /**
   * The Category id
   */
  private Long categoryId;
}
