package shoppingcart.com.shoppingcart.Cart;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import shoppingcart.com.shoppingcart.Product.Product;

import java.util.*;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
    Optional<Cart> findById(Long Id);

}
