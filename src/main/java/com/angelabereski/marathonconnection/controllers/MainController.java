package com.angelabereski.marathonconnection.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.angelabereski.marathonconnection.models.LoginUser;
import com.angelabereski.marathonconnection.models.RunSchedule;
import com.angelabereski.marathonconnection.models.TrainingLog;
import com.angelabereski.marathonconnection.models.User;
import com.angelabereski.marathonconnection.services.RunScheduleService;
import com.angelabereski.marathonconnection.services.TrainingLogService;
import com.angelabereski.marathonconnection.services.UserService;

@Controller
@RequestMapping("/")
public class MainController {

	@Autowired
	private UserService uService;

	@Autowired
	private TrainingLogService tService;

	@Autowired
	private RunScheduleService rService;

	@GetMapping("")
	public String index(Model model) {

		model.addAttribute("user", new User());
		model.addAttribute("logUser", new LoginUser());

		return "index.jsp";
	}

	@PostMapping("register")
	public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session,
			Model model) {
		User u = uService.register(user, result);

		if (result.hasErrors()) {
			model.addAttribute("logUser", new LoginUser());
			return "index.jsp";
		}

		session.setAttribute("loggedIn", u.getId());

		return "redirect:/dashboard";
	}

	@PostMapping("login")
	public String login(@Valid @ModelAttribute("logUser") LoginUser logUser, BindingResult result, HttpSession session,
			Model model) {

		User userDb = uService.authenticate(logUser, result);

		if (result.hasErrors()) {
			model.addAttribute("user", new User());
			return "index.jsp";
		}

		session.setAttribute("loggedIn", userDb.getId());

		return "redirect:/dashboard";
	}

	@GetMapping("dashboard")
	public String dashboard(Model model, HttpSession session) {

		if (session.getAttribute("loggedIn") == null) {
			return "redirect:/logout";
		}
		Long loggedIn = (Long) session.getAttribute("loggedIn");

		List<TrainingLog> trainingLogs = tService.allTrainingLogs();
		model.addAttribute("trainingLogs", trainingLogs);

		List<RunSchedule> runSchedules = rService.allRunSchedules();
		model.addAttribute("runSchedules", runSchedules);

		model.addAttribute("user", uService.findUser(loggedIn));

		return "dashboard.jsp";
	}

	@GetMapping("logout")
	public String logout(HttpSession session) {

		session.removeAttribute("loggedIn");

		return "redirect:/";

	}
}