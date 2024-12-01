package com.usermanagement.tests;

import com.usermanagement.UserManager;
import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UserManagerTest extends TestCase {
    private UserManager userManager;
    private ByteArrayOutputStream outputStream; // Declare the outputStream here

    @Override
    protected void setUp() throws Exception {
        userManager = new UserManager();
        outputStream = new ByteArrayOutputStream(); // Initialize the outputStream
        System.setOut(new PrintStream(outputStream)); // Redirect System.out
    }

    @Override
    protected void tearDown() throws Exception {
        System.setOut(System.out); // Restore the original System.out
    }

    public void testAddUser() {
        userManager.addUser(1, "Alice");
        assertEquals("Alice", userManager.getUser(1));
    }

    public void testAddDuplicateUser() {
        userManager.addUser(1, "Alice");
        try {
            userManager.addUser(1, "Bob");
            fail("Exception not thrown for duplicate user ID.");
        } catch (IllegalArgumentException e) {
            assertEquals("User ID already exists.", e.getMessage());
        }
    }

    public void testGetUser() {
        userManager.addUser(1, "Alice");
        assertEquals("Alice", userManager.getUser(1));
        assertEquals("User not found.", userManager.getUser(2));
    }

    public void testUserCount() {
        userManager.addUser(1, "Alice");
        userManager.addUser(2, "Bob");
        assertEquals(2, userManager.getUserCount());
    }

    public void testPrintUsers() {
        userManager.addUser(1, "Alice");
        userManager.addUser(2, "Bob");

        userManager.printUsers(); // Call the method to print users

        String expectedOutput = "User List:\nID: 1, Name: Alice, Email: No email added\nID: 2, Name: Bob, Email: No email added";

        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }

    // Test for adding an email to a user
    public void testAddUserEmail() {
        userManager.addUser(1, "Alice");
        userManager.addUserEmail(1, "alice@example.com");

        // Verify the email is added correctly
        assertEquals("alice@example.com", userManager.getUserEmail(1));
    }

    public void testAddEmailForNonExistentUser() {
        try {
            userManager.addUserEmail(99, "unknown@example.com");
            fail("Exception not thrown for non-existent user.");
        } catch (IllegalArgumentException e) {
            assertEquals("User ID does not exist.", e.getMessage());
        }
    }

    public void testPrintUsersWithEmails() {
        userManager.addUser(1, "Alice");
        userManager.addUser(2, "Bob");

        userManager.addUserEmail(1, "alice@example.com");

        userManager.printUsers();

        String expectedOutput = "User List:\nID: 1, Name: Alice, Email: alice@example.com\nID: 2, Name: Bob, Email: No email added";

        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }
}
