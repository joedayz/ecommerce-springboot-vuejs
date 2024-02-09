package pe.joedayz.ecommerce.service;

import pe.joedayz.ecommerce.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {  // CTrl + B | Cmd + B
    Category readCategory(String categoryName);

    Optional<Category> readCategory(Integer categoryID);

    void createCategory(Category category);

    List<Category> listCategories();

    void updateCategory(Integer categoryID, Category newCategory);
}
