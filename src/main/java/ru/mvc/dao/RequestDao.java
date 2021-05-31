package ru.mvc.dao;


import ru.mvc.model.Event;
import ru.mvc.model.Request;
import ru.mvc.model.User;
import ru.mvc.util.DBConnection;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDao extends AbstractDao<Request> {
    public String insert(Request request) {
        Event event = request.getEvent_id();
        String comment = request.getComment();
        User user = request.getSubscriber_id();


        Connection con = null;
        Connection con1 = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        ResultSet resultSet = null;


        try {
            con = DBConnection.createConnection();
            con1 = DBConnection.createConnection();
            preparedStatement1 = con1.prepareStatement("SELECT * FROM request where event_id=? ");
            preparedStatement1.setInt(1, event.getId());
            resultSet = preparedStatement1.executeQuery();
            while (resultSet.next()) {
                int subscriber = resultSet.getInt("subscriber_id");
                int event_id = resultSet.getInt("event_id");
                if (event.getId() == event_id & user.getId() == subscriber) {
                    return "вы уже участвуете в этом мероприятии";
                }
            }
            String query = "insert into request(event_id, subscriber_id, comment) values (?,?,?)";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, event.getId());
            preparedStatement.setInt(2, user.getId());
            preparedStatement.setString(3, comment);


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
            if (preparedStatement1 != null) {
                try {
                    preparedStatement1.close();
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
        return "Что-то пошло не так";
    }

    @Override
    public Request findById(int id) {
        return null;
    }

    @Override
    public void update(Request adr) {
    }


    @Override
    public List<Request> findAll() {
        return null;
    }

    public List<Request> findAllByEventId(int event_id) {
        List<Request> requests = new ArrayList<>();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            con = DBConnection.createConnection();
            preparedStatement = con.prepareStatement("SELECT * FROM request where event_id=? ");
            preparedStatement.setInt(1, event_id);
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                int subscriber = resultSet.getInt("subscriber_id");
                String comment = resultSet.getString("comment");

                UserDao userDao = new UserDao();
                User user = userDao.findById(subscriber);
                EventDao eventDao = new EventDao();
                Event event = eventDao.findById(event_id);
                Request request = new Request();
                request.setSubscriber_id(user);
                request.setComment(comment);
                request.setEvent_id(event);


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

        return requests;
    }


    public List<Event> findAllByUserId(int user_id) {
        List<Event> requests = new ArrayList<>();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            con = DBConnection.createConnection();
            preparedStatement = con.prepareStatement("SELECT * FROM request where subscriber_id=? ");
            preparedStatement.setInt(1, user_id);
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                int subscriber = resultSet.getInt("subscriber_id");
                String comment = resultSet.getString("comment");
                int event_id = resultSet.getInt("event_id");

                UserDao userDao = new UserDao();
                User user = userDao.findById(subscriber);
                EventDao eventDao = new EventDao();
                Event event = eventDao.findById(event_id);
                Request request = new Request();
                request.setSubscriber_id(user);
                request.setComment(comment);
                request.setEvent_id(event);


                requests.add(event);
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

        return requests;
    }

    public void delete(int event_id, int user_id) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = DBConnection.createConnection();
            preparedStatement = con.prepareStatement("DELETE FROM request where event_id=? and subscriber_id=?");
            preparedStatement.setInt(1, event_id);
            preparedStatement.setInt(2, user_id);
            int row = preparedStatement.executeUpdate();
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
}