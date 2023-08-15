package shoppingcart.com.shoppingcart.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProducts(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.ok(createdProduct);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProducts(@PathVariable Long id,
                                                  @RequestParam(required = false) String name,
                                                  @RequestParam(required = false) Double price,
                                                  @RequestParam(required = false) Integer amount) {
        if(productService.productExistsById(id)) {
            Product updatedProduct = productService.updateProducts(id, name, price, amount);
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<String> deleteProductsById(@PathVariable Long id) {
        if(productService.productExistsById(id)) {
            productService.deleteProductsById(id);
            return ResponseEntity.ok("Product Deleted Successfully.");
        } else {
            return ResponseEntity.ok("Product Not Found.");
        }
    }

    @DeleteMapping("/delete/name/{name}")
    public ResponseEntity<String> deleteProductsByName(@PathVariable Product name) {
        if(productService.productExistsByName(name.getName())) {
            productService.deleteProductsByName(name);
            return ResponseEntity.ok("Product Deleted Successfully.");
        } else {
            return ResponseEntity.ok("Product Not Found.");
        }
    }

    @PutMapping("/increaseAmount/{id}/{amount}")
    public ResponseEntity<Product> increaseAmount(@PathVariable Long id, @PathVariable int amount) {
        if(productService.productExistsById(id)) {
            Product increasedAmount = productService.increaseAmount(id, amount);
            return ResponseEntity.ok(increasedAmount);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/decreaseAmount/{id}/{amount}")
    public ResponseEntity<Object> decreaseAmount(@PathVariable Long id, @PathVariable int amount) {
        if(productService.productExistsById(id)) {

            Product getProduct = productService.getProduct(id);
            if(getProduct.getAmount() == 0) {
                return ResponseEntity.badRequest().body(getProduct.getName() + " is empty.");
            }

            if(amount > getProduct.getAmount()) {
                amount = getProduct.getAmount();
            }

            Product decreasedAmount = productService.increaseAmount(id, -1*amount);
            return ResponseEntity.ok(decreasedAmount);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
