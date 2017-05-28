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

	private List<Concert> userConcertList = new ArrayList<>();

	public ConcertDaoDbImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
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
	public void addConcertToUserList(Concert concert) {
		try {
			Connection conn = DriverManager.getConnection(url, user, pw);
			String sql = "select c.id, p.name, v.name, c.date, p.photo_url"
					+ " from concert c join concert_has_performer cp on c.id = cp.concert_id"
				    + " join performer p on cp.performer_id = p.id"
				    + " join venue v on c.venue_id = v.id"
				    + " join concert_has_user cu on cu.concert_id = c.id"
				    + " join user i on cu.user_id = i.id"
					+ " where c.id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			pstmt.setInt(1,concert.getId());
			while (rs.next()) {
				concert = new Concert(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				userConcertList.add(concert);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<Concert> getUserConcertList(int userId) {
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
			ResultSet rs = pstmt.executeQuery();
			pstmt.setInt(1, userId);
			while (rs.next()) {
				Concert concert = new Concert(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
//				userConcertList.add(concert);
				System.out.println("in get user concert list: " + concert);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		// TODO Auto-generated method stub

	}

	@Override
	public void addConcertToList(Concert concert) {
		int newId = 0;
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
			
			conn.prepareStatement(setIdSql).executeQuery();
			
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
}
