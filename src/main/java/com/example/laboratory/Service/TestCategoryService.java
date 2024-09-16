package com.example.laboratory.Service;

import com.example.laboratory.ExceptionHandler.DataRetrievalException;
import com.example.laboratory.ExceptionHandler.DuplicateEntityException;
import com.example.laboratory.Repository.TestCategoryRepository;
import com.example.laboratory.Response.MessageResponse;
import com.example.laboratory.models.TestCategories;
import com.example.laboratory.models.Tests;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestCategoryService {

    private static final Logger logger = LoggerFactory.getLogger(TestCategoryService.class);

    private final TestCategoryRepository testCategoryRepository;

    public TestCategoryService(TestCategoryRepository testCategoryRepository) {
        this.testCategoryRepository = testCategoryRepository;
    }

    // Add a new category
    public TestCategories addCategory(String categoryName) {
        logger.info("Adding category: {}", categoryName);

        if (categoryName == null || categoryName.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
        TestCategories category = new TestCategories();
        category.setCategory(categoryName);
        try {
            return testCategoryRepository.save(category);
        } catch (DataIntegrityViolationException e) {
            // Handle the unique constraint violation
            logger.error("Unique constraint violation while adding category: " + categoryName, e);
            throw new DuplicateEntityException("Category with the same name already exists: " + categoryName);

        } catch (Exception e) {
            logger.error("Error adding category: {}", e.getMessage(), e);
            throw new DuplicateEntityException("Error adding category" + e.getMessage());
        }
    }

    public List<TestCategories> getAllCategories() {
        logger.info("Retrieving all Categories");
        try {
            List<TestCategories> categories = testCategoryRepository.findAll();
            if (categories.isEmpty()) {
                logger.warn("No categories found");
            }
            return categories;
        } catch (DataAccessException e) {
            logger.error("Error retrieving categories from database", e);
            throw new DataRetrievalException("Error retrieving categories");
        }
    }

    public List<TestCategories> getCategoriesByName(String categoryName) {
        logger.info("Searching for category by name: {}", categoryName);
        return testCategoryRepository.findByCategoryContainingIgnoreCase(categoryName);
    }

    @Transactional
    public void deleteCategory(Long id) {
        logger.info("Deleting category with id: {}", id);
        TestCategories category = testCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));

        try {
            testCategoryRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error occurred while deleting category with id: {}", id, e);
            throw new RuntimeException("Error occurred while deleting category with id: " + id, e);
        }
    }

    public TestCategories editCategory(Long id, String newCategoryName) {
        logger.info("Editing category with id: {} to new name: {}", id, newCategoryName);

        // Validate input
        if (newCategoryName == null || newCategoryName.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }

        TestCategories category = testCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category with id " + id + " not found"));

        // Update category
        category.setCategory(newCategoryName);

        try {
            return testCategoryRepository.save(category);
        } catch (DataIntegrityViolationException e) {
            logger.error("Error saving updated category with id: {}", id, e);
            throw new RuntimeException("Error saving updated category", e);
        }
    }
}
