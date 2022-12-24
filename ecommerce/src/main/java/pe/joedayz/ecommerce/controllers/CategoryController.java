package pe.joedayz.ecommerce.controllers;


import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.joedayz.ecommerce.common.ApiResponse;
import pe.joedayz.ecommerce.model.Category;
import pe.joedayz.ecommerce.service.CategoryService;
import pe.joedayz.ecommerce.service.ProductService;

@RestController
@RequestMapping("/category")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @PostMapping("/create")
  public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody Category category){
    if(Objects.nonNull(categoryService.readCategory(category.getCategoryName()))){
      return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category already exists"),
          HttpStatus.CONFLICT);
    }
    categoryService.createCategory(category);
    return new ResponseEntity<>(new ApiResponse(true, "created the category"), HttpStatus.CREATED);
  }

  @GetMapping("/")
  public ResponseEntity<List<Category>> getCategories(){
    List<Category> body = categoryService.listCategories();
    return new ResponseEntity<>(body, HttpStatus.OK);
  }

  @PostMapping("/update/{categoryID}")
  public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryID") Integer categoryID,
                                                    @Valid @RequestBody Category category){
    if(Objects.nonNull(categoryService.readCategory(categoryID))){
      categoryService.updateCategory(categoryID, category);
      return new ResponseEntity<ApiResponse>(new ApiResponse(true, "updated the category"), HttpStatus.OK);
    }

    return new ResponseEntity<>(new ApiResponse(false, "category does not exist"), HttpStatus.NOT_FOUND);
  }
}
