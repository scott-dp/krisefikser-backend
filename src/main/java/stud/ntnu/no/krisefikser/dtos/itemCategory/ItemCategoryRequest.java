package stud.ntnu.no.krisefikser.dtos.itemCategory;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * DTO for creating item categories.
 * <p>
 * This class contains the name of the item category.
 * </p>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemCategoryRequest {

  /**
   * Name of the item category.
   */
  @NotBlank(message = "name cannot be blank")
  private String name;
}
