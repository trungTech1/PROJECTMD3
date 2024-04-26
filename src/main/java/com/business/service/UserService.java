package main.java.com.business.service;

import main.java.com.business.interfaces.IUserService;
import main.java.com.business.models.User;
import main.java.com.business.ultil.otherFile.*;
import main.java.com.business.ultil.validation.UserValidation;

import java.util.List;
import java.util.Optional;

public class UserService implements IUserService {
    public static List<User> userList = IoFileUtility.readFromFile(IoFileUtility.USERS_PATH, User.class);

    private int getNewId() {
        int newId = 0;
        // Tìm id lớn nhất trong danh sách người dùng
        for(User user : userList) {
            if(user.getId() > newId) {
                newId = user.getId();
            }
        }
        // Tăng id lớn nhất thêm 1 để tạo id mới
        newId += 1;
        return newId;
    }

    // Phương thức để đăng ký người dùng
    @Override
    public void registerUser(User newUser) {
       // Thêm người dùng mới vào danh sách txt
        newUser.setId(getNewId());
        userList.add(newUser);
        System.out.println("Đăng ký thành công");
        // Lưu lại danh sách người dùng
        IoFileUtility.writeToFile(IoFileUtility.USERS_PATH, userList);
    }

    // Phương thức để đăng nhập
    public static User loginUser( String username, String password){
        // Đảm bảo userList được khởi tạo
        if (userList == null) {
            System.err.println("Danh sách người dùng không tồn tại.");
            return null;
        }
        // Kiểm tra đăng nhập
        for (User user : userList) {
            if (user.getName().equals(username) && user.getPassword().equals(password)) {
                System.out.println("Đăng nhập thành công");
                return user;
            }
        }
        System.err.println("Username or password is incorrect.");
        return null;
    }

    // Phương thức update thông tin người dùng
    public static void updateUser(int userId){
        // Lấy danh sách người dùng từ file
        List<User> userList = IoFileUtility.readFromFile(IoFileUtility.USERS_PATH, User.class);
        // Tìm người dùng cần update thông tin và thực hện update
        Optional<User> userToUpdate = userList.stream().filter(u -> u.getId() == userId).findFirst();
        // Nếu tìm thấy người dùng, thực hiện cập nhật thông tin
        userToUpdate.ifPresent(user -> {
            user.inputData(false);
            System.out.println("Bạn có muốn cập nhật thông tin không? (Y/N)");
            boolean isUpdate = InputMethods.getBoolean();
            if(isUpdate) {
                System.out.println("Do you want to change your email? (Y/N)");
                boolean changeEmail = InputMethods.getBoolean();
                if(changeEmail) {
                    System.out.println("Nhập email mới: ");
                    while (true) {
                        String newEmail = InputMethods.getString();
                        if (UserValidation.isValidEmail(newEmail)) {
                            user.setEmail(newEmail);
                        } else {
                            System.err.println("Email không hợp lệ. Vui lòng nhập lại.");
                        }
                    }
                }
                System.out.println("Ban co muon thay doi so dien thoai cua minh khong? (Y/N)");
                boolean changePhone = InputMethods.getBoolean();
                if(changePhone) {
                    System.out.println("Enter your new phone number: ");
                    while (true) {
                        String newPhone = InputMethods.getString();
                        if (UserValidation.isValidPhone(newPhone)) {
                            user.setPhone(newPhone);
                        } else {
                            System.err.println("So dien thoai khong hop le. Vui long nhap lai.");
                        }
                    }
                }
            }
            System.out.println("Cập nhật thông tin thành công");
            // Lưu lại danh sách người dùng
            IoFileUtility.writeToFile(IoFileUtility.USERS_PATH, userList);
        });

    }
    public static void display(User user) {
        System.out.println(user.toString());
    }

    public static void logout() {
        IoFileUtility.writeToFile(IoFileUtility.USER_LOGIN_PATH, null);
    }

    public void changePassword(){
        // Lấy danh sách người dùng từ file
        List<User> userList = IoFileUtility.readFromFile(IoFileUtility.USERS_PATH, User.class);

        System.out.println("Nhập tên đăng nhập: ");
        String username = InputMethods.getString();
        System.out.println("Nhập mật khẩu cũ: ");
        String oldPassword = InputMethods.getString();
        System.out.println("Nhập mật khẩu mới: ");
        String newPassword = InputMethods.getString();

        // Kiểm tra danh sách người dùng có tồn tại hay không
        if(userList == null) {
            System.err.println("User list is not available.");
            return;
        }
        // Kiểm tra thông tin đăng nhập
        for (User user : userList) {
            if (user.getName().equals(username) && user.getPassword().equals(oldPassword)) {
                user.setPassword(newPassword);
                System.out.println("Password changed successfully");
                // Lưu lại danh sách người dùng
                IoFileUtility.writeToFile(IoFileUtility.USERS_PATH, userList);
                return;
            }
            else {
                System.err.println("Username or password is incorrect.");
            }
        }

    }
    public void deleteUser(int userId){
        // Lấy danh sách người dùng từ file
        List<User> userList = IoFileUtility.readFromFile(IoFileUtility.USERS_PATH, User.class);
        // Kiểm tra danh sách người dùng có tồn tại hay không
        if(userList == null) {
            System.err.println("User list is not available.");
            return;
        }
        // Xóa người dùng khỏi danh sách
        userList.removeIf(user -> user.getId() == userId);
        System.out.println("User deleted successfully");
        // Lưu lại danh sách người dùng
        IoFileUtility.writeToFile(IoFileUtility.USERS_PATH, userList);
    }
    public void displayAllUsers(){
        // Lấy danh sách người dùng từ file
        List<User> userList = IoFileUtility.readFromFile(IoFileUtility.USERS_PATH, User.class);
        // Kiểm tra danh sách người dùng có tồn tại hay không
        if(userList == null) {
            System.err.println("User list is not available.");
            return;
        }
        // Hiển thị danh sách người dùng
        for(User user : userList) {
            System.out.println(user);
        }
    }
    public User getUserById(int userId){
        // Lấy danh sách người dùng từ file
        List<User> userList = IoFileUtility.readFromFile(IoFileUtility.USERS_PATH, User.class);
        // Kiểm tra danh sách người dùng có tồn tại hay không
        if(userList == null) {
            System.err.println("User list is not available.");
            return null;
        }
        // Tìm người dùng theo id
        Optional<User> user = userList.stream().filter(u -> u.getId() == userId).findFirst();
        // Trả về người dùng nếu tìm thấy
        return user.orElse(null);
    }
}
