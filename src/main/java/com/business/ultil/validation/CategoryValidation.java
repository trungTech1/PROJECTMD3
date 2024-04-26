package main.java.com.business.ultil.validation;

public class CategoryValidation {

    public static boolean checkCategoryName(String name) {
        return !name.isEmpty();
    }
    public static boolean checkCategoryDescription(String description) {
        return !description.isEmpty();
    }
    public static boolean checkCategoryStatus(String status) {
        return status.matches("^[a-zA-Z0-9 ]{1,50}$");
    }
}
