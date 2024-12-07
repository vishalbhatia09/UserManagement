package com.usermanagement;

import com.usermanagement.UserManager;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserManagerTest {

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
    public void testAddUser() {
        userManager.addUser(1, "Alice");
        assertEquals("Alice", userManager.getUser(1));
    }

    @Test
    public void testAddDuplicateUser() {
        userManager.addUser(1, "Alice");
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            userManager.addUser(1, "Bob");
        });
        assertEquals("User ID already exists.", exception.getMessage());
    }

    @Test
    public void testGetUser() {
        userManager.addUser(1, "Alice");
        assertEquals("Alice", userManager.getUser(1));
        assertEquals("User not found.", userManager.getUser(2));
    }

    @Test
    public void testUserCount() {
        userManager.addUser(1, "Alice");
        userManager.addUser(2, "Bob");
        assertEquals(2, userManager.getUserCount());
    }

    @Test
    public void testPrintUsers() {
        userManager.addUser(1, "Alice");
        userManager.addUser(2, "Bob");

        userManager.printUsers(); // Call the method to print users

        String expectedOutput = "User List:\nID: 1, Name: Alice, Email: Email not found.\nID: 2, Name: Bob, Email: Email not found.\n";

        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }

    // Test for adding an email to a user
    @Test
    public void testAddUserEmail() {
        userManager.addUser(1, "Alice");
        userManager.addUserEmail(1, "alice@example.com");

        // Verify the email is added correctly
        assertEquals("alice@example.com", userManager.getUserEmail(1));
    }

    @Test
    public void testAddEmailForNonExistentUser() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            userManager.addUserEmail(99, "unknown@example.com");
        });
        assertEquals("User ID does not exist.", exception.getMessage());
    }

    @Test
    public void testPrintUsersWithEmails() {
        userManager.addUser(1, "Alice");
        userManager.addUser(2, "Bob");

        userManager.addUserEmail(1, "alice@example.com");

        userManager.printUsers();

        String expectedOutput = "User List:\nID: 1, Name: Alice, Email: alice@example.com\nID: 2, Name: Bob, Email: Email not found.\n";

        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }
}
