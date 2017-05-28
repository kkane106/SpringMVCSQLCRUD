package com.skilldistillery.data;

import java.util.List;

public interface ConcertDAO {
	public Concert getConcert(Concert concert);
	public Concert getConcertByPerformer(String performer);
	public void addConcertToUserList(Concert concert);
	List<Concert> getAllConcerts();
	public void persistConcertList(List<Concert> userConcertList);
	void addConcertToList(Concert concert);
	public List<Concert> getUserConcertList(int id);
}
