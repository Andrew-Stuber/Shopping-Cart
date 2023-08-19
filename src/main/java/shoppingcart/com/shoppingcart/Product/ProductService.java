package shoppingcart.com.shoppingcart.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    public Product getProduct(Long id) { return productRepository.findById(id).orElse(null); }

    public Product createProduct(Product product) {
        if(productRepository.findByName(product.getName()).isPresent()) {
            throw new IllegalStateException(product.getName() + " already exists.");
        } else {
            return productRepository.save(product);
        }
    }

    @Transactional
    public Product updateProducts(Long id, String name, Double price, Integer amount) {
        Product getProduct = productRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("Product with id " + id + " does not exist."));

        if(name != null) {
            if(productRepository.findByName(name).isPresent()) {
                throw new IllegalStateException(name + " already exists.");
            } else {
                getProduct.setName(name);
            }
        }
        if(price != null) {
            getProduct.setPrice(price);
        }
        if(amount != null) {
            getProduct.setAmount(amount);
        }

        return productRepository.save(getProduct);
    }

    public void deleteProductsById(Long id) {
        productRepository.deleteById(id);
    }

    public void deleteProductsByName(Product product) {
        productRepository.delete(product);
    }

    public boolean productExistsById(Long id) {
        return productRepository.findById(id).isPresent();
    }

    public boolean productExistsByName(String product) {
        return productRepository.findByName(product).isPresent();
    }

    public Product increaseAmount(Long id, int increaseAmount) {
        Product getProduct = productRepository.findById(id).orElseThrow(() -> new IllegalStateException("Product does not exist."));
        int currentAmount = getProduct.getAmount();
        getProduct.setAmount(currentAmount + increaseAmount);
        return productRepository.save(getProduct);
    }

    public Product decreaseAmount(Long id, int decreaseAmount) {
        Product getProduct = productRepository.findById(id).orElseThrow(() -> new IllegalStateException("Product does not exist."));
        int currentAmount = getProduct.getAmount();
        getProduct.setAmount(currentAmount - decreaseAmount);
        return productRepository.save(getProduct);
    }
    
}
