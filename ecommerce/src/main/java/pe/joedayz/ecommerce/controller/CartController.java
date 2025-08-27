package pe.joedayz.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.joedayz.ecommerce.common.ApiResponse;
import pe.joedayz.ecommerce.dto.cart.AddToCartDto;
import pe.joedayz.ecommerce.dto.cart.CartDto;
import pe.joedayz.ecommerce.exceptions.AuthenticationFailException;
import pe.joedayz.ecommerce.exceptions.ProductNotExistException;
import pe.joedayz.ecommerce.model.Product;
import pe.joedayz.ecommerce.model.User;
import pe.joedayz.ecommerce.service.AuthenticationService;
import pe.joedayz.ecommerce.service.CartService;
import pe.joedayz.ecommerce.service.ProductService;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    ProductService productService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto, @RequestParam("token") String token)
            throws ProductNotExistException, AuthenticationFailException {
        // first authenticate the token
        authenticationService.authenticate(token);

        // get the user
        User user = authenticationService.getUser(token);

        // find the product to add and add item by service
        Product product = productService.getProductById(addToCartDto.getProductId());
        cartService.addToCart(addToCartDto, product, user);

        // return response
        return new ResponseEntity<>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);

    }

    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token) throws AuthenticationFailException {
        // first authenticate the token
        authenticationService.authenticate(token);

        // get the user
        User user = authenticationService.getUser(token);

        // get items in the cart for the user.
        CartDto cartDto = cartService.listCartItems(user);

        return new ResponseEntity<CartDto>(cartDto,HttpStatus.OK);
    }

}
