package main.java.com.business.interfaces;

import main.java.com.business.models.Order;

public interface IOrderService {
    void createOrder( Order order);
    void updateOrder( Order order);
    void deleteOrder( Order order);
    void displayOrder( Order order);
    Order getOrder( int orderId);

}
