package com.skilldistillery.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConcertDaoDbImpl implements ConcertDAO {

	private String url = "jdbc:mysql://localhost:3306/concerteventdb";
	private String user = "concertgoer";
	private String pw = "Chowchow21";


	public ConcertDaoDbImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public User login(String username, String password) {
		User u = new User();
		try {
			Connection conn = DriverManager.getConnection(url, user, pw);
			String sql = "select password, first_name, last_name, id from user where username= ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				if(rs.getString(1).equals(password)){
					u.setPassword(rs.getString(1));
					u.setUsername(username);
					u.setFname(rs.getString(2));
					u.setLname(rs.getString(3));
					u.setId(rs.getInt(4));
					u.setConcertList(getUserConcertList(u));;
					return u;
				}
				else{
					return null;
				}
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public User signup(User u) {
		u = new User();
		try {
			Connection conn = DriverManager.getConnection(url, user, pw);
			String sql = "insert into user(first_name, last_name, username, password)"
					+ " values(?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u.getFname());
			pstmt.setString(2, u.getLname());
			pstmt.setString(1, u.getUsername());
			pstmt.setString(1, u.getPassword());
			pstmt.executeUpdate();
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public Concert getConcert(Concert concert) {
		return null;
	}

	@Override
	public Concert getConcertByPerformer(String performer) {
		Concert concert = null;
		List<Concert> concerts = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(url, user, pw);
			String sql = "select c.id, p.name, v.name, c.date, p.photo_url"
					+ " from concert c join concert_has_performer cp on c.id = cp.concert_id"
					+ " join performer p on cp.performer_id = p.id" + " join venue v on c.venue_id = v.id"
					+ " where p.name = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, performer);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				concert = new Concert(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				concerts.add(concert);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return concert;
	}
	
	@Override
	public Concert getConcertById(int id) {
		Concert concert = null;
		List<Concert> concerts = new ArrayList<>();
		
		try {
			Connection conn = DriverManager.getConnection(url, user, pw);
			String sql = "select c.id, p.name, v.name, c.date, p.photo_url"
					+ " from concert c join concert_has_performer cp on c.id = cp.concert_id"
					+ " join performer p on cp.performer_id = p.id" + " join venue v on c.venue_id = v.id"
					+ " where c.id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				concert = new Concert(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				concerts.add(concert);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return concert;
	}
	

	@Override
	public User addConcertToUserList(Concert concert, User u) {
		try {
			Connection conn = DriverManager.getConnection(url, user, pw);
			String sql = "insert into concert_has_user(concert_id,user_id) values (?,?)";
			System.out.println("concert id: " + concert.getId());
			System.out.println("user id: " + u.getId());
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1,concert.getId());
			stmt.setInt(2,u.getId());
			stmt.executeUpdate();
			u.addConcert(concert);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	
	@Override
	public User removeConcertFromUserList(Concert concert, User u) {
		try {
			Connection conn = DriverManager.getConnection(url, user, pw);
			String sql = "delete from concert_has_user where concert_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1,concert.getId());
			stmt.executeUpdate();
			u.removeConcert(concert);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	
	@Override
	public List<Concert> getUserConcertList(User u) {
		List<Concert> userConcertList = new ArrayList<>();
		System.out.println("in get user concerts");
		
		try {
			Connection conn = DriverManager.getConnection(url, user, pw);
			String sql = "select c.id, p.name, v.name, c.date, p.photo_url"
					+ " from user i join concert_has_user cu on i.id = cu.user_id"
					+ " join concert c on cu.concert_id = c.id"
					+ " join concert_has_performer cp on cp.concert_id = c.id"
					+ " join performer p on cp.performer_id = p.id" 
					+ " join venue v on c.venue_id = v.id"
					+ " where i.id = ?"
					+ " order by c.id asc";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, u.getId());
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Concert concert = new Concert(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				userConcertList.add(concert);
				System.out.println("in get user concert list: " + concert);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		u.setConcertList(userConcertList);
		return userConcertList;
	}

	@Override
	public List<Concert> getAllConcerts() {
		Concert concert = null;
		List<Concert> concerts = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(url, user, pw);
			String sql = "select c.id, p.name, v.name, c.date, p.photo_url "
					+ " from concert c join concert_has_performer cp on c.id = cp.concert_id "
					+ " join performer p on cp.performer_id = p.id join venue v on c.venue_id = v.id "
					+ " order by c.id asc";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				concert = new Concert(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				concerts.add(concert);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return concerts;
	}

	@Override
	public void persistConcertList(List<Concert> userConcertList) {

	}

	@Override
	public void addConcertToList(Concert concert) {
		try {
			Connection conn = DriverManager.getConnection(url, user, pw);
			
			String insertPerformerSql = "insert into performer(name) value (?)";
			String setIdSql = "set @performer_id = last_insert_id()";
			String insertConcertSql = "insert into concert(performer_id, venue_id, date) values(@performer_id, ?, ?)";
			String setIdSql2 = "set @concert_id = last_insert_id()";
			String setIdSql3 = "insert into concert_has_performer(performer_id, concert_id ) values (@performer_id, @concert_id)";
						 
			conn.setAutoCommit(false);
			
			PreparedStatement stmt = conn.prepareStatement(insertPerformerSql);
			stmt.setString(1,concert.getPerformer());
			stmt.executeUpdate();
			
			conn.prepareStatement(setIdSql).executeUpdate();
			
			PreparedStatement stmt2 = conn.prepareStatement(insertConcertSql);
			stmt2.setInt(1, 2);
			stmt2.setString(2, concert.getDate());
			stmt2.executeUpdate();
			
			conn.prepareStatement(setIdSql2).executeUpdate();
			conn.prepareStatement(setIdSql3).executeUpdate();
			
			conn.commit();
			System.out.println("success: concert added!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Override 
	public User updateConcert(Concert concert, String date, User u) {
		try {
			Connection conn = DriverManager.getConnection(url, user, pw);
			String sql = "update concert set date = ? where id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1,date);
			stmt.setInt(2,concert.getId());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		u.setConcertList(getUserConcertList(u));
		return u;
	}
}
