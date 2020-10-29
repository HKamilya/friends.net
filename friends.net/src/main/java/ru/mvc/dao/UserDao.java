package ru.mvc.dao;


import ru.mvc.model.Image;
import ru.mvc.model.User;
import ru.mvc.util.DBConnection;

import javax.jws.soap.SOAPBinding;
import java.sql.*;
import java.util.List;

public class UserDao extends AbstractDao<User> {
    public String insert(User user) {
        String fullName = user.getFullname();
        String email = user.getEmail();
        String userName = user.getUsername();
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

    public User authenticateUser(User loginBean) {
        String userName = loginBean.getUsername();
        String password = loginBean.getPassword();

        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String userNameDB = "";
        String passwordDB = "";

        try {
            con = DBConnection.createConnection();
            preparedStatement = con.prepareStatement("SELECT * FROM \"user\" where username=? and password=?");
            preparedStatement.setString(1, loginBean.getUsername());
            preparedStatement.setString(2, loginBean.getPassword());

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setFullname(resultSet.getString("fullname"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setDescription(resultSet.getString("description"));
                ImageDao imageDao = new ImageDao();
                Image image = imageDao.findById(resultSet.getInt("image"));
                user.setImage(image);
                user.setId(resultSet.getInt("id"));

                return user;
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

        return null;
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
                int imageId = resultSet.getInt("image");
                String description = resultSet.getString("description");

                user.setId(user_id);
                user.setEmail(email);
                user.setFullname(fullname);
                ImageDao imageDao = new ImageDao();
                Image image = imageDao.findById(imageId);
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

    public void update(User user) {
        String fullName = user.getFullname();
        String description = user.getDescription();

        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = DBConnection.createConnection();
            preparedStatement = con.prepareStatement("update \"user\" set fullname=?, description=?, image=? where username=?");
            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, user.getImage().getId());
            preparedStatement.setString(4, user.getUsername());


            int i = preparedStatement.executeUpdate();

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
    }

    public User findById(int id) {
        User user = new User();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            con = DBConnection.createConnection();
            preparedStatement = con.prepareStatement("SELECT * FROM \"user\" where id=? ");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String fullname = resultSet.getString("fullname");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                int imageId = resultSet.getInt("image");
                String description = resultSet.getString("description");

                user.setId(id);
                user.setEmail(email);
                user.setUsername(username);
                user.setFullname(fullname);
                ImageDao imageDao = new ImageDao();
                Image image = imageDao.findById(imageId);
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

        return user;
    }


    @Override
    public List<User> findAll() {
        return null;
    }
}
