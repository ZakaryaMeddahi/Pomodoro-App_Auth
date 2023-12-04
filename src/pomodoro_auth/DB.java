/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pomodoro_auth;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class DB {
    public static Connection connectDB() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pomodoro_db", "root", "");
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    
    public static void syncUserData() {
        User user = new User("Zakarya", "zaki@gmail.com");
        List<Task> tasks = new ArrayList(Arrays.asList(
            new Task(1, "Task 1", "To Do", "Urgent", null),
            new Task(2, "Task 2", "To Do", "Urgent", "Project")
        ));
        Settings settings = new Settings(30, 5, 15, 2, true, true, true, false);
        
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
