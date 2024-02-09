package pe.joedayz.ecommerce.dto.product;


import jakarta.validation.constraints.NotNull;
import pe.joedayz.ecommerce.model.Product;

public class ProductDto {

  //comment 2
  private Integer id;
  private @NotNull String name;
  private @NotNull String imageURL;
  private @NotNull double price;
  private @NotNull String description;
  private @NotNull Integer categoryId;

  public ProductDto(String name, String imageURL, double price, String description,
      Integer categoryId) {
    this.name = name;
    this.imageURL = imageURL;
    this.price = price;
    this.description = description;
    this.categoryId = categoryId;
  }

  public ProductDto(Product product) {
    this.setId(product.getId());
    this.setName(product.getName());
    this.setImageURL(product.getImageURL());
    this.setDescription(product.getDescription());
    this.setPrice(product.getPrice());
    this.setCategoryId(product.getCategory().getId());
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImageURL() {
    return imageURL;
  }

  public void setImageURL(String imageURL) {
    this.imageURL = imageURL;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }
}
