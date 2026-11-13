package integration;

import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class DatabaseIntegrationTest {

    @Test
    public void testDatabaseInsertAndRead() {
        String url = System.getenv("DB_URL") != null ? System.getenv("DB_URL") : "jdbc:postgresql://localhost:5432/testdb";
        String user = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "testuser";
        String password = System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : "testpassword";

        String randomUniqueName = "TestUser_" + UUID.randomUUID().toString().substring(0, 8);

        // 1. Create table if any
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY, username VARCHAR(100))";
        String insertSQL = "INSERT INTO users (username) VALUES (?)";
        String selectSQL = "SELECT username FROM users WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            // 1. Create table
            Allure.step("Create User table", () -> {
                try (PreparedStatement pstmtCreate = conn.prepareStatement(createTableSQL)) {
                    pstmtCreate.execute();
                }
            });

            // 2. Insert a record
            Allure.step("Insert a record with random unique name " + randomUniqueName, () -> {
                try (PreparedStatement pstmtInsert = conn.prepareStatement(insertSQL)) {
                    pstmtInsert.setString(1, randomUniqueName);
                    int affectedRows = pstmtInsert.executeUpdate();
                    Assert.assertTrue(affectedRows > 0, "Insert execution failed!");
                }
            });

            // 3. Read a record and verify it
            Allure.step("Read a record and verify whether the username in that record matches the input data", () -> {
                try (PreparedStatement pstmtSelect = conn.prepareStatement(selectSQL)) {
                    pstmtSelect.setString(1, randomUniqueName);
                    try (ResultSet rs = pstmtSelect.executeQuery()) {
                        Assert.assertTrue(rs.next(), "No record found with username: " + randomUniqueName);
                        String fetchedName = rs.getString("username");
                        Assert.assertEquals(fetchedName, randomUniqueName, "Read data does not match inserted data!");
                    }
                }
            });

            System.out.println("--> Database Integration Test passed successfully for record: " + randomUniqueName);

        } catch (Exception e) {
            Assert.fail("Database operation threw an exception: " + e.getMessage(), e);
        }
    }
}