package shoppingcart.com.shoppingcart;

import java.util.*;

public class Cart {
    public Cart() { }

    List<Products> products = new ArrayList();

    public Object totalPrice() {
        double total = 0;

        if(products.isEmpty()) {
            return total;
        }

        for(Products i : products) {
            total += i.getPrice();
        }

        return total;
    }

    public List<Products> getProducts() {
        return products;
    }

    public Object[] listProducts() {
        return products.toArray();
    }

}
