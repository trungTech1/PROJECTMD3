package main.java.com.business.models;

import main.java.com.business.ultil.otherFile.*;
import main.java.com.business.ultil.validation.*;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class User implements Serializable{
    public enum Role {
        ADMIN, USER
    }

    private int id;
    private String name;
    private String email;
    private String password;
    private String address;
    private String phone;
    private Cart cart;
    private Role role;
    private boolean status = true;
    private List<Order> orders;

    public User() {
    }

    public User(int id, String name, String email, String password, String address, String phone, Cart cart, Role role,boolean status, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.cart = cart;
        this.role = role;
        this.orders = orders;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Role changeRole() {
        Scanner scanner = new Scanner(System.in);
       while (true) {
            System.out.println("Nhập 1 để chuyển thành admin, 2 để chuyển thành user");
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 1) {
                return Role.ADMIN;
            } else if (choice == 2) {
                return Role.USER;
            } else {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
    public void inputData(boolean isCreate) {
        //Nhập và kiển tra tên đăng nhập
        while (true) {
            System.out.println("Nhập tên đăng nhập: ");
            String name = InputMethods.getString();
            if (name.length() > 0) {
                this.name = name;
                break;
            } else {
                System.out.println("Tên đăng nhập không hợp lệ. Vui lòng nhập lại.");
            }
        }
        //Nhập và kiểm tra mật khẩu
        while (true) {
            System.out.println("Nhập mật khẩu: ");
            String password = InputMethods.getString();
            if (UserValidation.isValidPassword(password)) {
                this.password = password;
                break;
            } else {
                System.out.println("Mật khẩu phải có 1 chữ viết hoa, 1 chữ viết thường, 1 số và có 8 kí tự trở lên. Vui lòng nhập lại.");
            }
        }
        //Nhập và kiểm tra email
        while (true) {
            System.out.println("Nhập email: ");
            String email = InputMethods.getString();
            if (UserValidation.isValidEmail(email)) {
                this.email = email;
                break;
            } else {
                System.out.println("Email không hợp lệ. Vui lòng nhập lại.");
            }
        }
        //Nhập và kiểm tra số điện thoại
        while (true) {
            System.out.println("Nhập số điện thoại: ");
            String phone = InputMethods.getString();
            if (UserValidation.isValidPhone(phone)) {
                this.phone = phone;
                break;
            } else {
                System.out.println("Số điện thoại không hợp lệ. Vui lòng nhập lại.");
            }
        }
        //Nhập và kiểm tra địa chỉ
        while (true) {
            System.out.println("Nhập địa chỉ: ");
            String address = InputMethods.getString();
            if (address.length() > 0) {
                this.address = address;
                break;
            } else {
                System.out.println("Địa chỉ không hợp lệ. Vui lòng nhập lại.");
            }
        }
        //role
        if (isCreate) {
            this.role = Role.USER;
        }
    }

    @Override
    public String toString() {
        return String.format(
                """ 
                            User{
                            id=%d,
                            name='%s',
                            email='%s',
                            address='%s',
                            phone='%s',
                            cart=%s
                        }
                        """, id, name, email, address, phone, cart
        );
    }
}
