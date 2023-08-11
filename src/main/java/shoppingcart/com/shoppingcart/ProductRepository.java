package shoppingcart.com.shoppingcart;

import org.springframework.data.repository.CrudRepository;
import java.util.*;

public interface ProductRepository extends CrudRepository<Products, Long> {
    List<Products> findByName(String name);

    Optional<Products> findById(Long Id);

}

