package main.java.com.business.models;
import java.io.Serializable;

public class Categories implements Serializable{
    private String id;
    private String name;
    private String description;

    public Categories() {
    }

    public Categories(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Categories(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
