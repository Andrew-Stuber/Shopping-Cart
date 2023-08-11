package shoppingcart.com.shoppingcart;

import java.util.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id; 
    String name;
    double price;
    int amount;

    public Products(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Products(String name, double price, int amount) {
        this.name = name;
        this.price = price * amount;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
