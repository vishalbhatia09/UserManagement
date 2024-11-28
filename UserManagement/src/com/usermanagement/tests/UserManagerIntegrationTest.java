package com.usermanagement.tests;

import com.usermanagement.UserManager;
import junit.framework.TestCase;

public class UserManagerIntegrationTest extends TestCase {
    public void testUserManagementFlow() {
        UserManager userManager = new UserManager();

        // Add users
        userManager.addUser(1, "Alice");
        userManager.addUser(2, "Bob");

        // Retrieve and validate users
        assertEquals("Alice", userManager.getUser(1));
        assertEquals("Bob", userManager.getUser(2));

        // Print users to console
        userManager.printUsers();
    }
}
