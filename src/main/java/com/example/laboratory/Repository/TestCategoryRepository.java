package com.example.laboratory.Repository;

import com.example.laboratory.models.TestCategories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TestCategoryRepository extends JpaRepository<TestCategories, String> {


    @Override
    List<TestCategories> findAll();

//    @Override
    Optional<TestCategories> findById(Long id);

    //    @Override
    List<TestCategories> findByCategoryContainingIgnoreCase(String categoryName);

    TestCategories save(TestCategories testCategories);

    void deleteById(Long id);

    Optional<Object> findByCategoryIgnoreCase(String categoryName);
}
