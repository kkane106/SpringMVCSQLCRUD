package com.skilldistillery.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthenticationDaoDbImpl implements AuthenticationDAO {

	private String url = "jdbc:mysql://localhost:3306/concerteventdb";
	private String user = "concertgoer";
	private String pw = "Chowchow21";
	
	private List<User> users = new ArrayList<>();
	
	@Override
	public User createUser(User u) {
		try {
		Connection conn = DriverManager.getConnection(url, user, pw);
		String createUserSql = "insert into user(username, password, first_name, last_name)"
				+ " values (?, ?, ?, ?)";
		String setIdSql = "set @user_id = last_insert_id()";
		
		conn.setAutoCommit(false);
		
		PreparedStatement stmt = conn.prepareStatement(createUserSql);
		stmt.setString(1, u.getUsername());
		stmt.setString(2, u.getPassword());
		stmt.setString(3, u.getFname());
		stmt.setString(4, u.getLname());
		stmt.executeUpdate();
		
		conn.prepareStatement(setIdSql).executeUpdate();
		
		conn.commit();
		System.out.println("success: user created!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean usernameIsUnique(String username) {
		try {
			Connection conn = DriverManager.getConnection(url, username, pw);
			String sql =  "select count(*) from user where username = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.getInt(1) > 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public User validUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validPassword(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
