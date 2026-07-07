package day7.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Database {
	
	private static final String SEARCHBYEMAIL = "SELECT studentid, email, firstname, lastname, password FROM student WHERE email = ? LIMIT 1";
	private static final String ADDSTUDENT = "INSERT INTO student(email, password, firstname, lastname) VALUES (?, ?, ?, ?)";
    private static final String SEARCHSTUDENT = "SELECT studentid, email, firstname, lastname FROM student"
    		+ " WHERE firstname LIKE ?"
    		+ " OR lastname LIKE ?"
    		+ " OR email LIKE ?"
    		+ " OR studentid::TEXT LIKE ?";
    private static final String UPDATEPASSWORD = "UPDATE student SET password = ?, dateupdated = NOW() WHERE email = ?";
    private static final String DELETESTUDENT = "DELETE FROM student WHERE email = ?";
	
	private static ResultSet checkExistingEmail(Scanner scanner, Connection con, String email) throws SQLException {       
		PreparedStatement ps = con.prepareStatement(SEARCHBYEMAIL);
		ps.setString(1, email);

		return ps.executeQuery();
	}
	
	private static void addStudent(Scanner scanner, Connection con) throws SQLException {    
    	System.out.print("Enter email: ");
        String email = scanner.nextLine();
        ResultSet rs = checkExistingEmail(scanner, con, email);
        
        if (rs.next()) {
        	System.out.println("Email is taken.");
        	return;
        }
        
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        System.out.print("Enter first name: ");
        String fname = scanner.nextLine();
        
        System.out.print("Enter last name: ");
        String lname = scanner.nextLine();
        
		PreparedStatement ps = con.prepareStatement(ADDSTUDENT);

		ps.setString(1, email);
		ps.setString(2, password);
		ps.setString(3, fname);
		ps.setString(4, lname);
		
		ps.executeUpdate();
		
		System.out.println("Student added");
	}
	
	private static void viewStudent(Scanner scanner, Connection con) throws SQLException {
        boolean isNotEmpty = false;
        
        System.out.print("Search a student: ");
        String searchQuery = scanner.nextLine();
        String searchTerm = "%" + searchQuery + "%";
        
		PreparedStatement ps = con.prepareStatement(SEARCHSTUDENT);
		ps.setString(1, searchTerm);
		ps.setString(2, searchTerm);
		ps.setString(3, searchTerm);
		ps.setString(4, searchTerm);
		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			System.out.println("[ID " + rs.getString("studentid") + "] " + 
					rs.getString("firstname") + " " + rs.getString("lastname")
					+ " (" + rs.getString("email") + ")");
			isNotEmpty = true;
		}
		
		if (!isNotEmpty) {
			System.out.println("No students found.");
		}
	}
	
	private static void updatePassword(Scanner scanner, Connection con) throws SQLException { 
    	System.out.print("Enter email: ");
        String email = scanner.nextLine();
		ResultSet rs = checkExistingEmail(scanner, con, email);
		
		if (!rs.next()) {
			System.out.println("No students found.");
			return;
		}
		
		System.out.print("Enter current password: ");
		String oldPassword = scanner.nextLine();
		
		if (!oldPassword.equals(rs.getString("password"))) {
	        System.out.println("Incorrect password.");
	        return;
	    }
		
		System.out.print("Enter a new password: ");
		String newPassword = scanner.nextLine();
		
		System.out.print("Confirm password: ");
		String confirmPassword = scanner.nextLine();
		
		if (!newPassword.equals(confirmPassword)) {
			System.out.println("Passwords did not match.");
			return;
		}
		
		PreparedStatement ps2 = con.prepareStatement(UPDATEPASSWORD);
		ps2.setString(1, newPassword);
		ps2.setString(2, rs.getString("email"));
		
		ps2.executeUpdate();
		System.out.println("Password for " + rs.getString("email") + " was successfully changed.");
	}
	
	private static void deleteStudent(Scanner scanner, Connection con) throws SQLException {
    	System.out.print("Enter email: ");
        String email = scanner.nextLine();
		ResultSet rs = checkExistingEmail(scanner, con, email);
		
		if (!rs.next()) {
			System.out.println("No students found.");
			return;
		}
		
		System.out.print("Enter password to confirm deletion: ");
		String password = scanner.nextLine();
		
		if (!password.equals(rs.getString("password"))) {
	        System.out.println("Incorrect password.");
	        return;
	    }
		
		System.out.print("Confirm password: ");
	    String confirmPassword = scanner.nextLine();
	    if (!password.equals(confirmPassword)) {
	        System.out.println("Passwords did not match.");
	        return;
	    }
	    
		PreparedStatement ps2 = con.prepareStatement(DELETESTUDENT);
		ps2.setString(1, rs.getString("email"));
		ps2.executeUpdate();
		System.out.println("User " + rs.getString("email") + " was successfully deleted.");
	}
	
	public static void main(String[] args) throws SQLException {
		
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String uname = "postgres";
		String pass = "eren";
		
		boolean isRunning = true;
		
		Connection con = DriverManager.getConnection(url, uname, pass);

        Scanner scanner = new Scanner(System.in);
		
        while (isRunning) {
            System.out.println("\n=== MENU ===\n [A]dd\n [V]iew\n [U]pdate Password\n [D]elete\n [Q]uit");
            System.out.print("Enter choice: ");
            
            String selection = scanner.nextLine();
            
            switch (selection.toUpperCase()) {
    	        case "A" -> addStudent(scanner, con);
    	        case "V" -> viewStudent(scanner, con);
    	        case "U" -> updatePassword(scanner, con);
    	        case "D" -> deleteStudent(scanner, con);
    	        case "Q" -> isRunning = false;
    	        default -> System.out.println("Invalid choice");
            }
        }
        
        con.close();
        scanner.close();
	}

}
