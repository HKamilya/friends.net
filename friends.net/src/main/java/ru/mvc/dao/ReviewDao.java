package ru.mvc.dao;

import ru.mvc.model.Event;
import ru.mvc.model.Review;
import ru.mvc.model.User;
import ru.mvc.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao implements DaoInterface<Review> {
    public List<Review> getReviews(int id) {
        List<Review> reviews = new ArrayList<>();
        Event res = null;
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            con = DBConnection.createConnection();
            String sql = "SELECT * FROM  review  where event_id=" + id;
            statement = con.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int user_id = resultSet.getInt("user_id");
                int event_id = resultSet.getInt("event_id");
                String text = resultSet.getString("text");
                String date = resultSet.getString("date");

                UserDao userDao = new UserDao();
                User user = userDao.findById(user_id);
                EventDao eventDao = new EventDao();
                Event event = eventDao.findById(event_id);

                Review review = new Review();
                review.setDate(date);
                review.setEvent(event);
                review.setUser(user);
                review.setText(text);
                reviews.add(review);
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

        return reviews;
    }

    public String addReview(Review review) {
        String text = review.getText();

        Connection con = null;

        PreparedStatement preparedStatement = null;
        Statement statement = null;

        try {
            con = DBConnection.createConnection();
            String query = "insert into review(user_id,event_id,text,date) values (?,?,?,?)";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, review.getUser().getId());
            preparedStatement.setInt(2, review.getEvent().getId());
            preparedStatement.setString(3, text);
            preparedStatement.setString(4, review.getDate());


            int i = preparedStatement.executeUpdate();

            if (i != 0)
                return "SUCCESS";
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

        return "Что-то пошло не так";
    }

    @Override
    public Review findById(int id) {
        return null;
    }
}
