package com.radiant.sage.code.test.transaction.mangement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcTransactionManagement {

	private static final String DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/UserDB";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "root@123";

	private static final String INSERT_STUDENT_QUERY = "insert into Student (student_id, name, age) values (?,?,?)";
	private static final String INSERT_ADDRESS_QUERY = "insert into Address (student_id, address, city, country) values (?,?,?,?)";

	public static void main(String[] args) {
		Connection con = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			insertEmployeeData(con, 1, "Sanjay Kumar",24);
			insertAddressData(con, 1, "Fort Road", "Machilipatnam", "India");
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				
				if (con != null)
				  con.rollback();
				
				System.out.println("JDBC Transaction rolled back successfully");
			} catch (SQLException e1) {
				System.out.println("SQLException in rollback" + e.getMessage());
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection con = null;
		try {
			Class.forName(DB_DRIVER_CLASS);
			con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			System.out.println("DB Connection created successfully");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Exception occured inside the JdbcTransactionManagement getConnection: " + e.getMessage());
			throw e;
		}
		return con;
	}

	public static void insertAddressData(Connection con, int id, String address, String city, String country) throws SQLException {
		PreparedStatement stmt = con.prepareStatement(INSERT_ADDRESS_QUERY);
		stmt.setInt(1, id);
		stmt.setString(2, address);
		stmt.setString(3, city);
		stmt.setString(4, country);
		stmt.executeUpdate();
		System.out.println("Address Data inserted successfully for ID=" + id);
		stmt.close();
	}

	public static void insertEmployeeData(Connection con, int id, String name,int age) throws SQLException {
		PreparedStatement stmt = con.prepareStatement(INSERT_STUDENT_QUERY);
		stmt.setInt(1, id);
		stmt.setString(2, name);
		stmt.setInt(3, age);
		stmt.executeUpdate();
		System.out.println("Student Data inserted successfully for ID=" + id);
		stmt.close();
	}
}
