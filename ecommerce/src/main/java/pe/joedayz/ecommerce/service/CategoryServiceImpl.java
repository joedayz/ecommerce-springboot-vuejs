package pe.joedayz.ecommerce.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.joedayz.ecommerce.model.Category;
import pe.joedayz.ecommerce.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  @Override
  public Category readCategory(String categoryName) {
    return categoryRepository.findByCategoryName(categoryName);
  }

  @Override
  public Optional<Category> readCategory(Integer categoryID){
    return categoryRepository.findById(categoryID);
  }

  @Override
  public void createCategory(Category category) {
    categoryRepository.save(category);
  }

  @Override
  public List<Category> listCategories() {
    return categoryRepository.findAll();
  }

  @Override
  public void updateCategory(Integer categoryID, Category newCategory) {
    Category category = categoryRepository.findById(categoryID).get();
    category.setCategoryName(newCategory.getCategoryName());
    category.setDescription(newCategory.getDescription());
    category.setImageUrl(newCategory.getImageUrl());
    categoryRepository.save(category);
  }
}
