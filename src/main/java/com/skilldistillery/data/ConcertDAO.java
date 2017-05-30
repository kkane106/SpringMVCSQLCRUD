package com.skilldistillery.data;

import java.util.List;

public interface ConcertDAO {
	//user functions
	User login(String username, String password);
	User signup(User user);
	
	//all concerts
	public Concert getConcert(Concert concert);
	public Concert getConcertByPerformer(String performer);
	List<Concert> getAllConcerts();
	void addConcertToList(Concert concert);
	
	//user concerts
	public User addConcertToUserList(Concert concert, User u);
	public void persistConcertList(List<Concert> userConcertList);
	public List<Concert> getUserConcertList(User u);
	Concert getConcertById(int id);
	public User removeConcertFromUserList(Concert concert, User u);
	public User updateConcert(Concert concert, String date, User u);
}
