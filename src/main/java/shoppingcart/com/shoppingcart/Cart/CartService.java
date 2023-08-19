package shoppingcart.com.shoppingcart.Cart;

import org.springframework.transaction.annotation.Transactional;
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

    public List<CartItem> ListItems(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new IllegalStateException("Cart does not exist."));
        return cart.listProducts();
    }

    @Transactional
    public void addToCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new IllegalStateException("Cart does not exist."));
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalStateException("Product does not exist."));
        CartItem cartItem = new CartItem();

        if(product.getAmount() > 0) {
            boolean itemExists = false;
            for(CartItem i : cart.getCart()) {
                if(i.getProductId().equals(productId)) {
                    itemExists = true;
                    i.setPrice(i.getPrice() + product.getPrice());
                    i.setAmount(i.getAmount() + 1);
                    product.setAmount(product.getAmount() - 1);
                    break;
                }
            }

            if(!itemExists) {
                cartItem.setProductId(productId);
                cartItem.setName(product.getName());
                cartItem.setPrice(product.getPrice());
                cartItem.setAmount(1);
                product.setAmount(product.getAmount() - 1);
                cart.getCart().add(cartItem);
            }
        }

        cartRepository.save(cart);
        productRepository.save(product);
    }

    @Transactional
    public void addToCartByAmount(Long cartId, Long productId, int amount) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new IllegalStateException("Cart does not exist."));
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalStateException("Product does not exist."));
        CartItem cartItem = new CartItem();

        if(product.getAmount() > 0) {
            boolean itemExists = false;
            for(CartItem i : cart.getCart()) {
                if(i.getProductId().equals(productId)) {
                    itemExists = true;
                    i.setPrice(i.getPrice() + (product.getPrice() * amount));
                    i.setAmount(i.getAmount() + amount);
                    product.setAmount(product.getAmount() - amount);
                    break;
                }
            }

            if(!itemExists) {
                cartItem.setProductId(productId);
                cartItem.setName(product.getName());
                cartItem.setPrice(product.getPrice() * amount);
                cartItem.setAmount(amount);
                product.setAmount(product.getAmount() - amount);
                cart.getCart().add(cartItem);
            }
        }

        cartRepository.save(cart);
        productRepository.save(product);
    }

    @Transactional
    public void removeItemByAmount(Long cartId, Long productId, int amount) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new IllegalStateException("Cart does not exist."));
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalStateException("Product does not exist."));

        for(CartItem i : cart.getCart()) {
            if(i.getProductId().equals(productId)) {
                product.setAmount(product.getAmount() + amount);
                i.setAmount(i.getAmount() - amount);
                i.setPrice(i.getPrice() - (product.getPrice() * amount));

                if(i.getAmount() <= 0) {
                    cart.getCart().remove(i);
                }

                cartRepository.save(cart);
                productRepository.save(product);
                return;
            }
        }

        throw new IllegalStateException("Item with id " + productId + " is not in the cart.");
    }

    @Transactional
    public void removeItemById(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new IllegalStateException("Cart does not exist."));
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalStateException("Product does not exist."));

        for(CartItem i : cart.getCart()) {
            if(i.getProductId().equals(productId)) {
                product.setAmount(product.getAmount() + i.getAmount());
                cart.getCart().remove(i);
                cartRepository.save(cart);
                productRepository.save(product);
                return;
            }
        }

        throw new IllegalStateException("Item with id " + productId + " is not in the cart.");
    }

    @Transactional
    public void removeAllItems(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new IllegalStateException("Cart does not exist."));

        if(cart.getCart().isEmpty()) {
            throw new IllegalStateException("Cart is empty.");
        }

        Iterator<CartItem> iterator = cart.getCart().iterator();
        while(iterator.hasNext()) {
            CartItem cartItem = iterator.next();
            Product product = productRepository.findById(cartItem.getProductId())
                    .orElseThrow(() -> new IllegalStateException("Product does not exist."));

            product.setAmount(product.getAmount() + cartItem.getAmount());
            productRepository.save(product);

            iterator.remove();
        }

        cartRepository.save(cart);
    }

    public Double totalPrice(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new IllegalStateException("Cart does not exist."));
        double total = 0.0;

        if(cart.getCart().isEmpty()) {
            return total;
        }

        for(CartItem i : cart.getCart()) {
            total += i.getPrice();
        }

        return total;
    }


}
