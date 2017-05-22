package com.skilldistillery.data;

import java.util.List;

public interface ConcertDAO {
	public Concert getConcert(Concert concert);
	public Concert getConcertByPerformer(String performer);
	public void addConcertToList(Concert concert);
	List<Concert> getAllConcerts();
	public void persistConcertList(List<Concert> userConcertList);
}
