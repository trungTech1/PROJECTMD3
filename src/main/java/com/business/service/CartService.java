package main.java.com.business.service;

import main.java.com.business.models.Cart;
import main.java.com.business.models.Product;
import main.java.com.business.ultil.otherFile.IoFileUtility;

import java.util.List;

public class CartService {
    // Lấy dữ liệu từ file và khởi tạo giỏ hàng

    public static List<Cart> carts = IoFileUtility.readFromFile(IoFileUtility.CARTS_PATH, Cart.class);
   public static List<Product> products = IoFileUtility.readFromFile(IoFileUtility.PRODUCTS_PATH, Product.class);
    // Phương thức thêm sản phẩm vào giỏ hàng
    public static void addProductToCart(Cart cart) {
        // Thêm sản phẩm vào giỏ hàng
        carts.add(cart);
        // Lưu lại danh sách giỏ hàng
        IoFileUtility.writeToFile(IoFileUtility.CARTS_PATH, carts);


    }
   //kiểm tra số lượng tồn kho
    public static boolean checkQuantity(String id, int quantity) {
        // Tìm sản phẩm dựa trên id
        Product product = products.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        if (product == null) {
            return false;
        }
        // Kiểm tra số lượng tồn kho
        return product.getQuantity() >= quantity;
    }
    public static void removeProductFromCart(String id) {
        // Xóa sản phẩm khỏi giỏ hàng
        for (Cart cart : carts) {
            for (int i = 0; i < cart.getCartItems().size(); i++) {
                if (cart.getCartItems().get(i).getProduct().getId().equals(id)) {
                    cart.getCartItems().remove(i);
                    break;
                }
            }
        }
        // Lưu lại danh sách giỏ hàng
        IoFileUtility.writeToFile(IoFileUtility.CARTS_PATH, carts);
    }

    //xóa giỏ hàng trong txt
    public static void removeCart() {
        // Xóa giỏ hàng
        carts.clear();
        // Lưu lại danh sách giỏ hàng
        IoFileUtility.writeToFile(IoFileUtility.CARTS_PATH, carts);
    }


}
