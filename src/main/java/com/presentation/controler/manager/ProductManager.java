package main.java.com.presentation.controler.manager;

import main.java.com.business.models.Product;
import main.java.com.business.service.AdminService;
import main.java.com.business.ultil.validation.ProductValidation;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ProductManager {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void menuProduct() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(ANSI_BLUE + "╔════════════════════════════════════════════╗" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "  Quản lý sản phẩm                    " + ANSI_RESET);
            System.out.println(ANSI_BLUE + "╠════════════════════════════════════════════╣" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ Nhập lựa chọn của bạn:                     ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 1. Hiển thị danh sách sản phẩm             ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 2. Thêm sản phẩm mới                       ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 3. Chỉnh sửa sản phẩm                      ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 4. Xóa sản phẩm                            ║" + ANSI_RESET);
            System.out.println(ANSI_RED + "║ 5.Hiển thị sản phẩm theo danh mục          ║" + ANSI_RESET);
            System.out.println(ANSI_RED + "║ 6. Quay lại                                ║" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "╚════════════════════════════════════════════╝" + ANSI_RESET);
            int choise = scanner.nextInt();
            switch (choise) {
                case 1 -> showProduct();
                case 2 -> addProduct();
                case 3 -> editProduct();
                case 4 -> deleteProduct();
                case 5 -> showProductByCategory();
                case 6 -> {return;}
                default-> System.out.println("Lựa chọn không hợp lệ");
            }
        }
    }

    public static void showProduct() {
        AdminService.showAllProduct();
    }

    public static void addProduct() {
        Product product = new Product();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Thêm sản phẩm mới");
        Random random = new Random();
        int id = 1000 + random.nextInt(9000);
        String productId = "P" + id;
        product.setId(productId);
        System.out.println("Nhập tên sản phẩm: ");
        String productName = scanner.nextLine();
        while (!ProductValidation.isValidProductName(productName)) {
            System.out.println("Tên sản phẩm không được bỏ trống.");
            productName = scanner.nextLine();
        }
        product.setName(productName);
        System.out.println("Nhập giá sản phẩm: ");
        Double productPrice = Double.parseDouble(scanner.nextLine());
        while (!ProductValidation.isValidProductPrice(String.valueOf(productPrice))) {
            System.out.println("Giá sản phẩm không hợp lệ. Giá sản phẩm phải lớn hơn 1000 và không được bỏ trống.");
            productPrice = Double.parseDouble(scanner.nextLine());
        }
        product.setPrice(productPrice);
        System.out.println("Nhập số lượng sản phẩm: ");
        int productQuantity = Integer.parseInt(scanner.nextLine());
        while (!ProductValidation.isValidProductQuantity(String.valueOf(productQuantity))) {
            System.out.println("Số lượng sản phẩm không hợp lệ. Số lượng sản phẩm phải lớn hơn 0 và không được bỏ trống.");
            productQuantity = Integer.parseInt(scanner.nextLine());
        }
        product.setQuantity(productQuantity);
        System.out.println("Lựa chọn danh mục: ");
        product.setCategory(String.valueOf(AdminService.selectCategory()));
        System.out.println("Nhập mô tả: ");
        String productDescriptionnew = scanner.nextLine();
        while (!ProductValidation.isValidProductDescription(productDescriptionnew)) {
            System.out.println("Mô tả sản phẩm không được bỏ trống.");
            productDescriptionnew = scanner.nextLine();
        }
        product.setDescription(productDescriptionnew);
        LocalDate localDate = LocalDate.now();
        product.setCreatedDate(product.getFormattedCreatedDate(localDate));
        System.out.println("Nhập trạng thái sản phẩm: ");
        System.out.println("0. Hết hàng");
        System.out.println("1. Còn hàng");
        int productStatus = Integer.parseInt(scanner.nextLine());
        while (!ProductValidation.isValidProductStatus(String.valueOf(productStatus))) {
            System.out.println("Trạng thái sản phẩm không hợp lệ. Trạng thái sản phẩm phải là 0 hoặc 1.");
            productStatus = Integer.parseInt(scanner.nextLine());
        }
        product.setStatus(productStatus == 1);
        AdminService.addProduct(product);
        System.out.println("Thêm sản phẩm thành công");
    }

    public static void editProduct() {
        AdminService.showAllProduct();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chỉnh sửa sản phẩm");
        System.out.println("Nhập Id sản phẩm cần chỉnh sửa: ");
        String id = scanner.nextLine();
        Product product = AdminService.findProductById(id);
        if (product == null) {
            System.out.println("Không tìm thấy sản phẩm");
            return;
        }
        while (true) {
            System.out.println("Chọn thuộc tính cần chỉnh sửa");
            System.out.println("1. Tên sản phẩm");
            System.out.println("2. Giá sản phẩm");
            System.out.println("3. Số lượng sản phẩm");
            System.out.println("4. Danh mục sản phẩm");
            System.out.println("5. Mô tả sản phẩm");
            System.out.println("6. Trạng thái sản phẩm");
            System.out.println("7. Thoát");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Nhập tên sản phẩm mới: ");
                    String productName = scanner.nextLine();
                    if (!ProductValidation.isValidProductName(productName)) {
                        System.out.println("Tên sản phẩm không được bỏ trống.");
                        return;
                    }
                    product.setName(productName);

                case 2:
                    System.out.println("Nhập giá sản phẩm mới: ");
                    Double productPrice = Double.parseDouble(scanner.nextLine());
                    if (!ProductValidation.isValidProductPrice(String.valueOf(productPrice))) {
                        System.out.println("Giá sản phẩm không hợp lệ. Giá sản phẩm phải lớn hơn 1000 và không được bỏ trống.");
                        return;
                    }
                    product.setPrice(productPrice);
                    break;
                case 3:
                    System.out.println("Nhập số lượng sản phẩm mới: ");
                    int productQuantity = Integer.parseInt(scanner.nextLine());
                    if (!ProductValidation.isValidProductQuantity(String.valueOf(productQuantity))) {
                        System.out.println("Số lượng sản phẩm không hợp lệ. Số lượng sản phẩm phải lớn hơn 0 và không được bỏ trống.");
                        return;
                    }
                    product.setQuantity(productQuantity);
                    break;
                case 4:
                    System.out.println("Lựa chọn danh mục: ");
                    product.setCategory(String.valueOf(AdminService.selectCategory()));
                    break;
                case 5:
                    System.out.println("Nhập mô tả sản phẩm mới: ");
                    String productDescription = scanner.nextLine();
                    if (!ProductValidation.isValidProductDescription(productDescription)) {
                        System.out.println("Mô tả sản phẩm không được bỏ trống.");
                        return;
                    }
                    product.setDescription(productDescription);
                    break;
                case 6:
                    System.out.println("Nhập trạng thái sản phẩm mới: ");
                    System.out.println("0. Hết hàng");
                    System.out.println("1. Còn hàng");
                    int productStatus = Integer.parseInt(scanner.nextLine());
                    if (!ProductValidation.isValidProductStatus(String.valueOf(productStatus))) {
                        System.out.println("Trạng thái sản phẩm không hợp lệ. Trạng thái sản phẩm phải là 0 hoặc 1.");
                        return;

                    }
                    product.setStatus(productStatus == 1);
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
            AdminService.updateProduct(product);
            System.out.println("Chỉnh sửa sản phẩm thành công");
        }
    }

    public static void deleteProduct() {
        AdminService.showAllProduct();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập Id sản phẩm cần xóa: ");
        String id = scanner.nextLine();
        AdminService.deleteProduct(id);
        System.out.println("Xóa sản phẩm thành công");
    }

    public static void showProductByCategory() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập danh mục sản phẩm cần hiển thị: ");
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
}
