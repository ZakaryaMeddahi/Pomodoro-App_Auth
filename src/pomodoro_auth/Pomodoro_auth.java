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
        System.out.println("3- Exit");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        if(option == 1) {
            Register.registerUser();
        } else if(option == 2) {
            Login.loginUser();
        } else {
            scanner.close();
            System.exit(0);
        }
    }
    
}
