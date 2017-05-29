package com.skilldistillery.data;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;


public class ConcertDaoListImpl implements ConcertDAO {
	
	private static final String FILE_NAME="/WEB-INF/concerts.csv";
	private List<Concert> concerts = new ArrayList<>();
	private Concert c;
	
	@Autowired 
	private WebApplicationContext wac;

	@Autowired
	private ServletContext context;
	
	@PostConstruct
	public void init() {
		InputStream is = wac.getServletContext().getResourceAsStream(FILE_NAME);
		BufferedReader bf = new BufferedReader(new InputStreamReader(is));
		try {
			String line = bf.readLine();
			while ((line = bf.readLine()) != null) {
				String[] tokens = line.split(",");
				String performer = tokens[1];
				String venue = tokens[2];
				String date = tokens[3];
				String url = tokens[4];
				concerts.add(new Concert(performer, venue, date, url));
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	
	@Override
	public Concert getConcertByPerformer(String performer) {
		c = null;
		for (Concert concert : concerts) {
			if (performer.equalsIgnoreCase(concert.getPerformer())) {
				c = concert;
			}
		}
		return c;
	}

	@Override
	public void addConcertToList(Concert concert) {
		concerts.add(c);
//		return 0;
	}
	
	@Override
	public List<Concert> getAllConcerts() {
		return concerts;
	}
	
	 @Override
	    public void persistConcertList(List<Concert> userConcertList) {
	        String concertFile = "WEB-INF/userconcertlist.csv";
	        String filePath = context.getRealPath(concertFile);
	        System.out.println("DAO: " + filePath);
	        try {
	            PrintWriter out = new PrintWriter(new FileWriter(filePath));
	            for (Concert c : userConcertList) {
	                out.println(c.getPerformer() + ", " + c.getVenue() + ", " + c.getDate()
	                );
	                
	            }
	            out.close();
	        } catch (IOException ioe) {
	            ioe.printStackTrace();
	        }
	    }


	@Override
	public Concert getConcert(Concert concert) {
		for (Concert c : concerts) {
			if (c.equals(concert)) {
				concert = c;
				break;
			}
		}
		return concert;
	}


	@Override
	public User addConcertToUserList(Concert concert, User u) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public User getUserConcertList(User u) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Concert getConcertById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}







}