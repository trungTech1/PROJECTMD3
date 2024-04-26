package main.java.com.business.service;

import main.java.com.business.models.Categories;
import main.java.com.business.models.Order;
import main.java.com.business.models.Product;
import main.java.com.business.ultil.otherFile.IoFileUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminService {
    // Lấy dữ liệu từ file
    public static List<Product> products = IoFileUtility.readFromFile(IoFileUtility.PRODUCTS_PATH, Product.class);
    public static List<Categories> categories = IoFileUtility.readFromFile(IoFileUtility.CATEGORIES_PATH, Categories.class);
    public static List<Order> orderList = IoFileUtility.readFromFile(IoFileUtility.ORDERS_PATH, Order.class);

    // Các phương thức với sản phẩm
    // Phương thức thêm sản phẩm
    public static void addProduct(Product product) {
        // Thêm sản phẩm mới vào danh sách sản phẩm
        products.add(product);
        // Lưu lại danh sách sản phẩm
        IoFileUtility.writeToFile(IoFileUtility.PRODUCTS_PATH, products);
    }

    // Phương thức cập nhật sản phẩm
    public static void updateProduct(Product product) {
        // Tìm sản phẩm cần cập nhật
        for (Product p : products) {
            if (p.getId().equals(product.getId())) {
                // Cập nhật thông tin sản phẩm
                p.setName(product.getName());
                p.setPrice(product.getPrice());
                p.setQuantity(product.getQuantity());
                p.setCategory(product.getCategory());
                p.setId(product.getId());
                p.setStatus(product.isStatus());
                break;
            }
        }
        // Lưu lại danh sách sản phẩm
        IoFileUtility.writeToFile(IoFileUtility.PRODUCTS_PATH, products);
    }

    // Phương thức tìm sản phẩm theo id
    public static Product findProductById(String id) {
        // Tìm sản phẩm theo id
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    // Phương thức hiển thị sản phẩm theo danh mục
    public static List<Product> findProductsByCategory(String categoryName) {
        List<Product> productsByCategory = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(categoryName)) {
                productsByCategory.add(product);
            }
        }
        return productsByCategory;
    }

    // Phương thức hiển thị tất cả sản phẩm
    public static void showAllProduct() {
        if (products.isEmpty()) {
            System.out.println("Không có sản phẩm nào.");
            return;
        }
        // Hiển thị tất cả sản phẩm chỉ có tên và giá
        for (Product product : products) {
            System.out.println(product.getId() + " - " + product.getName() + " - " + product.getPrice());
        }
    }

    // Phương thức xóa sản phẩm
    public static void deleteProduct(String id) {
        if (products.isEmpty()) {
            System.out.println("Không có sản phẩm nào.");
            return;
        }
        // Tìm sản phẩm cần xóa
        for (Product product : products) {
            if (product.getId().equals(id)) {
                // Xóa sản phẩm
                products.remove(product);
                break;
            }
        }
        // Lưu lại danh sách sản phẩm
        IoFileUtility.writeToFile(IoFileUtility.PRODUCTS_PATH, products);
    }

    // Các phương thức với danh mục
    // Phương thức thêm danh mục
    public static void addCategory(Categories category) {
        // Thêm danh mục mới vào danh sách danh mục
        categories.add(category);
        // Lưu lại danh sách danh mục
        IoFileUtility.writeToFile(IoFileUtility.CATEGORIES_PATH, categories);
    }

    // Phương thức cập nhật danh mục
    public static void updateCategory(Categories category) {
        // Tìm danh mục cần cập nhật
        for (Categories c : categories) {
            if (c.getId().equals(category.getId())) {
                // Cập nhật thông tin danh mục
                c.setName(category.getName());
                c.setId(category.getId());
                break;
            }
        }
        // Lưu lại danh sách danh mục
        IoFileUtility.writeToFile(IoFileUtility.CATEGORIES_PATH, categories);
    }

    // Phương thức tìm danh mục theo id
    public static Categories findCategoryById(String id) {
        // Tìm danh mục theo id
        for (Categories category : categories) {
            if (category.getId().equals(id)) {
                return category;
            }
        }
        return null;
    }

    // Phương thức hiển thị tất cả danh mục
    public static void showAllCategory() {
        int index = 1;
        // Hiển thị tất cả danh mục
        for (Categories category : categories) {
            System.out.println(index + ". " + category.getId() + " - " + category.getName() + " - " + category.getDescription());
            index++;
        }
    }

    // Phương thức xóa danh mục
    public static void deleteCategory(String id) {
        // Tìm danh mục cần xóa
        for (Categories category : categories) {
            if (category.getId().equals(id)) {
                // Xóa danh mục
                categories.remove(category);
                break;
            }
        }
        // Lưu lại danh sách danh mục
        IoFileUtility.writeToFile(IoFileUtility.CATEGORIES_PATH, categories);
    }

    // Phương thức chọn danh mục theo index
    public static Categories selectCategory() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            // Hiển thị danh sách danh mục
            for (int i = 0; i < categories.size(); i++) {
                System.out.println((i + 1) + ". " + categories.get(i).getName());
            }
            System.out.print("Nhập số thứ tự của danh mục (0 để thoát): ");
            choice = scanner.nextInt();

            if (choice == 0) {
                return null; // Người dùng chọn thoát
            } else if (choice < 1 || choice > categories.size()) {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại!");
            } else {
                // Trả về danh mục tương ứng với lựa chọn ngay lập tức
                return categories.get(choice - 1);
            }
        } while (true);
    }

    // Các phương thức với đơn hàng
    // Phương thứuc hiển thị tất cả đơn hàng
    public static void showAllOrder() {
        // Hiển thị tất cả đơn hàng
        for (Order order : orderList) {
            System.out.println(order.toString());
        }
    }

    // Phuơng thức xem chi tiết đơn hàng
    public static void viewOrderDetail(String id) {
        // Tìm đơn hàng cần xem chi tiết
        for (Order order : orderList) {
            if (order.getOrderId().equals(id)) {
                // Hiển thị chi tiết đơn hàng
                System.out.println(order.getOrderDetails());
                break;
            }
        }
    }

    // Phương thứuc xác nhận đơn hàng
    public static void confirmOrder(String id) {
        // Tìm đơn hàng cần xác nhận
        for (Order order : orderList) {
            if (order.getOrderId().equals(id)) {
                // Xác nhận đơn hàng
                order.confirm();
                break;
            }
        }
        // Lưu lại danh sách đơn hàng
        IoFileUtility.writeToFile(IoFileUtility.ORDERS_PATH, orderList);
    }

    //Phương thứuc hủy đơn hàng
    public static void cancelOrder(String id) {
        // Tìm đơn hàng cần hủy
        for (Order order : orderList) {
            if (order.getOrderId().equals(id)) {
                // Hủy đơn hàng
                order.cancel();
                break;
            }
        }
        // Lưu lại danh sách đơn hàng
        IoFileUtility.writeToFile(IoFileUtility.ORDERS_PATH, orderList);
    }

    //Phương thức cập nhật dơn hàng
    public static void updateOrderStatus(String id) {
        // Tìm đơn hàng cần cập nhật
        for (Order order : orderList) {
            if (order.getOrderId().equals(id)) {
                // Cập nhật trạng thái đơn hàng
                order.nextStatus();
                break;
            }
        }
        // Lưu lại danh sách đơn hàng
        IoFileUtility.writeToFile(IoFileUtility.ORDERS_PATH, orderList);
    }

    // Phương thức tìm kiếm đơn hàng
    public static void searchOrder(String id) {
        // Tìm đơn hàng theo id
        for (Order order : orderList) {
            if (order.getOrderId().equals(id)) {
                // Hiển thị thông tin đơn hàng
                System.out.println(order.toString());
                break;
            }
        }
    }

    // Phương thức xóa đơn hàng
//    public static void deleteOrder(String id) {
//        // Tìm đơn hàng cần xóa
//        for (Order order : orderList) {
//            if (order.getOrderId().equals(id)) {
//                // Xóa đơn hàng
//                orderList.remove(order);
//                break;
//            }
//        }
//        // Lưu lại danh sách đơn hàng
//        IoFileUtility.writeToFile(IoFileUtility.ORDERS_PATH, orderList);
//    }
//
//    // Phương thức chuyển đổi trạng thái đơn hàng
//    public static void changeOrderStatus(String id) {
//        // Tìm đơn hàng cần chuyển trạng thái
//        for (Order order : orderList) {
//            if (order.getOrderId().equals(id)) {
//                // Chuyển đổi trạng thái của đơn hàng
//                order.nextStatus();
//                break;
//            }
//        }
//        // Lưu lại danh sách đơn hàng
//        IoFileUtility.writeToFile(IoFileUtility.ORDERS_PATH, orderList);
//    }


}
