package swingexample;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class LoginApp extends JFrame implements ActionListener {
    // GUI components
    JLabel label1, label2;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton;

    // Database URL, username, password
    final String DB_URL = "jdbc:mysql://localhost:3306/your_database"; // Replace with your DB URL
    final String DB_USER = "root"; // Replace with your DB username
    final String DB_PASSWORD = "root"; // Replace with your DB password

    // Constructor for GUI setup
    public LoginApp() {
        // Set up the frame
        setTitle("Login Application");
        setSize(400, 180);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // Labels
        label1 = new JLabel("Username:");
        label1.setBounds(50, 30, 100, 30);
        label2 = new JLabel("Password:");
        label2.setBounds(50, 70, 100, 30);

        // Text fields
        usernameField = new JTextField();
        usernameField.setBounds(150, 30, 150, 30);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 70, 150, 30);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBounds(150, 110, 80, 30);
        loginButton.addActionListener(this);

        // Add components to the frame
        add(label1);
        add(label2);
        add(usernameField);
        add(passwordField);
        add(loginButton);

        setVisible(true);
    }

    // Action performed when login button is clicked
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());

        if (authenticateUser(username, password)) {
            JOptionPane.showMessageDialog(this, "Login successful!");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to authenticate user with the database
    private boolean authenticateUser(String username, String password) {
        boolean isAuthenticated = false;

        try {
            // Connect to the database
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            // If user exists in the database, authenticate
            if (rs.next()) {
                isAuthenticated = true;
            }

            // Close resources
            rs.close();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return isAuthenticated;
    }

    // Main method to run the application
    public static void main(String[] args) {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
             // Start the login application
            new LoginApp();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
