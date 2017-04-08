package ar.edu.itba.paw.webapp.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.User;
import ar.edu.itba.paw.service.UserService;
import ar.edu.itba.paw.webapp.form.UserForm;

@Controller
public class HelloWorldController {
	
	@Autowired
	private UserService us;
	
	@ModelAttribute("sessionId")
	public Integer loggedUserId(final HttpSession session) {
		return (Integer) session.getAttribute("sessionId");
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("registerForm") @Valid final UserForm form, final BindingResult errors) {
		if (errors.hasErrors())
			return index(form);
		
		final User user = us.create(form.getUsername(), form.getPassword());
		return new ModelAndView("redirect:/user?userId=" + user.getId());
	}
	
	@RequestMapping("/")
	private ModelAndView index(@ModelAttribute("registerForm") UserForm form) {
		final ModelAndView mav = new ModelAndView("index");
		return mav;
	}

	@RequestMapping("/user")
	public ModelAndView helloWorld(@RequestParam(name = "userId", required = true) final int userId) {
		final ModelAndView mav = new ModelAndView("userDetails");
		
		mav.addObject("user", us.findById(userId));
		
		return mav;
	}
	
	@RequestMapping("/admin/user")
	public ModelAndView helloWorldAdmin(@RequestParam(name = "userId", required = true) final int userId) {
		final ModelAndView mav = new ModelAndView("userDetails");
		
		mav.addObject("user", us.findById(userId));
		
		return mav;
	}
	
	@RequestMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	
	@RequestMapping("/403")
	public ModelAndView forbidden() {
		return new ModelAndView("403");
	}
}
