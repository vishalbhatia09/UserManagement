package com.usermanagement;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<Integer, String> users = new HashMap<>();
    private Map<Integer, String> emails = new HashMap<>();

    // Add a user
    public void addUser(int id, String name) {
        if (users.containsKey(id)) {
            throw new IllegalArgumentException("User ID already exists.");
        }
        users.put(id, name);
    }

    // Retrieve a user
    public String getUser(int id) {
        return users.getOrDefault(id, "User not found.");
    }

    // Add email for a user
    public void addUserEmail(int id, String email) {
        if (!users.containsKey(id)) {
            throw new IllegalArgumentException("User ID does not exist.");
        }
        emails.put(id, email);
    }

    // Get email of a user
    public String getUserEmail(int id) {
        return emails.getOrDefault(id, "Email not found.");
    }

    // Print all users
    public void printUsers() {
        System.out.print("User List:");
        users.forEach(
                (id, name) -> System.out
                        .print("ID: " + id + ", Name: " + name + ", Email: " + getUserEmail(id) + ";"));
    }

    // Get the number of users
    public int getUserCount() {
        return users.size();
    }
}
