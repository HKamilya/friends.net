package ru.mvc.bean;

public class Events {
    private int id;
    private int user_id;
    private String name;
    private String city;
    private String street;
    private String house;
    private String image;
    private String description;
    private int category;
    private String status;

    public Events() {
    }

    public Events(int id, int user_id, String name, String city, String street, String house, String image, String description, int category, String status) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.city = city;
        this.street = street;
        this.house = house;
        this.image = image;
        this.description = description;
        this.category = category;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
