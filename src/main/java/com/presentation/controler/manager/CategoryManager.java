package main.java.com.presentation.controler.manager;

import java.util.Random;
import java.util.Scanner;

import main.java.com.business.models.Categories;
import main.java.com.business.service.AdminService;
import main.java.com.business.ultil.validation.CategoryValidation;

public class CategoryManager {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void categoryMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(ANSI_BLUE + "╔════════════════════════════════════════════╗" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "  Quản lý danh mục                     " + ANSI_RESET);
            System.out.println(ANSI_BLUE + "╠════════════════════════════════════════════╣" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ Nhập lựa chọn của bạn:                     ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 1. Hiển thị danh sách danh mục             ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 2. Thêm danh mục mới                       ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 3. Chỉnh sửa danh mục                      ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 4. Xóa danh mục                            ║" + ANSI_RESET);
            System.out.println(ANSI_RED + "║ 0. Quay lại                                ║" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "╚════════════════════════════════════════════╝" + ANSI_RESET);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1-> showCategory();
                case 2-> addCategory();
                case 3-> editCategory();
                case 4-> deleteCategory();
                case 0->{return;}
                default -> System.out.println("Lựa chọn không hợp lệ");
            }
        }
    }

    public static void addCategory() {
        Scanner scanner = new Scanner(System.in);
        Categories category = new Categories();
        System.out.println("Thêm danh mục mới");
        Random random = new Random();
        int id = 1000 + random.nextInt(9000);
        String idString = "C" + id;
        category.setId(idString);
        System.out.println("Nhập tên danh mục: ");
        String name = scanner.nextLine();
        while (!CategoryValidation.checkCategoryName(name)) {
            System.out.println("Tên danh mục không được bỏ trống và phải chứa 1 ký tự viết hoa. Vui lòng nhập lại");
            name = scanner.nextLine();
        }
        category.setName(name);
        System.out.println("Nhập mô tả: ");
        String description = scanner.nextLine();
        while (!CategoryValidation.checkCategoryDescription(description)) {
            System.out.println("Mô tả không được bỏ trống. Vui lòng nhập lại");
            description = scanner.nextLine();
        }
        category.setDescription(description);
        AdminService.addCategory(category);
        System.out.println("Thêm danh mục thành công");
    }
    public static void showCategory() {
        if (AdminService.categories.isEmpty()) {
            System.out.println("Danh sách danh mục trống.Vui lòng thêm danh mục mới");
            return;
        }
        System.out.println("Danh sách danh mục");
        AdminService.showAllCategory();
    }
    public static void editCategory() {
        Scanner scanner = new Scanner(System.in);
        if (AdminService.categories.isEmpty()) {
            System.out.println("Danh sách danh mục trống.Vui lòng thêm danh mục mới");
            return;
        }
        System.out.println("Chỉnh sửa danh mục");
        System.out.println("Nhập Id danh mục cần chỉnh sửa: ");
        String id = scanner.nextLine();
        Categories category = AdminService.findCategoryById(id);
        if (category == null) {
            System.out.println("Không tìm thấy danh mục");
            return;
        }
        System.out.println("Nhập tên danh mục mới: ");
        category.setName(scanner.nextLine());
        System.out.println("Nhập mô tả mới: ");
        category.setDescription(scanner.nextLine());
        AdminService.updateCategory(category);
        System.out.println("Chỉnh sửa danh mục thành công");
    }
    public static void deleteCategory() {
        Scanner scanner = new Scanner(System.in);
        if (AdminService.categories.isEmpty()) {
            System.out.println("Danh sách danh mục trống.Vui lòng thêm danh mục mới");
            return;
        }
        System.out.println("Xóa danh mục");
        System.out.println("Nhập Id danh mục cần xóa: ");
        String id = scanner.nextLine();
        AdminService.deleteCategory(id);
        System.out.println("Xóa danh mục thành công");
    }

}
