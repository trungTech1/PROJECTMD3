package main.java.com.presentation.controler.manager;

import main.java.com.business.service.AdminService;

import java.util.Scanner;

public class OrderManager {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void orderMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(ANSI_BLUE + "╔════════════════════════════════════════════╗" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "  Quản lý đơn hàng                  " + ANSI_RESET);
            System.out.println(ANSI_BLUE + "╠════════════════════════════════════════════╣" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 1. Hiển thị danh sách đơn hàng            ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 2. Xem chi tiết đơn hàng                       ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 3. Xác nhận đơn hàng                       ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 4. Hủy đơn hàng                       ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 5. Cập nhật trạng thái đơn hàng                      ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 6. Tìm kiếm đơn hàng                      ║" + ANSI_RESET);
            System.out.println(ANSI_RED + "║ 0. Quay lại menu                                 ║" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "╚════════════════════════════════════════════╝" + ANSI_RESET);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    displayOrders();
                    break;
                case 2:
                    viewOrderDetail();
                    break;
                case 3:
                    confirmOrder();
                    break;
                case 4:
                    cancelOrder();
                    break;
                case 5:
                    updateOrderStatus();
                    break;
                case 6:
                    searchOrder();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }

        }
    }
    public static void displayOrders() {
        AdminService.showAllOrder();
    }
    public static void viewOrderDetail() {
        Scanner scanner = new Scanner(System.in);
        AdminService.showAllOrder();
        System.out.print("Nhập mã đơn hàng cần xem chi tiết: ");
        String id = scanner.nextLine();
        AdminService.viewOrderDetail(id);
    }
    public static void confirmOrder() {
        Scanner scanner = new Scanner(System.in);
        AdminService.showAllOrder();
        System.out.print("Nhập mã đơn hàng cần xác nhận: ");
        String id = scanner.nextLine();
        AdminService.confirmOrder(id);
    }
    public static void cancelOrder() {
        Scanner scanner = new Scanner(System.in);
        AdminService.showAllOrder();
        System.out.print("Nhập mã đơn hàng cần hủy: ");
        String id = scanner.nextLine();
        AdminService.cancelOrder(id);
    }
    public static void updateOrderStatus() {
        Scanner scanner = new Scanner(System.in);
        AdminService.showAllOrder();
        System.out.print("Nhập mã đơn hàng cần cập nhật trạng thái: ");
        String id = scanner.nextLine();
        AdminService.updateOrderStatus(id);
    }
    public static void searchOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập mã đơn hàng cần tìm kiếm: ");
        String id = scanner.nextLine();
        AdminService.searchOrder(id);
    }
}
