package main.java.com.business.ultil.otherFile;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IoFileUtility {
    public static final String USERS_PATH = "src/main/java/com/business/data/users.txt";
    public static final String USER_LOGIN_PATH = "src/main/java/com/business/data/userLogin.txt";
    public static final String PRODUCTS_PATH = "src/main/java/com/business/data/products.txt";
    public static final String CARTS_PATH = "src/main/java/com/business/data/carts.txt";
    public static final String ORDERS_PATH = "src/main/java/com/business/data/orders.txt";
    public static final String CATEGORIES_PATH = "src/main/java/com/business/data/categories.txt";
    public static <T> void writeToFile( String path,List<T> list) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> void writeToFile2(T obj, String path) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> List<T> readFromFile(String path, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        File file = new File(path);
        if (file.exists() && file.length() > 0) {
            try (FileInputStream fis = new FileInputStream(path);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                Object obj = ois.readObject();

                if (obj instanceof List ) {
                    for (Object element : (List<?>) obj) {
                        list.add(clazz.cast(element));
                    }
                } else {
                    list.add(clazz.cast(obj));
                }
            } catch (EOFException e) {
                System.out.println("Đã đọc hết file: " + path);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
