package main.java.com.presentation.controler;

import main.java.com.business.models.*;
import main.java.com.business.service.AdminService;
import main.java.com.business.ultil.validation.ProductValidation;
import main.java.com.presentation.controler.manager.CategoryManager;
import main.java.com.presentation.controler.manager.OrderManager;
import main.java.com.presentation.controler.manager.ProductManager;
import main.java.com.presentation.controler.manager.UserManager;

import java.util.Scanner;

public class AdminControler {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void adminMenu(User user) {
        Scanner scanner = new Scanner(System.in);
        String name = user.getName();
        while (true) {
            System.out.println(ANSI_BLUE + "╔════════════════════════════════════════════╗" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "   Chào mừng " + name + " Admin                         " + ANSI_RESET);
            System.out.println(ANSI_BLUE + "╠════════════════════════════════════════════╣" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ Nhập lựa chọn của bạn:                     ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 1. Quản lý sản phẩm                        ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 2. Quản lý danh mục                        ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 3. Quản lý đơn hàng                        ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 4. Quản lý người dùng                      ║" + ANSI_RESET);
            System.out.println(ANSI_RED + "║ 5. Đăng xuất                               ║" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "╚════════════════════════════════════════════╝" + ANSI_RESET);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> productManagement();
                case 2 -> categoryManagement();
                case 3 -> orderManagement();
                case 4 -> userManagement();
                case 5 -> {
                    System.out.println("Đăng xuất thành công");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ");
            }

        }

    }

    public static void categoryManagement() {
        CategoryManager.categoryMenu();
    }

    public static void productManagement() {
        ProductManager.menuProduct();
    }

    public static void orderManagement() {
        OrderManager.orderMenu();
    }

    public static void userManagement() {
        UserManager.userMenu();
    }

}
