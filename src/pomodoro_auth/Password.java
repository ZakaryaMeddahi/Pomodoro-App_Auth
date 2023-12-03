/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pomodoro_auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Password {
    public static void resetPassword() {
        Scanner scanner = new Scanner(System.in);
        String email;
        String newPassword;
        
        System.out.print("Input your email: ");
        email = scanner.nextLine();
        
        do {
            System.out.print("Input your new password: ");
            newPassword = scanner.nextLine();
            if(newPassword.length() < 8 && newPassword.length() > 20) {
                System.err.println("Password length must be between 8 and 20");
            }
        } while(newPassword.length() < 8 && newPassword.length() > 20);
        
        Connection connection = DB.connectDB();
        String query = "SELECT * FROM user WHERE email = ?";
        PreparedStatement checkUserStatement;
        try {
            checkUserStatement = connection.prepareStatement(query);
            checkUserStatement.setString(1, email);
            
            ResultSet resultSet = checkUserStatement.executeQuery();
            
            if(!resultSet.next()) {
                // Email Does Not Exist
                System.err.println("Email Does Not Exist!");
            } else {
                // Update Password
                String updatePasswordQuery = "UPDATE user SET password = ? WHERE email = ?";
                PreparedStatement updatePasswordStatement = connection.prepareStatement(updatePasswordQuery);
                
                updatePasswordStatement.setString(1, newPassword);
                updatePasswordStatement.setString(2, email);
                
                updatePasswordStatement.executeUpdate();
                
                System.out.println("Password Has Been Updated Successfully");
                
                updatePasswordStatement.close();
            }
            
            checkUserStatement.close();
            resultSet.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Password.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
