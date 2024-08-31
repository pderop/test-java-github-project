package org.acme.fancy.lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

/**
 * Javadoc for {@link JavaLibraryTemplate}
 */
public class JavaLibraryTemplate {

    /**
     * Converts input to fUnKyCaSe...
     *
     * @param str the input text
     * @return the funky output of course
     */
    public static String toFunkyCase(String str) {
        final char[] chars = str.toCharArray();

        char c;
        for (int i = 0; i < chars.length; i++) {
            c = chars[i];

            chars[i] = i % 2 == 0
                    ? Character.toLowerCase(c)
                    : Character.toUpperCase(c);
        }

        return new String(chars);
    }

    void testCodeQL(String userInput) throws Exception {
            // Establishing a connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "password");

            // Vulnerable code: SQL Injection
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Process the results
            while (rs.next()) {
                System.out.println("User: " + rs.getString("username"));
            }

            // Secure code: Using PreparedStatement to prevent SQL Injection
            String secureQuery = "SELECT * FROM users WHERE username = ?";
            PreparedStatement pstmt = connection.prepareStatement(secureQuery);
            pstmt.setString(1, userInput);
            ResultSet secureRs = pstmt.executeQuery();

            // Process the secure results
            while (secureRs.next()) {
                System.out.println("Secure User: " + secureRs.getString("username"));
            }
    }

}
