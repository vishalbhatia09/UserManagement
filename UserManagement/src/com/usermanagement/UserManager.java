package com.usermanagement;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<Integer, String> users = new HashMap<>();

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

    // Print all users
    public void printUsers() {
        System.out.print("User List:");
        users.forEach((id, name) -> System.out.print("ID: " + id + ", Name: " + name));
    }

    // Get the number of users
    public int getUserCount() {
        return users.size();
    }
}
