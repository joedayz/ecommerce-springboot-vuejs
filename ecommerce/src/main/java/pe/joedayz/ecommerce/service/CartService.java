package pe.joedayz.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.joedayz.ecommerce.dto.cart.AddToCartDto;
import pe.joedayz.ecommerce.dto.cart.CartDto;
import pe.joedayz.ecommerce.dto.cart.CartItemDto;
import pe.joedayz.ecommerce.model.Cart;
import pe.joedayz.ecommerce.model.Product;
import pe.joedayz.ecommerce.model.User;
import pe.joedayz.ecommerce.repository.CartRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    public void addToCart(AddToCartDto addToCartDto, Product product, User user) {
        Cart cart = new Cart(product, addToCartDto.getQuantity(), user);
        cartRepository.save(cart);
    }

    public CartDto listCartItems(User user) {
        // first get all the cart items for user
        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);

        // convert cart to cartItemDto
        List<CartItemDto> cartItems = new ArrayList<>();
        for (Cart cart:cartList){
            CartItemDto cartItemDto = new CartItemDto(cart);
            cartItems.add(cartItemDto);
        }

        // calculate the total price
        double totalCost = 0;
        for (CartItemDto cartItemDto :cartItems){
            totalCost += cartItemDto.getProduct().getPrice() * cartItemDto.getQuantity();
        }

        // return cart DTO
        return new CartDto(cartItems,totalCost);
    }

    public void deleteUserCartItems(User user) {
        cartRepository.deleteByUser(user);
    }
}