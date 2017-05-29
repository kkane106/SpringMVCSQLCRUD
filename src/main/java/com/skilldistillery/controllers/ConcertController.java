package com.skilldistillery.controllers;

import java.util.List;

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
//		return dao.login("ntran", "pw123");
	}

//	@ModelAttribute("concertList")
//	public List<Concert> initSessionObject() {
//		List<Concert> userConcertList = new ArrayList<>();
//		return userConcertList;
//	}
//	@ModelAttribute("user")
	@RequestMapping(path="login.do", method=RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("user") User user,
							  @RequestParam("username") String username,
							  @RequestParam("password") String password) {
		ModelAndView mv = new ModelAndView();
		user = dao.login(username, password);
		if (user == null) {
			mv.setViewName("signin.jsp");
		}
		else {
			mv.setViewName("yourConcertsPage.jsp");
			mv.addObject(user);
			mv.addObject("concertList", user.getConcertList());
		}
		return mv;
	}
	@RequestMapping(path="loginForm.do", method=RequestMethod.GET)
	public ModelAndView loginForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("signin.jsp");
		return mv;
	}
	@RequestMapping(path="logout.do", method=RequestMethod.GET)
	public ModelAndView logout() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", new User());
		mv.setViewName("index.jsp");
		return mv;
	}

	@ModelAttribute("userConcertList")
	@RequestMapping(path = "createConcert.do", method=RequestMethod.POST)
	public ModelAndView addConcertToList(Concert c, @ModelAttribute("concertList") List<Concert> userConcertList,
			@ModelAttribute("user") User user) {
		System.out.println("in create concert");
		System.out.println("concert input: " + c);
		System.out.println("concert id: " + c.getId());
		
		ModelAndView mv = new ModelAndView();
//		c = dao.getConcert(c);
//		userConcertList.add(c);
		dao.persistConcertList(userConcertList);
		dao.addConcertToList(c);
		dao.addConcertToUserList(c, user);
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
	public String showAllConcerts(Model model, @ModelAttribute("user") User user) {
		System.out.println(user);
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
	public ModelAndView showUserConcerts(
//			@ModelAttribute("concertList") List<Concert> userConcertList,
										 @ModelAttribute("user") User u) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("yourConcertsPage.jsp");
		mv.addObject("concertList", u.getConcertList());
		return mv;
	}

	@ModelAttribute("userConcertList")
	@RequestMapping(path = "addConcertToList.do", method = RequestMethod.POST)
	public ModelAndView addConcertToUserList(
//			@ModelAttribute("concertList") List<Concert> userConcertList, 
			@RequestParam("concertId") int id,
			@ModelAttribute("user") User user) {
		System.out.println(user);
		ModelAndView mv = new ModelAndView();
		Concert c = dao.getConcertById(id);
		mv.setViewName("yourConcertsPage.jsp");
		System.out.println("In add current concert");
		System.out.println("Adding current concert: " + c);
//		userConcertList.add(c);
		System.out.println(user);
		User u = dao.addConcertToUserList(c, user);
		mv.addObject("user", u);
//		dao.persistConcertList(userConcertList);
		mv.addObject("concertList",u.getConcertList());
		mv.addObject("concert", c);
		return mv;
	}

	@RequestMapping(path = "removeConcert.do", method = RequestMethod.POST)
	public ModelAndView removeConcert(
//			@ModelAttribute("concertList") List<Concert> userConcertList,
			@ModelAttribute("user") User user,
			@RequestParam("concertId") int id
//			@RequestParam("performer") String performer
			) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("yourConcertsPage.jsp");
		System.out.println("in delete concert");
		Concert c = dao.getConcertById(id);
		User u = dao.removeConcertFromUserList(c, user);
		mv.addObject("concertList",u.getConcertList());
		mv.addObject("concert", c);
//		Concert c = dao.getConcertByPerformer(performer);
//		userConcertList.remove(c);
//		dao.persistConcertList(userConcertList);
//		mv.addObject("userConcertList", userConcertList);
		return mv;
	}

	@RequestMapping(path = "saveConcerts.do")
	public ModelAndView persistConcertList(@ModelAttribute("concertList") List<Concert> userConcertList) {
		ModelAndView mv = new ModelAndView();
		dao.persistConcertList(userConcertList);
		mv.setViewName("yourConcertsPage.jsp");
		return mv;
	}
	
	@RequestMapping(path = "getYourShows.do")
	public ModelAndView getYourShows(@ModelAttribute("user") User user) {
		ModelAndView mv = new ModelAndView();
		System.out.println(user);
		if(user.getUsername()==null){
			
			mv.setViewName("signup.jsp");
		}
		else{
			mv.addObject("concertList", user.getConcertList());
			mv.setViewName("yourConcertsPage.jsp");
		}
		return mv;
	}

}