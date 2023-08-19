package shoppingcart.com.shoppingcart.Cart;

import java.util.*;

import jakarta.persistence.*;

@Entity
@Table
public class Cart {
    public Cart() { }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ElementCollection
    @CollectionTable(name = "listOfItems", joinColumns = @JoinColumn(name = "cart_id"))
    List<CartItem> cart = new ArrayList<>();

    public List<CartItem> getCart() {
        return cart;
    }

    public Long getId() {
        return id;
    }

    public List<CartItem> listProducts() {
        return cart;
    }

    public void setCart(List<CartItem> cart) {
        this.cart = cart;
    }

}
