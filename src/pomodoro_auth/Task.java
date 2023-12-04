/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pomodoro_auth;


public class Task {
    private int id;
    private String content;
    private String status;
    private String tag;
    private String listName;

    public Task(int id, String content, String status, String tag, String listName) {
        this.id = id;
        this.content = content;
        this.status = status;
        this.tag = tag;
        this.listName = listName;
    }
}
