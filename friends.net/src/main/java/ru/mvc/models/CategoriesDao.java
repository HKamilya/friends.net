package ru.mvc.models;

import ru.mvc.bean.Categories;
import ru.mvc.bean.Events;
import ru.mvc.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDao {

    public List<Categories> getAllCategories() {
        List<Categories> categories = new ArrayList<>();
        Events res = null;
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            con = DBConnection.createConnection();
            String sql = "SELECT * FROM categories";
            statement = con.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");

                Categories category = new Categories(id, name, description);

                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public String getName(int id) {
        String name = null;
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            con = DBConnection.createConnection();
            String sql = "SELECT name FROM categories where id=" + id;
            statement = con.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                name = resultSet.getString(1);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }
}

