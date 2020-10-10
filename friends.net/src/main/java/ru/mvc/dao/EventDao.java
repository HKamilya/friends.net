package ru.mvc.dao;


import ru.mvc.model.Categories;
import ru.mvc.model.Events;
import ru.mvc.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDao {
    public String addEvent(String username, Events events) {
        String name = events.getName();
        String city = events.getCity();
        String street = events.getStreet();
        String house = events.getHouse();
        String date = events.getDatetime();
        String image = events.getImage();
        String description = events.getDescription();
        Categories category = events.getCategory();
        String status = events.getStatus();


        Connection con = null;
        Connection con1 = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet result = null;
        int user_id = 0;
        try {
            con = DBConnection.createConnection();
            con1 = DBConnection.createConnection();
            String query1 = "select id from \"user\" where username ='" + username + "'";
            statement = con1.createStatement();
            result = statement.executeQuery(query1);
            while (result.next()) {
                user_id = result.getInt(1);
            }
            System.out.println(user_id);
            String query = "insert into event(user_id,name,city,street,house,image,description,category_id,status,date) values (?,?,?,?,?,?,?,?,?,?)"; //Insert user details into the table 'USERS'
            preparedStatement = con.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
            preparedStatement.setInt(1, user_id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, city);
            preparedStatement.setString(4, street);
            preparedStatement.setString(5, house);
            preparedStatement.setString(6, image);
            preparedStatement.setString(7, description);
            preparedStatement.setInt(8, category.getId());
            preparedStatement.setString(9, status);
            preparedStatement.setString(10, date);
            int i = preparedStatement.executeUpdate();

            if (i != 0)
                return "SUCCESS";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Something went wrong";
    }

    public Events getRandomEvent() {
        List<Events> events = new ArrayList<>();
        Events res = null;
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            con = DBConnection.createConnection();
            String sql = "SELECT * FROM event where status='актуально'";
            statement = con.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int user_id = resultSet.getInt("user_id");
                String name = resultSet.getString("name");
                String city = resultSet.getString("city");
                String street = resultSet.getString("street");
                String house = resultSet.getString("house");
                String image = resultSet.getString("image");
                String description = resultSet.getString("description");
                int category_id = resultSet.getInt("category_id");
                String status = resultSet.getString("status");
                String date = resultSet.getString("date");
                CategoriesDao categoriesDao = new CategoriesDao();
                Categories category = categoriesDao.getCategoryById(category_id);

                Events event = new Events(id, user_id, name, city, street, house, date, image, description, category, status);

                events.add(event);
            }
            int a = (int) (Math.random() * events.size());

            res = events.get(a);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public List<Events> getAllEvents() {
        List<Events> events = new ArrayList<>();
        Events res = null;
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            con = DBConnection.createConnection();
            String sql = "SELECT * FROM event where status='актуально'";
            statement = con.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int user_id = resultSet.getInt("user_id");
                String name = resultSet.getString("name");
                String city = resultSet.getString("city");
                String street = resultSet.getString("street");
                String house = resultSet.getString("house");
                String image = resultSet.getString("image");
                String description = resultSet.getString("description");
                int category_id = resultSet.getInt("category_id");
                String status = resultSet.getString("status");
                String date = resultSet.getString("date");
                CategoriesDao categoriesDao = new CategoriesDao();
                Categories category = categoriesDao.getCategoryById(category_id);

                Events event = new Events(id, user_id, name, city, street, house, date, image, description, category, status);

                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    public List<Events> getAllUsersEvents(int id) {
        List<Events> events = new ArrayList<>();
        Events res = null;
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            con = DBConnection.createConnection();
            String sql = "SELECT * FROM event where user_id=" + id;
            statement = con.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int event_id = resultSet.getInt("id");
                int user_id = resultSet.getInt("user_id");
                String name = resultSet.getString("name");
                String city = resultSet.getString("city");
                String street = resultSet.getString("street");
                String house = resultSet.getString("house");
                String image = resultSet.getString("image");
                String description = resultSet.getString("description");
                int category_id = resultSet.getInt("category_id");
                String status = resultSet.getString("status");
                String date = resultSet.getString("date");
                CategoriesDao categoriesDao = new CategoriesDao();
                Categories category = categoriesDao.getCategoryById(category_id);

                Events event = new Events(event_id, user_id, name, city, street, house, date, image, description, category, status);

                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    public Events getEvent(int id) {
        List<Events> events = new ArrayList<>();
        Events res = null;
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            con = DBConnection.createConnection();
            String sql = "SELECT * FROM event where id=" + id;
            statement = con.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int user_id = resultSet.getInt("user_id");
                String name = resultSet.getString("name");
                String city = resultSet.getString("city");
                String street = resultSet.getString("street");
                String house = resultSet.getString("house");
                String image = resultSet.getString("image");
                String description = resultSet.getString("description");
                int category_id = resultSet.getInt("category_id");
                String status = resultSet.getString("status");
                String date = resultSet.getString("date");
                CategoriesDao categoriesDao = new CategoriesDao();
                Categories category = categoriesDao.getCategoryById(category_id);

                Events event = new Events(id, user_id, name, city, street, house, date, image, description, category, status);

                events.add(event);
            }
            int a = (int) (Math.random() * events.size());

            res = events.get(a);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public void updateStatus(int id, String status) {

        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = DBConnection.createConnection();
            String query = "update event set status=? where id=" + id;
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, status);

            int i = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
