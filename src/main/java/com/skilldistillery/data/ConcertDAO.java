package com.skilldistillery.data;

import java.util.List;

public interface ConcertDAO {
	public Concert getConcert(Concert concert);
	public Concert getConcertByPerformer(String performer);
	public User addConcertToUserList(Concert concert, User u);
	List<Concert> getAllConcerts();
	public void persistConcertList(List<Concert> userConcertList);
	void addConcertToList(Concert concert);
	public User getUserConcertList(User u);
	Concert getConcertById(int id);
	User login(String username, String password);
	User removeConcertFromUserList(Concert concert, User u);
}
