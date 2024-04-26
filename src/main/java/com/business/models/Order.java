package main.java.com.business.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    public enum OrderStatus {
        PENDING, SHIPPED, DELIVERED, CANCELLED
    }

    private String orderId;
    private int userId;
    private Date orderDate;
    private OrderStatus status;
    private List<OrderDetail> orderDetails;

    private double total;

    public Order() {
        this.orderDetails = new ArrayList<>();
    }

    public Order(String orderId, int userId, Date orderDate, OrderStatus status, List<OrderDetail> orderDetails, double total) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.status = status;
        this.orderDetails = orderDetails;
        this.total = total;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void nextStatus() {
        switch (status) {
            case PENDING:
                status = OrderStatus.SHIPPED;
                break;
            case SHIPPED:
                status = OrderStatus.DELIVERED;
                break;
            case DELIVERED:
                status = OrderStatus.CANCELLED;
                break;
            case CANCELLED:
                status = OrderStatus.PENDING;
                break;
        }
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void confirm() {
        this.setStatus(OrderStatus.SHIPPED);
    }

    public void cancel() {
        this.setStatus(OrderStatus.CANCELLED);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", userId=" + userId +
                ", orderDate=" + orderDate +
                ", status=" + status +
                ", total=" + total +
                '}';
    }
}
