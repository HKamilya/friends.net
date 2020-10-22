package ru.mvc.dao;


import ru.mvc.model.Categories;
import ru.mvc.model.Event;
import ru.mvc.model.Image;
import ru.mvc.model.User;
import ru.mvc.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDao extends AbstractDao<Event> {
    public String insert(Event event) {

        Connection con = null;

        PreparedStatement preparedStatement = null;

        try {
            con = DBConnection.createConnection();

            String query = "insert into event(user_id,name,city,street,house,image,description,category_id,status,date, time ) values (?,?,?,?,?,?,?,?,?,?,?)"; //Insert user details into the table 'USERS'
            preparedStatement = con.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
            preparedStatement.setInt(1, event.getUser().getId());
            preparedStatement.setString(2, event.getName());
            preparedStatement.setString(3, event.getCity());
            preparedStatement.setString(4, event.getStreet());
            preparedStatement.setString(5, event.getHouse());
            preparedStatement.setInt(6, event.getImage().getId());
            preparedStatement.setString(7, event.getDescription());
            preparedStatement.setInt(8, event.getCategory().getId());
            preparedStatement.setString(9, event.getStatus());
            preparedStatement.setString(10, event.getDate());
            preparedStatement.setString(11, event.getTime());
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
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ignore) {
                }
            }
        }

        return "Что-то пошло не так";
    }

    public Event findRandomEvent() {
        List<Event> events = new ArrayList<>();
        Event res = null;
        Connection con = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            con = DBConnection.createConnection();
            preparedStatement = con.prepareStatement("SELECT * FROM event where status=?");
            preparedStatement.setString(1, "актуально");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Event event = new Event();
                event.setId(resultSet.getInt("id"));
                UserDao userDao = new UserDao();
                User user = userDao.findById(resultSet.getInt("user_id"));
                event.setUser(user);
                event.setName(resultSet.getString("name"));
                event.setCity(resultSet.getString("city"));
                event.setStreet(resultSet.getString("street"));
                event.setStreet(resultSet.getString("house"));
                ImageDao imageDao = new ImageDao();
                Image image = imageDao.findById(resultSet.getInt("image"));
                event.setImage(image);
                event.setDescription(resultSet.getString("description"));
                CategoriesDao categoriesDao = new CategoriesDao();
                Categories category = categoriesDao.findById(resultSet.getInt("category_id"));
                event.setCategory(category);
                event.setStatus(resultSet.getString("status"));
                event.setDate(resultSet.getString("date"));
                event.setTime(resultSet.getString("time"));


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

        return res;
    }

    public List<Event> findAll() {
        List<Event> events = new ArrayList<>();
        Connection con = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            con = DBConnection.createConnection();
            preparedStatement = con.prepareStatement("SELECT * FROM event where status=? order by date asc");
            preparedStatement.setString(1, "актуально");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Event event = new Event();
                event.setId(resultSet.getInt("id"));
                UserDao userDao = new UserDao();
                User user = userDao.findById(resultSet.getInt("user_id"));
                event.setUser(user);
                event.setName(resultSet.getString("name"));
                event.setCity(resultSet.getString("city"));
                event.setStreet(resultSet.getString("street"));
                event.setStreet(resultSet.getString("house"));
                ImageDao imageDao = new ImageDao();
                Image image = imageDao.findById(resultSet.getInt("image"));
                event.setImage(image);
                event.setDescription(resultSet.getString("description"));
                CategoriesDao categoriesDao = new CategoriesDao();
                Categories category = categoriesDao.findById(resultSet.getInt("category_id"));
                event.setCategory(category);
                event.setStatus(resultSet.getString("status"));
                event.setDate(resultSet.getString("date"));
                event.setTime(resultSet.getString("time"));


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

        return events;
    }

    public List<Event> findByUserId(int id) {
        List<Event> events = new ArrayList<>();
        Connection con = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            con = DBConnection.createConnection();
            preparedStatement = con.prepareStatement("SELECT * FROM event where user_id=?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Event event = new Event();
                event.setId(resultSet.getInt("id"));
                UserDao userDao = new UserDao();
                User user = userDao.findById(resultSet.getInt("user_id"));
                event.setUser(user);
                event.setName(resultSet.getString("name"));
                event.setCity(resultSet.getString("city"));
                event.setStreet(resultSet.getString("street"));
                event.setStreet(resultSet.getString("house"));
                ImageDao imageDao = new ImageDao();
                Image image = imageDao.findById(resultSet.getInt("image"));
                event.setImage(image);
                event.setDescription(resultSet.getString("description"));
                CategoriesDao categoriesDao = new CategoriesDao();
                Categories category = categoriesDao.findById(resultSet.getInt("category_id"));
                event.setCategory(category);
                event.setStatus(resultSet.getString("status"));
                event.setDate(resultSet.getString("date"));
                event.setTime(resultSet.getString("time"));


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

        return events;
    }

    public Event findById(int id) {
        Event event = new Event();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            con = DBConnection.createConnection();
            preparedStatement = con.prepareStatement("SELECT * FROM event where id=?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int user_id = resultSet.getInt("user_id");
                String name = resultSet.getString("name");
                String city = resultSet.getString("city");
                String street = resultSet.getString("street");
                String house = resultSet.getString("house");
                int imageId = resultSet.getInt("image");
                String description = resultSet.getString("description");
                int category_id = resultSet.getInt("category_id");
                String status = resultSet.getString("status");
                String date = resultSet.getString("date");
                String time = resultSet.getString("time");

                CategoriesDao categoriesDao = new CategoriesDao();
                Categories category = categoriesDao.findById(category_id);


                UserDao userDao = new UserDao();
                User user = userDao.findById(user_id);


                event.setId(id);
                event.setUser(user);
                event.setCategory(category);
                event.setCity(city);
                event.setHouse(house);
                event.setStatus(status);
                event.setDate(date);
                event.setTime(time);
                ImageDao imageDao = new ImageDao();
                Image image = imageDao.findById(imageId);
                event.setImage(image);
                event.setImage(image);
                event.setDescription(description);
                event.setStreet(street);
                event.setName(name);
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
        return event;
    }

    @Override
    public void update(Event adr) {

    }


    public List<Event> findByNameAndCategory(String eventName, List<Integer> categories) {
        List<Event> events = new ArrayList<>();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = null;
        System.out.println(categories.size());
        try {
            con = DBConnection.createConnection();
            if (categories.size() > 0 & eventName.length() > 0) {
                sql = "SELECT * FROM event where status='актуально' and name ILIKE  ? ";
                sql += " and status='актуально' and (";
                for (int i = 0; i < categories.size() - 2; i++) {
                    sql += "category_id=" + categories.get(i) + " or ";
                }
                sql += "category_id=" + categories.get(categories.size() - 1) + ");";
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, eventName + "%");
                resultSet = preparedStatement.executeQuery();
            } else if (categories.size() > 0) {
                sql = "SELECT * FROM event where status='актуально' and ";
                for (int i = 0; i < categories.size() - 2; i++) {
                    sql += " category_id=" + categories.get(i) + " or ";
                }
                sql += "category_id=" + categories.get(categories.size() - 1) + ";";
                preparedStatement = con.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
            }


            if (resultSet != null) {
                while (resultSet.next()) {
                    Event event = new Event();
                    event.setId(resultSet.getInt("id"));
                    UserDao userDao = new UserDao();
                    User user = userDao.findById(resultSet.getInt("user_id"));
                    event.setUser(user);
                    event.setName(resultSet.getString("name"));
                    event.setCity(resultSet.getString("city"));
                    event.setStreet(resultSet.getString("street"));
                    event.setStreet(resultSet.getString("house"));
                    ImageDao imageDao = new ImageDao();
                    Image image = imageDao.findById(resultSet.getInt("image"));
                    event.setImage(image);
                    event.setDescription(resultSet.getString("description"));
                    CategoriesDao categoriesDao = new CategoriesDao();
                    Categories category = categoriesDao.findById(resultSet.getInt("category_id"));
                    event.setCategory(category);
                    event.setStatus(resultSet.getString("status"));
                    event.setDate(resultSet.getString("date"));
                    event.setTime(resultSet.getString("time"));


                    events.add(event);
                }
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

        return events;
    }

    public List<Event> findByNameAndCategoryAndDate(String eventName, List<Integer> categories, String date) {
        List<Event> events = new ArrayList<>();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = null;
        System.out.println(categories.size());
        try {
            con = DBConnection.createConnection();
            if (categories.size() > 0 & eventName.length() > 0) {
                sql = "SELECT * FROM event where status='актуально' and name ILIKE  ? and date =? ";
                sql += " and status='актуально' and (";
                for (int i = 0; i < categories.size() - 2; i++) {
                    sql += "category_id=" + categories.get(i) + " or ";
                }
                sql += "category_id=" + categories.get(categories.size() - 1) + ");";
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, eventName + "%");
                preparedStatement.setString(2, date);
                resultSet = preparedStatement.executeQuery();
            } else if (categories.size() > 0) {
                sql = "SELECT * FROM event where status='актуально' and date ILIKE ? ";
                for (int i = 0; i < categories.size() - 2; i++) {
                    sql += " category_id=" + categories.get(i) + " or ";
                }
                sql += "category_id=" + categories.get(categories.size() - 1) + ";";
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, date);
                resultSet = preparedStatement.executeQuery();
            }


            if (resultSet != null) {
                while (resultSet.next()) {
                    Event event = new Event();
                    event.setId(resultSet.getInt("id"));
                    UserDao userDao = new UserDao();
                    User user = userDao.findById(resultSet.getInt("user_id"));
                    event.setUser(user);
                    event.setName(resultSet.getString("name"));
                    event.setCity(resultSet.getString("city"));
                    event.setStreet(resultSet.getString("street"));
                    event.setStreet(resultSet.getString("house"));
                    ImageDao imageDao = new ImageDao();
                    Image image = imageDao.findById(resultSet.getInt("image"));
                    event.setImage(image);
                    event.setDescription(resultSet.getString("description"));
                    CategoriesDao categoriesDao = new CategoriesDao();
                    Categories category = categoriesDao.findById(resultSet.getInt("category_id"));
                    event.setCategory(category);
                    event.setStatus(resultSet.getString("status"));
                    event.setDate(resultSet.getString("date"));
                    event.setTime(resultSet.getString("time"));


                    events.add(event);
                }
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

        return events;
    }

    public List<Event> findByName(String eventName) {
        List<Event> events = new ArrayList<>();

        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            con = DBConnection.createConnection();
            preparedStatement = con.prepareStatement("SELECT * FROM event where name ILIKE ? and status='актуально'");
            preparedStatement.setString(1, eventName + "%");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Event event = new Event();
                event.setId(resultSet.getInt("id"));
                UserDao userDao = new UserDao();
                User user = userDao.findById(resultSet.getInt("user_id"));
                event.setUser(user);
                event.setName(resultSet.getString("name"));
                event.setCity(resultSet.getString("city"));
                event.setStreet(resultSet.getString("street"));
                event.setStreet(resultSet.getString("house"));
                ImageDao imageDao = new ImageDao();
                Image image = imageDao.findById(resultSet.getInt("image"));
                event.setImage(image);
                event.setDescription(resultSet.getString("description"));
                CategoriesDao categoriesDao = new CategoriesDao();
                Categories category = categoriesDao.findById(resultSet.getInt("category_id"));
                event.setCategory(category);
                event.setStatus(resultSet.getString("status"));
                event.setDate(resultSet.getString("date"));
                event.setTime(resultSet.getString("time"));


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

        return events;
    }

    public List<Event> findByNameAndDate(String eventName, String eventDate) {
        List<Event> events = new ArrayList<>();

        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            con = DBConnection.createConnection();
            preparedStatement = con.prepareStatement("SELECT * FROM event where name ILIKE ? and date ILIKE ? and status='актуально'");
            preparedStatement.setString(1, eventName);
            preparedStatement.setString(2, eventDate);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Event event = new Event();
                event.setId(resultSet.getInt("id"));
                UserDao userDao = new UserDao();
                User user = userDao.findById(resultSet.getInt("user_id"));
                event.setUser(user);
                event.setName(resultSet.getString("name"));
                event.setCity(resultSet.getString("city"));
                event.setStreet(resultSet.getString("street"));
                event.setStreet(resultSet.getString("house"));
                ImageDao imageDao = new ImageDao();
                Image image = imageDao.findById(resultSet.getInt("image"));
                event.setImage(image);
                event.setDescription(resultSet.getString("description"));
                CategoriesDao categoriesDao = new CategoriesDao();
                Categories category = categoriesDao.findById(resultSet.getInt("category_id"));
                event.setCategory(category);
                event.setStatus(resultSet.getString("status"));
                event.setDate(resultSet.getString("date"));
                event.setTime(resultSet.getString("time"));


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

        return events;
    }

    public List<Event> findByDate(String eventDate) {
        List<Event> events = new ArrayList<>();

        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            con = DBConnection.createConnection();
            preparedStatement = con.prepareStatement("SELECT * FROM event where date ILIKE ? and status='актуально'");
            preparedStatement.setString(1, eventDate);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Event event = new Event();
                event.setId(resultSet.getInt("id"));
                UserDao userDao = new UserDao();
                User user = userDao.findById(resultSet.getInt("user_id"));
                event.setUser(user);
                event.setName(resultSet.getString("name"));
                event.setCity(resultSet.getString("city"));
                event.setStreet(resultSet.getString("street"));
                event.setStreet(resultSet.getString("house"));
                ImageDao imageDao = new ImageDao();
                Image image = imageDao.findById(resultSet.getInt("image"));
                event.setImage(image);
                event.setDescription(resultSet.getString("description"));
                CategoriesDao categoriesDao = new CategoriesDao();
                Categories category = categoriesDao.findById(resultSet.getInt("category_id"));
                event.setCategory(category);
                event.setStatus(resultSet.getString("status"));
                event.setDate(resultSet.getString("date"));
                event.setTime(resultSet.getString("time"));


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

        return events;
    }

    public void updateStatus(int id, String status) {
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            con = DBConnection.createConnection();
            preparedStatement = con.prepareStatement("update event set status=? where id=?");
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, id);

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
}
