/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pomodoro_auth;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class DB {
    private static Connection connection = connectDB();
    private static int userId;
    
    public static Connection connectDB() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pomodoro_db", "root", "");
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    
    public static User getUser() throws SQLException {
        User user = null;
        String query = "SELECT * FROM user WHERE email = 'zaki@gmail.com' AND password = '123456'";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            
            if (resultSet.next()) {
                
                userId = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");

                
                System.out.println("id: " + userId + ", username: " + username + ", email: " + email);
                user = new User(userId, username, email);
            }
            preparedStatement.close();
            resultSet.close();
        }
        return user;
    }
    
    public static List<Task> getTasks() throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM task WHERE 'user-id' = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            System.out.println(userId);
            preparedStatement.setInt(1, 5);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                
                int id = resultSet.getInt("id");
                System.out.println(id);
                String content = resultSet.getString("content");
                System.out.println(content);
                String status = resultSet.getString("status");
                String tag = resultSet.getString("tag");
                String listName = null;
                int listId = resultSet.getInt("list-id");
                System.out.println(listId);
                
//                if(listId != 0) {
//                    String getListQuery = "SELECT * FROM list WHERE id = ?";
//                    PreparedStatement getListStatement = connection.prepareStatement(query);
////                    getListStatement.setInt(1, listId);
//                    System.out.println(listId);
//                    ResultSet resSet = getListStatement.executeQuery();
//                    
//                    if(resSet.next()) {
//                        listName = resSet.getString("title");
//                    }
//                    resSet.close();
//                }
                
                tasks.add(new Task(id, content, status, tag, listName));
            }
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException ex) {
            Logger.getLogger(Pomodoro_auth.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tasks;
    }
    
    public static Settings getSettings() throws SQLException {
        Settings settings = null;
        String query = "SELECT * FROM settings";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            
            if (resultSet.next()) {
                
                int sessionDuration = resultSet.getInt("session-duration");
                int shortBreak = resultSet.getInt("short-break");
                int longBreak = resultSet.getInt("long-break");
                int shortBreakAfter = resultSet.getInt("short-break-after");
                boolean autoStartBreak = resultSet.getBoolean("auto-start-break");
                boolean autoStartPomodoro = resultSet.getBoolean("auto-start-pomodoro");
                boolean autoStart = resultSet.getBoolean("auto-start");
                boolean disableBreaks = resultSet.getBoolean("disable-breaks");
                
                settings = new Settings(sessionDuration, shortBreak, longBreak, shortBreakAfter, autoStartBreak, autoStartPomodoro, autoStart, disableBreaks);
            }
            preparedStatement.close();
            resultSet.close();
            connection.close();
        }
        return settings;
    }
    
    public static void syncUserData() throws SQLException {
        User user = getUser();
        List<Task> tasks = getTasks();
        Settings settings = getSettings();
        
        Data userData = new Data(user, tasks, settings);
        
        Gson gson = new Gson();
        String data = gson.toJson(userData);
        
        try (FileWriter writer = new FileWriter("data.json")) {
            System.out.println(data);
            writer.write(data);
        } catch (IOException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
