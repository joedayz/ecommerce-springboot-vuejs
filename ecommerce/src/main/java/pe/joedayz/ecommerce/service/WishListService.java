package pe.joedayz.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.joedayz.ecommerce.model.User;
import pe.joedayz.ecommerce.model.WishList;
import pe.joedayz.ecommerce.repository.WishListRepository;

import java.util.List;

@Service

public class WishListService {
    @Autowired
    private WishListRepository wishListRepository;

    public void createWishlist(WishList wishList) {
        wishListRepository.save(wishList);
    }

    public List<WishList> readWishList(User user) {
        return wishListRepository.findAllByUserOrderByCreatedDateDesc(user);
    }
}
