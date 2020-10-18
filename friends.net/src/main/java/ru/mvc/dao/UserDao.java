package ru.mvc.dao;


import ru.mvc.model.User;
import ru.mvc.util.DBConnection;

import java.sql.*;
import java.util.List;

public class UserDao extends AbstractDao<User> {
    public String insert(User user) {
        String fullName = user.getFullName();
        String email = user.getEmail();
        String userName = user.getUserName();
        String password = user.getPassword();

        Connection con = null;
        PreparedStatement preparedStatement = null;


        try {
            con = DBConnection.createConnection();
            String query = "insert into \"user\"(fullname,email,username,password) values (?,?,?,?)";
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
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ignore) {
                }
            }
        }
        return "Вероятно такой пользователь уже существует";

    }

    public String authenticateUser(User loginBean) {
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

        return "Введен неверный логин или пароль";
    }

    public User findByName(String username) {
        User user = new User();
        Connection con = null;
        PreparedStatement ps = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            con = DBConnection.createConnection();
            ps = con.prepareStatement("SELECT * FROM \"user\" where username=?");
            ps.setString(1, username);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int user_id = resultSet.getInt("id");
                String fullname = resultSet.getString("fullname");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String image = resultSet.getString("image");
                String description = resultSet.getString("description");

                user.setId(user_id);
                user.setEmail(email);
                user.setFullName(fullname);
                user.setImage(image);
                user.setPassword(password);
                user.setDescription(description);
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

        return user;

    }

    public String updateUsersData(User user) {
        String fullName = user.getFullName();
        String description = user.getDescription();

        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = DBConnection.createConnection();
            preparedStatement = con.prepareStatement("update \"user\" set fullname=?, description=?, image=? where username=?");
            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, user.getImage());
            preparedStatement.setString(4, user.getUserName());


            int i = preparedStatement.executeUpdate();

            if (i != 0)
                return "SUCCESS";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }
        return "Что-то пошло не так";
    }

    public User findById(int id) {
        User user = new User();
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

                user.setId(id);
                user.setEmail(email);
                user.setUserName(username);
                user.setFullName(fullname);
                user.setImage(image);
                user.setPassword(password);
                user.setDescription(description);
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

        return user;
    }

    @Override
    public void update(User adr) {

    }

    @Override
    public void delete(User adr) {

    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
