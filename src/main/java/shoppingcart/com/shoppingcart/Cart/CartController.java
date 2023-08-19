package shoppingcart.com.shoppingcart.Cart;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
@RequestMapping("api/Cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // For testing
    @PostMapping("/create")
    public ResponseEntity<Cart> createCart() {
        Cart cart = cartService.createCart();
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/{cartId}/all")
    public ResponseEntity<List<CartItem>> listItems(@PathVariable Long cartId) {
        return ResponseEntity.ok(cartService.ListItems(cartId));
    }

    @PostMapping("/{cartId}/add/{productId}")
    public ResponseEntity<String> addItem(@PathVariable Long cartId, @PathVariable Long productId) {
        cartService.addToCart(cartId, productId);
        return ResponseEntity.ok("Product with id " + productId + " is added to the cart.");
    }

    @PostMapping("/{cartId}/add/{productId}/{amount}")
    public ResponseEntity<String> addItem(@PathVariable Long cartId, @PathVariable Long productId, @PathVariable int amount) {
        cartService.addToCartByAmount(cartId, productId, amount);
        return ResponseEntity.ok(amount + " product with id " + productId + " is added to the cart.");
    }

    @DeleteMapping("/{cartId}/remove/{productId}/{amount}")
    public ResponseEntity<String> removeItemByAmount(@PathVariable Long cartId, @PathVariable Long productId, @PathVariable int amount) {
        cartService.removeItemByAmount(cartId, productId, amount);
        return ResponseEntity.ok("Successfully removed " + amount + " product with id " + productId + ".");
    }

    @DeleteMapping("/{cartId}/remove/{productId}")
    public ResponseEntity<String> removeItem(@PathVariable Long cartId, @PathVariable Long productId) {
        cartService.removeItemById(cartId, productId);
        return ResponseEntity.ok("Successfully removed product with id " + productId + ".");
    }

    @DeleteMapping("/{cartId}/removeAll")
    public ResponseEntity<String> removeAll(@PathVariable Long cartId) {
        cartService.removeAllItems(cartId);
        return ResponseEntity.ok("Successfully removed all items.");
    }

    @GetMapping("/{cartId}/total")
    public ResponseEntity<Double> totalPrice(@PathVariable Long cartId) {
        Double total = cartService.totalPrice(cartId);
        return ResponseEntity.ok(total);
    }

}
