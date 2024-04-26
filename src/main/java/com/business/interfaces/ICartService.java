package main.java.com.business.interfaces;

import main.java.com.business.models.*;

public interface ICartService {
    void addProductToCart(Product product, int quantity);
    void removeProductFromCart( Product product);
    void displayCart();
    void checkOut();
    void displayOrderHistory();
}
