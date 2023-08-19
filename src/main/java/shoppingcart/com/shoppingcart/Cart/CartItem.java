package shoppingcart.com.shoppingcart.Cart;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CartItem implements Serializable {

    Long productId;
    String productName;
    double productPrice;
    int productAmount;

    public CartItem() { }
    
    public CartItem(Long productId, String productName, double productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public CartItem(Long productId, String productName, double productPrice, int productAmount) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productAmount = productAmount;
    }

    // public Long getId() {
    //     return id;
    // }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return productName;
    }

    public double getPrice() {
        return productPrice;
    }

    public int getAmount() {
        return productAmount;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setName(String productName) {
        this.productName = productName;
    }

    public void setPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public void setAmount(int productAmount) {
        this.productAmount = productAmount;
    }


}
