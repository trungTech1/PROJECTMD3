package main.java.com.presentation.controler.manager;

import main.java.com.business.models.User;
import main.java.com.business.ultil.otherFile.IoFileUtility;

import java.util.List;
import java.util.Scanner;

public class UserManager {
    private static final List<User> users = IoFileUtility.readFromFile(IoFileUtility.USERS_PATH, User.class);
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void userMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(ANSI_BLUE + "╔════════════════════════════════════════════╗" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "   Quản lý user                 " + ANSI_RESET);
            System.out.println(ANSI_BLUE + "╠════════════════════════════════════════════╣" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 1. Hiển thị toàn bộ user                   ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 2. Chuyển quyền user                       ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 3. Xóa user                                ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 0. Thoát                                   ║" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "╚════════════════════════════════════════════╝" + ANSI_RESET);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> displayAllUser();
                case 2 -> changeRole();
                case 3 -> deleteUser();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ");
            }
        }
    }

    public static void displayAllUser() {
        System.out.println("Danh sách user: ");
        for (User user : users) {
            System.out.println(user.getName() + " " + user.getRole());
        }
    }

    public static void changeRole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên user cần chuyển quyền: ");
        String name = scanner.nextLine();
        for (User user : users) {
            if (user.getName().equals(name)) {
                System.out.println("Nhập quyền mới: ");
                User.Role role = user.changeRole();
                user.setRole(role);
                IoFileUtility.writeToFile(IoFileUtility.USERS_PATH, users);
                System.out.println("Chuyển quyền thành công");
                return;
            }
        }
        System.out.println("Không tìm thấy user");
    }

    public static void deleteUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên user cần xóa: ");
        String name = scanner.nextLine();
        for (User user : users) {
            if (user.getName().equals(name)) {
                users.remove(user);
                IoFileUtility.writeToFile(IoFileUtility.USERS_PATH, users);
                System.out.println("Xóa user thành công");
                return;
            }
        }
        System.out.println("Không tìm thấy user");
    }

}
