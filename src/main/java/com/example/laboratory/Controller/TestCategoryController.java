package com.example.laboratory.Controller;

import com.example.laboratory.ExceptionHandler.DuplicateEntityException;
import com.example.laboratory.Response.MessageResponse;
import com.example.laboratory.Service.TestCategoryService;
import com.example.laboratory.models.DTO.TestCategoriesDTO;
import com.example.laboratory.models.TestCategories;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hibernate.Hibernate.map;

@CrossOrigin(origins = "*", maxAge = 3600) // allows for a specific domains to make request for a limited time
@RestController
@RequestMapping("/api")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class TestCategoryController {
    private static final Logger logger = LoggerFactory.getLogger(TestCategoryController.class);

    private final TestCategoryService testCategoryService;

    @Autowired
    public TestCategoryController(TestCategoryService testCategoryService) {
        this.testCategoryService = testCategoryService;
    }

    // 1. Add a new Category
    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@Valid @RequestBody TestCategoriesDTO categoryDTO) {
        logger.info("Received request to add category: {}", categoryDTO.getCategory());
        try {
            TestCategories newCategory = testCategoryService.addCategory(categoryDTO.getCategory());
            return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
        } catch (DuplicateEntityException e) {
            logger.error("Category addition failed: {}", categoryDTO.getCategory());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(e.getMessage()));
        }
    }

    // 2. Get all categories
    @GetMapping("/test-categories")
    public ResponseEntity<List<TestCategoriesDTO>> getAllCategories() {
        logger.info("Received request to retrieve all categories");
        List<TestCategoriesDTO> categories = testCategoryService.getAllCategories().stream()
                .map(category -> new TestCategoriesDTO(category.getId(), category.getCategory()))
                .collect(Collectors.toList());
        if (categories.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categories);
    }

    // 2. Delete a Category by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        logger.info("Received request to delete category with id: {}", id);
        try {
            testCategoryService.deleteCategory(id);
            logger.info("Category with id: {} is deleted successfully", id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new MessageResponse("Category has been deleted successfully")); // 204 No Content on successful deletion
        } catch (EntityNotFoundException e) {
            logger.error("Category not found with id: {}", id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse(e.getMessage()));
        } catch (Exception e) {
            logger.error("Error deleting category with id: " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Error deleting category"));
        }
    }


    // 3. Edit a Category by ID
    @PutMapping("/{id}")
    public ResponseEntity<?> editCategory(@PathVariable Long id, @RequestBody TestCategoriesDTO categoryDTO) {
        logger.info("Received request to edit category with id: {} to name: {}", id, categoryDTO.getCategory());

        // Validate the input
        if (categoryDTO.getCategory() == null || categoryDTO.getCategory().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Category name cannot be null or empty"));
        }

        try {
            TestCategories updatedCategory = testCategoryService.editCategory(id, categoryDTO.getCategory());
            return ResponseEntity.ok(updatedCategory);
        } catch (EntityNotFoundException e) {
            logger.error("Error editing category with id: {}", id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(e.getMessage()));
        } catch (Exception e) {
            logger.error("Unexpected error editing category with id: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Unexpected error occurred"));
        }
    }


    // 4. Find a Category by Name (Ignoring Case)
    @GetMapping("/search")
    public ResponseEntity<?> getCategoryByName(@RequestParam String name) {
//        logger.info(name);
        String cleanedName = name.trim();
        cleanedName = cleanedName.replaceAll("[^a-zA-Z0-9\\\\s]", ""); // Remove quotes and backslashes
        logger.info("Received request to search category by name: {}", cleanedName);
        List<TestCategories> categories = testCategoryService.getCategoriesByName(cleanedName);

        if (categories.isEmpty()) {
            logger.warn("No categories found by name: {}", cleanedName);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("No categories found with name: " + cleanedName));
        } else {
            logger.info("Found {} categories with name: {}", categories.size(), cleanedName);
            return ResponseEntity.ok(categories); // Return 200 OK with the list of categories
        }
    }
}
