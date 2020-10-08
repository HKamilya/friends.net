package ru.mvc.models;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import ru.mvc.bean.Users;
import ru.mvc.util.DBConnection;
 
public class RegisterDao { 
     public String registerUser(Users users)
     {
         String fullName = users.getFullName();
         String email = users.getEmail();
         String userName = users.getUserName();
         String password = users.getPassword();
          
         Connection con = null;
         PreparedStatement preparedStatement = null;         
         try
         {
             con = DBConnection.createConnection();
             String query = "insert into \"user\"(fullname,email,username,password) values (?,?,?,?)"; //Insert user details into the table 'USERS'
             preparedStatement = con.prepareStatement(query);
             preparedStatement.setString(1, fullName);
             preparedStatement.setString(2, email);
             preparedStatement.setString(3, userName);
             preparedStatement.setString(4, password);
              
             int i= preparedStatement.executeUpdate();
              
             if (i!=0)  //Just to ensure data has been inserted into the database
             return "SUCCESS"; 
         }
         catch(SQLException e)
         {
            e.printStackTrace();
         }       
         return "Oops.. Something went wrong there..!";

     }
}