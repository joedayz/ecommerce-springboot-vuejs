package pe.joedayz.ecommerce.controllers;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
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

  @GetMapping("/")
  public ResponseEntity<List<ProductDto>> getProducts(){
     List<ProductDto> productDtos = productService.listProducts();
     return new ResponseEntity<>(productDtos, HttpStatus.OK);
  }

  @PostMapping("/update/{productID}")
  public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productID") Integer productID,
                                     @RequestBody @Valid ProductDto productDto){
    Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
    if(!optionalCategory.isPresent()){
      return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category is invalid"),
          HttpStatus.CONFLICT);
    }
    Category category = optionalCategory.get();
    productService.updateProduct(productID, productDto, category);
    return new ResponseEntity<>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);

  }


}
