package main.java.com.presentation.run;

import main.java.com.business.models.*;
import main.java.com.presentation.controler.AdminControler;
import main.java.com.presentation.controler.UserControler;
import main.java.com.business.ultil.otherFile.InputMethods;
import main.java.com.business.service.*;
import main.java.com.business.ultil.otherFile.IoFileUtility;

import java.util.Scanner;


public class Main {
    public static UserService userService = new UserService();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        // Mã màu ANSI
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_CYAN = "\u001B[36m";
        System.out.println("********** Welcome to Online Shopping **********");
        while (true) {
            // In khung đăng nhập với màu
            // In khung đăng nhập với màu chữ và khung khác nhau
            System.out.println(ANSI_PURPLE + "╔════════════════════════╗" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "╠════════════════════════╣" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "║" + ANSI_RESET + ANSI_CYAN + "1. Register" + ANSI_RESET + ANSI_PURPLE + "             ║" + ANSI_RESET);

            System.out.println(ANSI_PURPLE + "║" + ANSI_RESET + ANSI_CYAN + "2. Login" + ANSI_RESET + ANSI_PURPLE + "                ║" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "║" + ANSI_RESET + ANSI_CYAN + "3. Exit" + ANSI_RESET + ANSI_PURPLE + "                 ║" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "╚ " + ANSI_RESET + ANSI_CYAN + "Enter your choice" + ANSI_RESET + ANSI_PURPLE + " ═════╝" + ANSI_RESET);
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> register();
                case 2 -> login();
                case 3 -> {
                    System.out.println("Goodbye");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ");
            }
        }
    }

    public static void login() {
        System.out.println("Nhập tên đăng nhập : ");
        String username = InputMethods.getString();
        System.out.println("Nhập mật khẩu :");
        String password = InputMethods.getString();
        User user = UserService.loginUser(username, password);
        if (user == null) {
            System.out.println("Đăng nhập thất bại");
            return;
        }
        IoFileUtility.writeToFile2(user, IoFileUtility.USER_LOGIN_PATH);
        // Xet role
        if (user.isStatus()) {
            switch (user.getRole()) {
                case ADMIN -> {
                    System.out.println("Đăng nhập thành công với quyền Admin");
                    AdminControler.adminMenu(user);
                }
                case USER -> {
                    System.out.println("Đăng nhập thành công với quyền User");
                    UserControler.userMenu(user);
                }
            }
        }
    }

    public static void register() {
        System.out.println("Nhập thông tin người dùng");
        User newUser = new User();
        newUser.inputData(true);
        userService.registerUser(newUser);
    }
}
