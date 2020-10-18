package ru.mvc.dao;

import ru.mvc.model.Categories;
import ru.mvc.model.Event;
import ru.mvc.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDao implements DaoInterface<Categories> {

    public List<Categories> getAllCategories() {
        List<Categories> categories = new ArrayList<>();
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


                Categories category = new Categories();
                category.setDescription(description);
                category.setId(id);
                category.setName(name);


                categories.add(category);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ignore) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignore) {
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }

        return categories;
    }

    public Categories findById(int id) {
        Categories categories = new Categories();
        Connection con = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            con = DBConnection.createConnection();
            preparedStatement = con.prepareStatement("SELECT * FROM categories where id=?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                categories.setId(id);
                categories.setDescription(description);
                categories.setName(name);

            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ignore) {
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ignore) {
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }

        return categories;
    }
}

