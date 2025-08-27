package pe.joedayz.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.joedayz.ecommerce.model.OrderItem;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItem,Integer> {
}