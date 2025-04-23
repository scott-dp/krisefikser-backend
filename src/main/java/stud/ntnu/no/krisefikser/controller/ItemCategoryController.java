package stud.ntnu.no.krisefikser.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import stud.ntnu.no.krisefikser.dtos.itemCategory.ItemCategoryResponse;
import stud.ntnu.no.krisefikser.service.ItemCategoryService;

// TODO: Add tests and logging

/**
 * Controller for handling itemCategory related operations.
 * <p>
 * Provides endpoints for managing item categories in the system.
 * </p>
 */
@RestController
@RequestMapping("/api/itemcategories")
@RequiredArgsConstructor
public class ItemCategoryController {

  private final ItemCategoryService itemCategoryService;

  @GetMapping
  public List<ItemCategoryResponse> getAllItemCategories() {
    return itemCategoryService.getAllItemCategories();
  }
}
