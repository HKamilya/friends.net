package ru.mvc.models;

import ru.mvc.bean.Events;
import ru.mvc.bean.Review;
import ru.mvc.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao {
    public List<Review> getReviews(int id) {
        List<Review> reviews = new ArrayList<>();
        Events res = null;
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            con = DBConnection.createConnection();
            String sql = "SELECT * FROM review where event_id=" + id;
            statement = con.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int user_id = resultSet.getInt("user_id");
                int event_id = resultSet.getInt("event_id");
                String text = resultSet.getString("text");

                Review review = new Review(user_id, event_id, text);
                reviews.add(review);
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    public String addReview(String username, Review review) {
        String text = review.getText();
        int event_id = review.getEvent_id();

        Connection con = null;
        Connection con1 = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet result = null;
        int user_id = 0;
        try {
            con = DBConnection.createConnection();
            con1 = DBConnection.createConnection();
            String query1 = "select id from users where username ='" + username + "'";
            statement = con1.createStatement();
            result = statement.executeQuery(query1);
            while (result.next()) {
                user_id = result.getInt(1);
            }
            System.out.println(user_id);
            String query = "insert into review(user_id,event_id,text) values (?,?,?)"; //Insert user details into the table 'USERS'
            preparedStatement = con.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, event_id);
            preparedStatement.setString(3, text);


            int i = preparedStatement.executeUpdate();

            if (i != 0)  //Just to ensure data has been inserted into the database
                return "SUCCESS";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Oops.. Something went wrong there..!";  // On failure, send a message from here.
    }

}
