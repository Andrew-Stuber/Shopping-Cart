// package shoppingcart.com.shoppingcart;

// import org.junit.jupiter.api.Test;
// import org.springframework.boot.test.context.SpringBootTest;
// import shoppingcart.com.shoppingcart.Cart.Cart;
// import shoppingcart.com.shoppingcart.Product.Product;

// import static org.junit.jupiter.api.Assertions.assertEquals;


// @SpringBootTest
// class ShoppingcartApplicationTests {

// 	private Cart cart;

// 	@Test
// 	void testEmptyCart() {
// 		cart = new Cart();
// 		assertEquals(0d, cart.totalPrice());
// 	}

// 	@Test
// 	void testCartWithItems() {
// 		cart = new Cart();
// 		cart.getProducts().add(new Product("Coke", 10d));
// 		cart.getProducts().add(new Product("Chocolate", 5d));
// 		assertEquals(15d, cart.totalPrice());
// 	}

// 	@Test
// 	void testCartWithAmounts() {
// 		cart = new Cart();
// 		cart.getProducts().add(new Product("Coke", 10d, 2));
// 		cart.getProducts().add(new Product("Chocolate", 5d, 2));
// 		assertEquals(30d, cart.totalPrice());
// 	}

// 	@Test
// 	void testListItems() {
// 		cart = new Cart();
// 		cart.getProducts().add(new Product("Coke", 10d));
// 		cart.getProducts().add(new Product("Chocolate", 5d));
// 		System.out.println(cart.listProducts());
// 	}

// }
