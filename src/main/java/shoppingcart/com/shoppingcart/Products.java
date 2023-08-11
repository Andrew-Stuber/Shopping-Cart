package shoppingcart.com.shoppingcart;
import java.util.*;

public class Products {
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

    public double getPrice() {
        return price;
    }
}
