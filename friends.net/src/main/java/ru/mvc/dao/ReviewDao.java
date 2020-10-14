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

                UserDao userDao = new UserDao();
                User user = userDao.findById(user_id);
                EventDao eventDao = new EventDao();
                Event event = eventDao.findById(event_id);

                Review review = new Review();
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

        int user_id = 0;
        try {
            con = DBConnection.createConnection();
            System.out.println(user_id);
            String query = "insert into review(event_id,user_id,text) values (?,?,?)";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, review.getUser().getId());
            preparedStatement.setInt(2, review.getEvent().getId());
            preparedStatement.setString(3, text);


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

        return "Something went wrong";
    }

    @Override
    public Review findById(int id) {
        return null;
    }
}
