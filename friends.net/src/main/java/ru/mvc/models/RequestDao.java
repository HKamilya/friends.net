package ru.mvc.models;


import ru.mvc.bean.Request;
import ru.mvc.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDao {
    public String addRequest(String username, Request request) {
        int event_id = request.getEvent_id();
        String comment = request.getComment();


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
            String query = "insert into request(event_id, subscriber, comment) values (?,?,?)"; //Insert user details into the table 'USERS'
            preparedStatement = con.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
            preparedStatement.setInt(1, event_id);
            preparedStatement.setInt(2, user_id);
            preparedStatement.setString(3, comment);


            int i = preparedStatement.executeUpdate();

            if (i != 0)
                return "SUCCESS";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Something went wrong";
    }

    public List<Request> getAllRequests(int event_id) {
        List<Request> requests = new ArrayList<>();
        Request res = null;
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            con = DBConnection.createConnection();
            String sql = "SELECT * FROM request where event_id=" + event_id;
            statement = con.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int event_id1 = resultSet.getInt("event_id");
                int subscriber = resultSet.getInt("subscriber");
                String comment = resultSet.getString("comment");

                Request request = new Request(event_id1, subscriber, comment);
                requests.add(request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }
}
