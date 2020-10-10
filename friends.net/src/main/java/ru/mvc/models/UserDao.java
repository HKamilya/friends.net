package ru.mvc.models;


import ru.mvc.bean.Users;
import ru.mvc.util.DBConnection;

import java.sql.*;

public class UserDao {
    public String registerUser(Users users) {
        String fullName = users.getFullName();
        String email = users.getEmail();
        String userName = users.getUserName();
        String password = users.getPassword();

        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = DBConnection.createConnection();
            String query = "insert into \"user\"(fullname,email,username,password) values (?,?,?,?)"; //Insert user details into the table 'USERS'
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, userName);
            preparedStatement.setString(4, password);

            int i = preparedStatement.executeUpdate();

            if (i != 0)
                return "SUCCESS";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Something went wrong";

    }

    public String authenticateUser(Users loginBean) {
        String userName = loginBean.getUserName();
        String password = loginBean.getPassword();

        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        System.out.println(password);
        String userNameDB = "";
        String passwordDB = "";

        try {
            con = DBConnection.createConnection();
            statement = con.createStatement();
            resultSet = statement.executeQuery("select username,password from \"user\"");

            while (resultSet.next()) {
                userNameDB = resultSet.getString("username");
                passwordDB = resultSet.getString("password");


                if (userName.equals(userNameDB) && password.equals(passwordDB))
                    return "User_Role";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Invalid user credentials";
    }

    public Users getInfo(String username) {
        Users user = new Users();
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            con = DBConnection.createConnection();
            String sql = "SELECT * FROM \"user\" where username=" + "'" + username + "'";
            statement = con.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int user_id = resultSet.getInt("id");
                String fullname = resultSet.getString("fullname");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String image = resultSet.getString("image");
                String description = resultSet.getString("description");

                user.setEmail(email);
                user.setFullName(fullname);
                user.setImage(image);
                user.setPassword(password);
                user.setDescription(description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;

    }

    public String updateUsersData(Users users) {
        String fullName = users.getFullName();
        String description = users.getDescription();

        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = DBConnection.createConnection();
            String query = "update \"user\" set fullname=?, description=? where username=" + "'" + users.getUserName() + "'";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, description);


            int i = preparedStatement.executeUpdate();

            if (i != 0)
                return "SUCCESS";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Something went wrong";
    }

    public Users findById(int id) {
        Users user = new Users();
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            con = DBConnection.createConnection();
            String sql = "SELECT * FROM \"user\" where id=" + id;
            statement = con.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String fullname = resultSet.getString("fullname");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String image = resultSet.getString("image");
                String description = resultSet.getString("description");

                user.setEmail(email);
                user.setUserName(username);
                user.setFullName(fullname);
                user.setImage(image);
                user.setPassword(password);
                user.setDescription(description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
