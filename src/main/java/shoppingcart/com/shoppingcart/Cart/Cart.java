package shoppingcart.com.shoppingcart.Cart;

import shoppingcart.com.shoppingcart.Product.Product;

import java.util.*;

public class Cart {
    public Cart() { }

    List<Product> products = new ArrayList();

    public Object totalPrice() {
        double total = 0;

        if(products.isEmpty()) {
            return total;
        }

        for(Product i : products) {
            total += i.getPrice();
        }

        return total;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Object[] listProducts() {
        return products.toArray();
    }

}
