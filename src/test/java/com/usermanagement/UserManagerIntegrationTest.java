package com.usermanagement;

import com.usermanagement.UserManager;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserManagerIntegrationTest {

    private UserManager userManager;
    private ByteArrayOutputStream outputStream; // Declare the outputStream here

    @BeforeEach
    void setUp() {
        userManager = new UserManager();
        outputStream = new ByteArrayOutputStream(); // Initialize the outputStream
        System.setOut(new PrintStream(outputStream)); // Redirect System.out
    }

    @AfterEach
    void tearDown() {
        System.setOut(System.out); // Restore the original System.out
    }

    @Test
    public void testUserManagementFlow() {
        UserManager userManager = new UserManager();

        // Add users
        userManager.addUser(1, "Alice");
        userManager.addUser(2, "Bob");

        userManager.addUserEmail(1, "alice@example.com");

        // Retrieve and validate users
        assertEquals("Alice", userManager.getUser(1));
        assertEquals("Bob", userManager.getUser(2));

        String expectedOutput = "User List:ID: 1, Name: Alice, Email: alice@example.com; ID: 2, Name: Bob, Email: Email not found.";

        // Print users to console
        userManager.printUsers();

    }
}
