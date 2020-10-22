package ru.mvc.dao;

import ru.mvc.model.Image;
import ru.mvc.util.DBConnection;

import java.sql.*;
import java.util.List;

public class ImageDao extends AbstractDao<Image> {
    @Override
    public String insert(Image adr) {

        Connection con = null;

        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int id = 0;

        try {
            con = DBConnection.createConnection();
            String query = "insert into image(type, address) values (?,?) returning id";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, adr.getType());
            preparedStatement.setString(2, adr.getAddress());

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
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
        return String.valueOf(id);
    }

    public void update(Image image) {
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            con = DBConnection.createConnection();
            preparedStatement = con.prepareStatement("update image set address=?, type=? where id=?");
            preparedStatement.setString(1, image.getAddress());
            preparedStatement.setString(2, image.getType());
            preparedStatement.setInt(3, image.getId());

            int i = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
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
    }

    @Override
    public Image findById(int id) {
        Image image = new Image();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            con = DBConnection.createConnection();
            preparedStatement = con.prepareStatement("SELECT * FROM image where id=? ");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                image.setId(resultSet.getInt("id"));
                image.setType(resultSet.getString("type"));
                image.setAddress(resultSet.getString("address"));
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

        return image;
    }


    @Override
    public List<Image> findAll() {
        return null;
    }
}
