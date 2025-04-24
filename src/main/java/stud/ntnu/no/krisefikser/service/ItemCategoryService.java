package stud.ntnu.no.krisefikser.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import stud.ntnu.no.krisefikser.dtos.itemCategory.ItemCategoryRequest;
import stud.ntnu.no.krisefikser.dtos.itemCategory.ItemCategoryResponse;
import stud.ntnu.no.krisefikser.dtos.mappers.ItemCategoryMapper;
import stud.ntnu.no.krisefikser.entities.ItemCategory;
import stud.ntnu.no.krisefikser.repository.ItemCategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing ItemCategory entities.
 * <p>
 * This class provides a method to retrieve all item categories and convert them
 * to DTOs.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class ItemCategoryService {

  private static final Logger logger = LogManager.getLogger(ItemCategoryService.class);
  private final ItemCategoryRepository categoryRepository;

  /**
   * Retrieves all item categories and converts them to DTOs.
   *
   * @return a list of {@link ItemCategoryResponse} DTOs representing all item categories
   */
  public List<ItemCategoryResponse> getAllItemCategories() {
    logger.info("Fetching all item categories from the database");
    List<ItemCategoryResponse> categories = categoryRepository.findAll().stream()
        .map(ItemCategoryMapper::toDto)
        .collect(Collectors.toList());
    logger.info("Retrieved {} item categories", categories.size());
    return categories;
  }

  /**
   * Creates a new item category based on the provided request.
   *
   * @param itemCategoryRequest the request containing the details of the new item category
   * @return the created {@link ItemCategoryResponse} DTO
   */
  public ItemCategoryResponse createCategory(ItemCategoryRequest itemCategoryRequest) {
    logger.info("Creating new item category: {}", itemCategoryRequest.getName());
    ItemCategory itemCategory = ItemCategoryMapper.toEntity(itemCategoryRequest);
    ItemCategory savedItemCategory = categoryRepository.save(itemCategory);
    logger.info("Created new item category with ID: {}", savedItemCategory.getId());
    return ItemCategoryMapper.toDto(savedItemCategory);
  }
}
