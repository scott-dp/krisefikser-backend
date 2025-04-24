package stud.ntnu.no.krisefikser.dtos.itemCategory;

import lombok.*;

/**
 * DTO for returning category details.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemCategoryResponse {

  /**
   * Unique identifier for the category.
   */
  private Long id;

  /**
   * Name of the category.
   */
  private String name;
}
