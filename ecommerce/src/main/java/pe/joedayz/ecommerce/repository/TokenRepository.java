package pe.joedayz.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.joedayz.ecommerce.model.AuthenticationToken;
import pe.joedayz.ecommerce.model.User;

@Repository
public interface TokenRepository  extends JpaRepository<AuthenticationToken, Integer> {

    AuthenticationToken findTokenByUser(User user);
    AuthenticationToken findTokenByToken(String token);
}
