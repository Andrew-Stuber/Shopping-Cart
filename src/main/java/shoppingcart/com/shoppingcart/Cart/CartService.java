package shoppingcart.com.shoppingcart.Cart;

import shoppingcart.com.shoppingcart.Product.Product;
import shoppingcart.com.shoppingcart.Product.ProductRepository;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Cart createCart() {
        return cartRepository.save(new Cart());
    }

    public List<Product> ListItems(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new IllegalStateException("Cart does not exist."));
        return cart.listProducts();
    }

    public void addToCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new IllegalStateException("Cart does not exist."));
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalStateException("Product does not exist."));
        
        if(product.getAmount() > 0) {
            cart.getCart().add(new Product(product.getName(), product.getPrice(), 1));
        }

        cart.getCart().add(product);
        cartRepository.save(cart);
    }

    public void removeItemById(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new IllegalStateException("Cart does not exist."));

        for(Product i : cart.getCart()) {
            if(i.getId() == productId) {
                cart.getCart().remove(i);
            }
        }
    }

    public void removeAllItems(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new IllegalStateException("Cart does not exist."));

        cart.getCart().removeAll(cart.getCart());
        cartRepository.save(cart);
    }

    public Double totalPrice(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new IllegalStateException("Cart does not exist."));
        Double total = 0.0;

        for(Product i : cart.getCart()) {
            total += i.getPrice();
        }

        return total;
    }


}
