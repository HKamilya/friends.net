package ru.mvc.dao;

import ru.mvc.model.SupportMessage;
import ru.mvc.util.DBConnection;

import java.sql.*;

public class SupportMessageDao {
    public void addMessage(SupportMessage supportMessage) {
        Connection con = null;
        PreparedStatement preparedStatement = null;

        try {
            con = DBConnection.createConnection();
            String query = "insert into \"supportMessage\"(email,title,text) values (?,?,?)";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, supportMessage.getEmail());
            preparedStatement.setString(2, supportMessage.getTitle());
            preparedStatement.setString(3, supportMessage.getText());


            int i = preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }
    }
}

