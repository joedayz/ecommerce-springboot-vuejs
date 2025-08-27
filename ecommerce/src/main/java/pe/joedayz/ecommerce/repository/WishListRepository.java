package pe.joedayz.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.joedayz.ecommerce.model.User;
import pe.joedayz.ecommerce.model.WishList;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Integer> {

    // find all the products saved to wishlist for a user
    // sort by latest created date
    List<WishList> findAllByUserOrderByCreatedDateDesc(User user);

}
