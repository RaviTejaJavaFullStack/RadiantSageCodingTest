package com.radiant.sage.code.test.jdbc.streams;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class JdbcResultListStreams {

	private static final String DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/UserDB";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "root@123";

	private static final String SELECT_STUDENT_QUERY = "select student_id, name, age from student";

	public static void main(String[] args) {

		 // List<Student> students = getStudentDetails();
		List<Student> students = getStaticStudentDetails();
		
		System.out.println("Stream Filter: " +usingStreamAgeFilter(students));
		System.out.println("Normal Filter: " +usingNormalAgeFilter(students));
		
	}

	private static List<Student> getStaticStudentDetails() {
		List<Student> students = new ArrayList<>();
		students.add(new Student(1, "Sai", 10));
		students.add(new Student(2, "Ramu", 8));
		students.add(new Student(3, "Raghu", 14));
		return students;
	}

	private static List<Student> usingStreamAgeFilter(List<Student> students) {
		if(!students.isEmpty()) {
			 return students.stream().filter(e->e.getAge() <= 12).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}
	
	private static List<Student> usingNormalAgeFilter(List<Student> students) {
		if(!students.isEmpty()) {
			List<Student> list = new ArrayList<>();
			for(Student student:students) {
				if(student.getAge() <= 12) {
					list.add(student);
				}
			}
			return list;
		}
		return Collections.emptyList();
	}

	private static List<Student> getStudentDetails() {
		List<Student> students = new ArrayList<>();
		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(SELECT_STUDENT_QUERY);) {
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					students.add(new Student(rs.getInt("student_id"), rs.getString("name"),rs.getInt("age")));
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return students;
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

}
