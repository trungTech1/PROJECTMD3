package main.java.com.business.service;

import java.util.List;
import main.java.com.business.models.Order;
import main.java.com.business.ultil.otherFile.IoFileUtility;

public class OrderService {
    private static final List<Order> orders = IoFileUtility.readFromFile(IoFileUtility.ORDERS_PATH, Order.class);
    public static void addOrder(Order order) {
        // Thêm đơn hàng
        orders.add(order);
        // Lưu lại danh sách đơn hàng
        IoFileUtility.writeToFile(IoFileUtility.ORDERS_PATH, orders);
    }

}
