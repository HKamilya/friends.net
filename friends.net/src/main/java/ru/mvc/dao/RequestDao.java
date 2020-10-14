package ru.mvc.dao;


import ru.mvc.model.Event;
import ru.mvc.model.Request;
import ru.mvc.model.User;
import ru.mvc.util.DBConnection;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDao {
    public String addRequest(Request request) {
        Event event = request.getEvent();
        String comment = request.getComment();
        User user = request.getSubscriber();


        Connection con = null;
        Connection con1 = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try {
            con = DBConnection.createConnection();
            con1 = DBConnection.createConnection();
            String sql = "SELECT * FROM request where event_id=" + event.getId();
            statement = con1.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int subscriber = resultSet.getInt("subscriber");
                int event_id = resultSet.getInt("event_id");
                if (event.getId() == event_id & user.getId() == subscriber) {
                    return "вы уже участвуете в этом мероприятии";
                }
            }
            String query = "insert into request(event_id, subscriber, comment) values (?,?,?)";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, event.getId());
            preparedStatement.setInt(2, user.getId());
            preparedStatement.setString(3, comment);


            int i = preparedStatement.executeUpdate();

            if (i != 0)
                return "SUCCESS";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ignore) {
            }
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
                int subscriber = resultSet.getInt("subscriber");
                String comment = resultSet.getString("comment");

                UserDao userDao = new UserDao();
                User user = userDao.findById(subscriber);
                EventDao eventDao = new EventDao();
                Event event = eventDao.findById(event_id);
                Request request = new Request();
                request.setSubscriber(user);
                request.setComment(comment);
                request.setEvent(event);

                requests.add(request);
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

        return requests;
    }
}
