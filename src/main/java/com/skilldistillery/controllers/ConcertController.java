package com.skilldistillery.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.data.Concert;
import com.skilldistillery.data.ConcertDAO;

@Controller
@SessionAttributes({"concertList", "concert"})
public class ConcertController {

	@Autowired
	private ConcertDAO dao;

	@ModelAttribute("concertList")
	public List<Concert> initSessionObject(){
		List<Concert> userConcertList = new ArrayList<>();
		return userConcertList;
	}

	@ModelAttribute("userConcertList")
	@RequestMapping(path = "GetConcertData.do", params="AddYourEvent")
	public ModelAndView addEvent(@RequestParam("performer") String performer, 
			@RequestParam("venue") String venue,
			@RequestParam("date") String date, 
			@ModelAttribute("concertList") List<Concert> userConcertList,
			HttpSession session) {
		System.out.println("in addEvent");
		ModelAndView mv = new ModelAndView();
		Concert c = new Concert(performer, venue, date);
		userConcertList.add(c);
		mv.addObject("concert",c);
		mv.addObject("userConcertList", userConcertList);
		mv.setViewName("yourConcertsPage.jsp");
		return mv;
	}
	
	@RequestMapping(path="GetConcertData.do", params="LookUp")
	public ModelAndView getByPerformer(@RequestParam("performer") String performer, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		System.out.println("in get By Performer");
		Concert c = new Concert();
		c = dao.getConcertByPerformer(performer);
		mv.addObject("concert", c);
		if (c != null) {
			session.setAttribute("concert", c);
			Concert savedconcert = (Concert)session.getAttribute("concert");
			System.out.println("Saved: " + savedconcert);
		}
		mv.setViewName("concertEvent.jsp");
		return mv;
	}
	
	
	@RequestMapping(path = "GetConcertData.do", params = "GetConcertList")
	public ModelAndView showUserConcerts(@ModelAttribute("concertList") List<Concert> userConcertList) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("yourConcertsPage.jsp");
		mv.addObject("concertList", userConcertList);
		return mv;
	}
	
	@ModelAttribute("userConcertList")
	@RequestMapping(path="GetConcertData.do", params="addThisEvent")
	public ModelAndView addCurrentConcert(@ModelAttribute("concertList") List<Concert> userConcertList,
			HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("concertEvent.jsp");
		Concert c = (Concert)(session.getAttribute("concert"));
		System.out.println("Adding current concert: "+ c);
		userConcertList.add(c);
		mv.addObject("concertList", userConcertList);
		mv.addObject(c);
		return mv;
	}
	
	@RequestMapping(path="removeConcert.do", params="performer")
	public ModelAndView removeConcert(@ModelAttribute("concertList") List<Concert> userConcertList,
										@RequestParam("performer") String performer){
		ModelAndView mv = new ModelAndView();									
		mv.setViewName("yourConcertsPage.jsp");
		System.out.println("in delete concert");
		System.out.println(performer);
		System.out.println(dao.getConcertByPerformer(performer));
		Concert c = dao.getConcertByPerformer(performer);
		userConcertList.remove(c);
		mv.addObject("userConcertList", userConcertList);
		return mv;
	}

}