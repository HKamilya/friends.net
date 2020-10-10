package ru.mvc.dao;

import ru.mvc.model.Categories;
import ru.mvc.model.Events;
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

    public Categories getCategoryById(int id) {
        Categories categories = new Categories();
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            con = DBConnection.createConnection();
            String sql = "SELECT * FROM categories where id=" + id;
            statement = con.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                categories.setId(id);
                categories.setDescription(description);
                categories.setName(name);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}

