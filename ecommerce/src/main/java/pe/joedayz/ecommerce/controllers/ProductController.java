package pe.joedayz.ecommerce.controllers;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.joedayz.ecommerce.common.ApiResponse;
import pe.joedayz.ecommerce.dto.product.ProductDto;
import pe.joedayz.ecommerce.model.Category;
import pe.joedayz.ecommerce.service.CategoryService;
import pe.joedayz.ecommerce.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

  @Autowired
  ProductService productService;
  @Autowired
  CategoryService categoryService;

  @PostMapping("/add")
  public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDto productDto){
    Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
    if(!optionalCategory.isPresent()){
      return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category is invalid"),
          HttpStatus.CONFLICT);
    }
    Category category = optionalCategory.get();
    productService.addProduct(productDto, category);
    return new ResponseEntity<>(new ApiResponse(true, "Product has been added"), HttpStatus.CREATED);
  }
}
