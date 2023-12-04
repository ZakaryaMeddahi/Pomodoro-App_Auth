/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pomodoro_auth;

import java.util.Scanner;

public class Pomodoro_auth {

    public static void main(String[] args) {
        
        // Choose Opration
        System.out.println("1- Register");
        System.out.println("2- Login");
        System.out.println("3- Reset Password");
        System.out.println("4- Sync User Data");
        System.out.println("5- Exit");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        if(option == 1) {
            Register.registerUser();
        } else if(option == 2) {
            Login.loginUser();
        } else if(option == 3) {
            Password.resetPassword();
        } else if(option == 4) {
            DB.syncUserData();
        } else {
            scanner.close();
            System.exit(0);
        }
    }
}
