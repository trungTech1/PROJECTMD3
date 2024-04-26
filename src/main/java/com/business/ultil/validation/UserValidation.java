package main.java.com.business.ultil.validation;

public class UserValidation {
    public static boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_]+@[a-zA-Z]+\\.[a-zA-Z]+$";
        return email.matches(regex);
    }

    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
        return password.matches(regex);
    }

    public static boolean isValidPhone(String phone) {
        String regex = "^(\\+84|0)\\d{9,10}$";
        return phone.matches(regex);
    }
}
