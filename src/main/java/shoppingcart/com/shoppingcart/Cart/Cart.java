package shoppingcart.com.shoppingcart.Cart;

import shoppingcart.com.shoppingcart.Product.Product;
import java.util.*;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyJoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Cart {
    public Cart() { }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cart_id")
    List<Product> cart = new ArrayList();

    public List<Product> setPrduct() {
        return cart;
    }

    public List<Product> getCart() {
        return cart;
    }

    public Long getId() {
        return id;
    }

    public List<Product> listProducts() {
        return cart;
    }

}
