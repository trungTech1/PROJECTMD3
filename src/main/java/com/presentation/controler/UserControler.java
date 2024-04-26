package main.java.com.presentation.controler;

import main.java.com.business.models.*;
import main.java.com.business.service.AdminService;
import main.java.com.business.service.CartService;
import main.java.com.business.service.OrderService;
import main.java.com.business.service.UserService;

import java.util.List;
import java.util.Scanner;

public class UserControler {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void userMenu(User user) {
        Scanner scanner = new Scanner(System.in);
        String name = user.getName();
        while (true) {
            System.out.println(ANSI_BLUE + "╔════════════════════════════════════════════╗" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "║  xin chào " + name + "                       ║ " + ANSI_RESET);
            System.out.println(ANSI_BLUE + "╠════════════════════════════════════════════╣" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 1. Hiển thị thông tin cá nhân              ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 2. Sửa đổi thông tin                       ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 3. Xem tất cả mặt hàng                     ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 4. Xem mặt hàng theo danh mục              ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 5. Thêm mặt hàng vào giỏ                   ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 6. xem giỏ hàng                            ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 7 Xóa mặt hàng khỏi giỏ                    ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 8. Thanh toán                              ║" + ANSI_RESET);
            System.out.println(ANSI_RED + "║ 0. Đăng xuất                               ║" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "╚════════════════════════════════════════════╝" + ANSI_RESET);
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> displayInfo(user);
                case 2 -> updateInfo(user);
                case 3 -> displayAllProduct();
                case 4 -> displayProductByCategory(scanner);
                case 5 -> addProductToCart(user);
                case 6 -> viewCart(user);
                case 7 -> removeProductFromCart();
                case 8 -> pay(user);
                case 0 -> {
                    logout();
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ");
            }

        }
    }


    public static void displayInfo(User user) {
        UserService.display(user);
    }

    public static void displayAllProduct() {
        AdminService.showAllProduct();
    }

    public static void displayProductByCategory(Scanner scanner) {
        System.out.println("Danh sách danh mục: ");
        AdminService.showAllCategory();
        System.out.println("Nhập id danh mục: ");
        String categoryName = scanner.nextLine();
        List<Product> productsByCategory = AdminService.findProductsByCategory(categoryName);
        if (productsByCategory.isEmpty()) {
            System.out.println("Không có sản phẩm trong danh mục này.");
        } else {
            System.out.println("Sản phẩm thuộc danh mục " + categoryName + ":");
            for (Product product : productsByCategory) {
                System.out.println(product.toString());
            }
        }
    }

    public static void addProductToCart(User user) {
        Scanner scanner = new Scanner(System.in);
        // Hiển thị tất cả sản phẩm
        AdminService.showAllProduct();
        if (AdminService.products.isEmpty()) {
            return;
        }
        System.out.println("Nhập id sản phẩm: ");
        String id = scanner.nextLine();
        System.out.println("Nhập số lượng: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        // Kiểm tra số lượng
        while (quantity <= 0) {
            System.out.println("Số lượng không hợp lệ. Vui lòng nhập lại: ");
            quantity = Integer.parseInt(scanner.nextLine());
        }
        //kiểm tra số lượng tồn kho
        while (!CartService.checkQuantity(id, quantity)) {
            System.out.println("Số lượng không đủ. Vui lòng nhập lại: ");
            quantity = Integer.parseInt(scanner.nextLine());
        }

        // Tìm sản phẩm dựa trên id
        Product product = AdminService.findProductById(id);
        if (product == null) {
            System.out.println("Không tìm thấy sản phẩm");
            return;
        }
        // Tạo một giỏ hàng mới nếu người dùng chưa có giỏ hàng
        Cart cart = user.getCart();
        if (cart == null) {
            cart = new Cart();
            user.setCart(cart);
        }
        // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
        boolean found = false;
        for (CartItem cartItem : cart.getCartItems()) {
            if (cartItem.getProduct().getId().equals(product.getId())) {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                found = true;
                break;
            }
        }

        // Nếu sản phẩm chưa tồn tại trong giỏ hàng, thêm một CartItem mới
        if (!found) {
            CartItem newCartItem = new CartItem(product, quantity);
            cart.getCartItems().add(newCartItem);
        }

        // Tính tổng tiền
        cart.setTotal(cart.getTotal() + product.getPrice() * quantity);

        // Cập nhật giỏ hàng
        CartService.addProductToCart(cart);
    }

    public static void viewCart(User user) {
        Cart cart = user.getCart();
        if (cart == null || cart.getCartItems().isEmpty()) {
            System.out.println("Giỏ hàng trống");
            return;
        }
        System.out.println("Danh sách sản phẩm trong giỏ hàng: ");
        for (CartItem cartItem : cart.getCartItems()) {
            int index = cart.getCartItems().indexOf(cartItem);
            System.out.println((index + 1) + ". " + cartItem.getProduct().getName() + " - " + cartItem.getQuantity() + " - " + cartItem.getProduct().getPrice() + " - " + cartItem.getProduct().getPrice() * cartItem.getQuantity());
        }
        System.out.println("Tổng tiền: " + cart.getTotal());
    }

    public static void removeProductFromCart() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhập id sản phẩm cần xóa: ");
        String id = scanner.nextLine();
        CartService.removeProductFromCart(id);
    }

    public static void pay(User user) {
        // Thanh toán
        Cart cart = user.getCart();
        if (cart == null || cart.getCartItems().isEmpty()) {
            System.out.println("Giỏ hàng trống");
            return;
        }
        // Tạo hóa đơn
        Order order = new Order();
        order.setUserId(user.getId());
        order.setTotal(cart.getTotal());
        for (CartItem cartItem : cart.getCartItems()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProductId(cartItem.getProduct().getId());
            orderDetail.setQuantity(cartItem.getQuantity());
            order.getOrderDetails().add(orderDetail);
        }
        // Xóa giỏ hàng
        user.setCart(null);
        // Lưu hóa đơn
        user.getOrders().add(order);
        OrderService.addOrder(order);
        // Xóa giỏ hàng trong file txt
        CartService.removeCart();
        System.out.println("Thanh toán thành công");
    }

    public static void logout() {
        UserService.logout();
        System.out.println("Đăng xuất thành công");
    }

    public static void updateInfo(User user) {
        UserService.updateUser(user.getId());
    }
}
