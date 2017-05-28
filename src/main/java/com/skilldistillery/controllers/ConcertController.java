package com.skilldistillery.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.data.AuthenticationDAO;
import com.skilldistillery.data.Concert;
import com.skilldistillery.data.ConcertDAO;
import com.skilldistillery.data.User;

@Controller
@SessionAttributes({ "concertList", "user" })
public class ConcertController {

	@Autowired
	private ConcertDAO dao;
	private AuthenticationDAO authDao;

	@ModelAttribute("user")
	public User newUser() {
		return new User();
	}

	@ModelAttribute("concertList")
	public List<Concert> initSessionObject() {
		List<Concert> userConcertList = new ArrayList<>();
		return userConcertList;
	}

	@ModelAttribute("userConcertList")
	@RequestMapping(path = "createConcert.do", method = RequestMethod.POST)
	public ModelAndView addConcertToList(Concert c, @ModelAttribute("concertList") List<Concert> userConcertList,
			HttpSession session) {
		System.out.println("in create concert");
		System.out.println("concert input: " + c);
		System.out.println("concert id: " + c.getId());
//		c.setId(dao.addConcertToList(c));
//		c.setId(18);
		ModelAndView mv = new ModelAndView();
//		c = dao.getConcert(c);
//		userConcertList.add(c);
		dao.persistConcertList(userConcertList);
		dao.addConcertToList(c);
		dao.addConcertToUserList(c);
		List<Concert> concerts = dao.getAllConcerts();
		for (Concert concert : concerts) {
			System.out.println(concert);
		}
		mv.addObject("concert", c);
		mv.addObject("userConcertList", userConcertList);
		mv.setViewName("yourConcertsPage.jsp");
		return mv;
	}


	@RequestMapping(path = "getAllShows.do")
	public String showAllConcerts(Model model) {
		List<Concert> concerts = dao.getAllConcerts();
		for (Concert concert : concerts) {
			System.out.println(concert);
		}
		model.addAttribute("concerts", concerts);
		return "ConcertsPage.jsp";
	}

	@RequestMapping(path = "GetConcertData.do", params = "LookUp")
	public ModelAndView getByPerformer(@RequestParam("performer") String performer) {
		ModelAndView mv = new ModelAndView();
		System.out.println("in get By Performer");
		System.out.println(performer);
		System.out.println(dao.getConcertByPerformer(performer));
		mv.addObject("concert", dao.getConcertByPerformer(performer));
		mv.setViewName("concertEvent.jsp");
		return mv;
	}

	@RequestMapping(path = "GetConcertData.do", params = "GetConcertList")
	public ModelAndView showUserConcerts(@ModelAttribute("concertList") List<Concert> userConcertList,
										 @ModelAttribute("user") User user) {
		ModelAndView mv = new ModelAndView();
		userConcertList = dao.getUserConcertList(user.getId());
		mv.setViewName("yourConcertsPage.jsp");
		mv.addObject("concertList", userConcertList);
		return mv;
	}

	@ModelAttribute("userConcertList")
	@RequestMapping(path = "addConcertToList.do", method = RequestMethod.POST)
	public ModelAndView addCurrentConcert(@ModelAttribute("concertList") List<Concert> userConcertList, Concert c,
			@ModelAttribute("user") User user) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("yourConcertsPage.jsp");
		System.out.println("In add current concert");
		System.out.println("Adding current concert: " + c);
		userConcertList.add(c);
		dao.addConcertToUserList(c);
		dao.persistConcertList(userConcertList);
		mv.addObject("concertList", userConcertList);
		mv.addObject("concert", c);
		return mv;
	}

	@RequestMapping(path = "removeConcert.do", params = "performer", method = RequestMethod.POST)
	public ModelAndView removeConcert(@ModelAttribute("concertList") List<Concert> userConcertList,
			@RequestParam("performer") String performer) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("yourConcertsPage.jsp");
		System.out.println("in delete concert");
		System.out.println(performer);
		System.out.println(dao.getConcertByPerformer(performer));
		Concert c = dao.getConcertByPerformer(performer);
		userConcertList.remove(c);
		dao.persistConcertList(userConcertList);
		mv.addObject("userConcertList", userConcertList);
		return mv;
	}

	@RequestMapping(path = "saveConcerts.do")
	public ModelAndView persistConcertList(@ModelAttribute("concertList") List<Concert> userConcertList) {
		ModelAndView mv = new ModelAndView();
		dao.persistConcertList(userConcertList);
		mv.setViewName("yourConcertsPage.jsp");
		return mv;
	}

}