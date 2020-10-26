package ru.mvc.dao;

import ru.mvc.model.SupportMessage;
import ru.mvc.util.DBConnection;

import java.sql.*;
import java.util.List;

public class SupportMessageDao extends AbstractDao<SupportMessage> {
    public String insert(SupportMessage supportMessage) {
        Connection con = null;
        PreparedStatement preparedStatement = null;

        try {
            con = DBConnection.createConnection();
            String query = "insert into \"supportMessage\"(email,title,text, date ) values (?,?,?,?)";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, supportMessage.getEmail());
            preparedStatement.setString(2, supportMessage.getTitle());
            preparedStatement.setString(3, supportMessage.getText());
            preparedStatement.setString(4, supportMessage.getDate());

            int i = preparedStatement.executeUpdate();
            if (i != 0) {
                return "SUCCESS";
            }

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

    @Override
    public SupportMessage findById(int id) {
        return null;
    }

    @Override
    public void update(SupportMessage adr) {

    }


    @Override
    public List<SupportMessage> findAll() {
        return null;
    }
}

