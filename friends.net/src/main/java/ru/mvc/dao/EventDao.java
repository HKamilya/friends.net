package ru.mvc.dao;


import ru.mvc.model.Categories;
import ru.mvc.model.Event;
import ru.mvc.model.User;
import ru.mvc.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDao implements DaoInterface<Event> {
    public String addEvent(Event event) {
        String name = event.getName();
        String city = event.getCity();
        String street = event.getStreet();
        String house = event.getHouse();
        String date = event.getDate();
        String image = event.getImage();
        String description = event.getDescription();
        Categories category = event.getCategory();
        String status = event.getStatus();
        User user = event.getUser();


        Connection con = null;

        PreparedStatement preparedStatement = null;
        Statement statement = null;

        try {
            con = DBConnection.createConnection();

            String query = "insert into event(user_id,name,city,street,house,image,description,category_id,status,date) values (?,?,?,?,?,?,?,?,?,?)"; //Insert user details into the table 'USERS'
            preparedStatement = con.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, city);
            preparedStatement.setString(4, street);
            preparedStatement.setString(5, house);
            preparedStatement.setString(6, image);
            preparedStatement.setString(7, description);
            preparedStatement.setInt(8, category.getId());
            preparedStatement.setString(9, status);
            preparedStatement.setString(10, date);
            int i = preparedStatement.executeUpdate();

            if (i != 0)
                return "SUCCESS";
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }

        return "Something went wrong";
    }

    public Event getRandomEvent() {
        List<Event> events = new ArrayList<>();
        Event res = null;
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            con = DBConnection.createConnection();
            String sql = "SELECT * FROM event where status='актуально'";
            statement = con.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int user_id = resultSet.getInt("user_id");
                String name = resultSet.getString("name");
                String city = resultSet.getString("city");
                String street = resultSet.getString("street");
                String house = resultSet.getString("house");
                String image = resultSet.getString("image");
                String description = resultSet.getString("description");
                int category_id = resultSet.getInt("category_id");
                String status = resultSet.getString("status");
                String date = resultSet.getString("date");

                CategoriesDao categoriesDao = new CategoriesDao();
                Categories category = categoriesDao.findById(category_id);

                UserDao userDao = new UserDao();
                User user = userDao.findById(user_id);

                Event event = new Event();
                event.setId(id);
                event.setUser(user);
                event.setCategory(category);
                event.setCity(city);
                event.setHouse(house);
                event.setStatus(status);
                event.setDate(date);
                event.setImage(image);
                event.setDescription(description);
                event.setStreet(street);
                event.setName(name);


                events.add(event);

            }
            int a = (int) (Math.random() * events.size());

            res = events.get(a);
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

        return res;
    }

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        Event res = null;
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            con = DBConnection.createConnection();
            String sql = "SELECT * FROM event where status='актуально'";
            statement = con.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int user_id = resultSet.getInt("user_id");
                String name = resultSet.getString("name");
                String city = resultSet.getString("city");
                String street = resultSet.getString("street");
                String house = resultSet.getString("house");
                String image = resultSet.getString("image");
                String description = resultSet.getString("description");
                int category_id = resultSet.getInt("category_id");
                String status = resultSet.getString("status");
                String date = resultSet.getString("date");
                CategoriesDao categoriesDao = new CategoriesDao();
                Categories category = categoriesDao.findById(category_id);


                UserDao userDao = new UserDao();
                User user = userDao.findById(user_id);

                Event event = new Event();
                event.setId(id);
                event.setUser(user);
                event.setCategory(category);
                event.setCity(city);
                event.setHouse(house);
                event.setStatus(status);
                event.setDate(date);
                event.setImage(image);
                event.setDescription(description);
                event.setStreet(street);
                event.setName(name);
                events.add(event);
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

        return events;
    }

    public List<Event> getAllUsersEvents(int id) {
        List<Event> events = new ArrayList<>();
        Event res = null;
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            con = DBConnection.createConnection();
            String sql = "SELECT * FROM event where user_id=" + id;
            statement = con.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int event_id = resultSet.getInt("id");
                int user_id = resultSet.getInt("user_id");
                String name = resultSet.getString("name");
                String city = resultSet.getString("city");
                String street = resultSet.getString("street");
                String house = resultSet.getString("house");
                String image = resultSet.getString("image");
                String description = resultSet.getString("description");
                int category_id = resultSet.getInt("category_id");
                String status = resultSet.getString("status");
                String date = resultSet.getString("date");
                CategoriesDao categoriesDao = new CategoriesDao();
                Categories category = categoriesDao.findById(category_id);


                UserDao userDao = new UserDao();
                User user = userDao.findById(user_id);

                Event event = new Event();
                event.setUser(user);
                event.setCategory(category);
                event.setCity(city);
                event.setHouse(house);
                event.setStatus(status);
                event.setDate(date);
                event.setImage(image);
                event.setDescription(description);
                event.setStreet(street);
                event.setName(name);
                events.add(event);
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

        return events;
    }

    public Event findById(int id) {
        List<Event> events = new ArrayList<>();
        Event res = null;
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            con = DBConnection.createConnection();
            String sql = "SELECT * FROM event where id=" + id;
            statement = con.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int user_id = resultSet.getInt("user_id");
                String name = resultSet.getString("name");
                String city = resultSet.getString("city");
                String street = resultSet.getString("street");
                String house = resultSet.getString("house");
                String image = resultSet.getString("image");
                String description = resultSet.getString("description");
                int category_id = resultSet.getInt("category_id");
                String status = resultSet.getString("status");
                String date = resultSet.getString("date");
                CategoriesDao categoriesDao = new CategoriesDao();
                Categories category = categoriesDao.findById(category_id);


                UserDao userDao = new UserDao();
                User user = userDao.findById(user_id);

                Event event = new Event();
                event.setUser(user);
                event.setCategory(category);
                event.setCity(city);
                event.setHouse(house);
                event.setStatus(status);
                event.setDate(date);
                event.setImage(image);
                event.setDescription(description);
                event.setStreet(street);
                event.setName(name);
                events.add(event);
            }
            int a = (int) (Math.random() * events.size());

            res = events.get(a);
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

        return res;
    }

    public void updateStatus(int id, String status) {

        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = DBConnection.createConnection();
            String query = "update event set status=? where id=" + id;
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, status);

            int i = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException ignore) {
        }
    }
}
