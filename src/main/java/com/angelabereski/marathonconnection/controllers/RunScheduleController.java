package com.angelabereski.marathonconnection.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.angelabereski.marathonconnection.models.MileDedication;
import com.angelabereski.marathonconnection.models.RunSchedule;
import com.angelabereski.marathonconnection.models.User;
import com.angelabereski.marathonconnection.services.MileDedicationService;
import com.angelabereski.marathonconnection.services.RunScheduleService;
import com.angelabereski.marathonconnection.services.UserService;

@Controller
@RequestMapping("/runschedule")
public class RunScheduleController {

	@Autowired
	RunScheduleService rService;

	@Autowired
	UserService uService;

	@Autowired
	MileDedicationService mService;

	@GetMapping("/add")
	public String addRunSchedule(Model model, @ModelAttribute("runSchedule") RunSchedule runSchedule,
			HttpSession session) {
		if (session.getAttribute("loggedIn") == null) {
			return "redirect:/logout";
		}

		return "addRunSchedule.jsp";
	}

	@PostMapping("/add")
	public String createRunSchedule(@Valid @ModelAttribute("runSchedule") RunSchedule runSchedule, BindingResult result,
			HttpSession session) {
		if (session.getAttribute("loggedIn") == null) {
			return "redirect:/logout";
		}

		Long loggedIn = (Long) session.getAttribute("loggedIn");

		if (result.hasErrors()) {
			return "addRunSchedule.jsp";
		} else {
			User user = uService.findUser(loggedIn);
			RunSchedule newRunSchedule = new RunSchedule(runSchedule.getDateOfRun(), runSchedule.getNumMiles(),
					runSchedule.getRaceRunnerF());
			newRunSchedule.setRaceRunnerF(user);
			rService.createRunSchedule(newRunSchedule);
			user.getRunnerFutures().add(newRunSchedule);
			uService.updateUser(user);

			return "redirect:/dashboard";
		}

	}

	@GetMapping("/{id}")
	public String viewScheduledRun(Model model, @PathVariable("id") Long id, HttpSession session) {
		if (session.getAttribute("loggedIn") == null) {
			return "redirect:/logout";
		}

		Long loggedIn = (Long) session.getAttribute("loggedIn");

		model.addAttribute("user", uService.findUser(loggedIn));
//		model.addAttribute("undevelopedGames", gService.getNonDeveloperUsers(uService.findUser(loggedIn)));
//		model.addAttribute("developedGames", gService.getDevelopers(uService.findUser(loggedIn)));

		RunSchedule runSchedule = rService.findRunSchedule(id);
		model.addAttribute("runSchedule", runSchedule);

		MileDedication mileDedication = mService.findMileDedication(id);
		model.addAttribute("mileDedication", mileDedication);

		List<MileDedication> allMileDedications = mService.mileDedicationsByRunSchedule(id);
		model.addAttribute("mileDedication", allMileDedications);

		return "viewScheduledRun.jsp";
	}

	@GetMapping("/edit/{id}")
	public String editScheduledRun(@PathVariable("id") Long id, Model model) {
		RunSchedule runSchedule = rService.findRunSchedule(id);
		model.addAttribute("runSchedule", runSchedule);

		return "editScheduledRun.jsp";
	}

	@PostMapping("/edit/{id}")
	public String updateScheduledRun(@PathVariable("id") Long id,
			@Valid @ModelAttribute("runSchedule") RunSchedule runSchedule, BindingResult result) {
		RunSchedule scheduleExist = rService.findRunSchedule(id);
		if (scheduleExist == null) {
			return "editScheduledRun.jsp";
		}
		if (result.hasErrors()) {
			return "editScheduledRun.jsp";
		}

		scheduleExist.setDateOfRun(runSchedule.getDateOfRun());
		scheduleExist.setNumMiles(runSchedule.getNumMiles());
		rService.updateRunSchedule(scheduleExist);

		return "redirect:/dashboard";
	}

	@DeleteMapping("/{id}")
	public String deleteRunSchedule(Model model, @PathVariable("id") Long id, HttpSession session) {

		if (session.getAttribute("loggedIn") == null) {
			return "redirect:/logout";
		}
//		Long loggedIn = (Long) session.getAttribute("loggedIn");

		rService.destroyRunSchedule(id);
//		model.addAttribute("undevelopedGames", gService.getNonDeveloperUsers(uService.findUser(loggedIn)));
//		model.addAttribute("developedGames", gService.getDevelopers(uService.findUser(loggedIn)));

		return "redirect:/dashboard";
	}

}