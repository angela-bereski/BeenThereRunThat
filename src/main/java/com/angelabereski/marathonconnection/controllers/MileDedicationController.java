package com.angelabereski.marathonconnection.controllers;

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
import com.angelabereski.marathonconnection.services.MileDedicationService;
import com.angelabereski.marathonconnection.services.RunScheduleService;
import com.angelabereski.marathonconnection.services.UserService;

@Controller
@RequestMapping("/milededication")
public class MileDedicationController {

	@Autowired
	UserService uService;

	@Autowired
	MileDedicationService mService;

	@Autowired
	RunScheduleService rService;

	@GetMapping("/{id}")
	public String addMileDedication(Model model, @PathVariable("id") Long id,
			@ModelAttribute("mileDedication") MileDedication mileDedication, HttpSession session) {
		if (session.getAttribute("loggedIn") == null) {
			return "redirect:/logout";
		}

		RunSchedule runSchedule = rService.findRunSchedule(id);
		model.addAttribute("runSchedule", runSchedule);

		return "addMileDedication.jsp";
	}

	@PostMapping("/{id}")
	public String createMileDedication(Model model, @PathVariable("id") Long id, HttpSession session,
			@Valid @ModelAttribute("mileDedication") MileDedication mileDedication, BindingResult result) {
		if (session.getAttribute("loggedIn") == null) {
			return "redirect:/logout";
		}

		Long loggedIn = (Long) session.getAttribute("loggedIn");
		RunSchedule runSchedule = rService.findRunSchedule(id);

		if (result.hasErrors()) {
			model.addAttribute("runSchedule", runSchedule);
			model.addAttribute("mileDedications", mService.allMileDedications());
			return "addMileDedication.jsp";
		} else {
			MileDedication newMileDedication = new MileDedication(mileDedication.getWhichMile(),
					mileDedication.getDedicationRequest(), mileDedication.getMileRequester());
			newMileDedication.setMileRequester(uService.findUser(loggedIn));
			newMileDedication.setRunScheduleMileDedication(runSchedule);
			runSchedule.getDedications().add(newMileDedication);
			mService.createMileDedication(newMileDedication);

			return "redirect:/dashboard";
		}

	}

	@GetMapping("/view/{id}")
	public String viewMileDedication(Model model, @PathVariable("id") Long id, HttpSession session) {
		if (session.getAttribute("loggedIn") == null) {
			return "redirect:/logout";
		}

		Long loggedIn = (Long) session.getAttribute("loggedIn");

		model.addAttribute("user", uService.findUser(loggedIn));
//		model.addAttribute("undevelopedGames", gService.getNonDeveloperUsers(uService.findUser(loggedIn)));
//		model.addAttribute("developedGames", gService.getDevelopers(uService.findUser(loggedIn)));

		MileDedication mileDedication = mService.findMileDedication(id);
		model.addAttribute("mileDedication", mileDedication);

		RunSchedule runSchedule = rService.findRunSchedule(id);
		model.addAttribute("runSchedule", runSchedule);

		return "viewMileDedication.jsp";
	}

	@GetMapping("/edit/{id}")
	public String editMileDedication(@PathVariable("id") Long id, Model model) {
		MileDedication mileDedication = mService.findMileDedication(id);
		model.addAttribute("mileDedication", mileDedication);

		return "editMileDedication.jsp";
	}

	@PostMapping("/edit/{id}")
	public String updateMileDedication(@PathVariable("id") Long id,
			@Valid @ModelAttribute("mileDedication") MileDedication mileDedication, BindingResult result) {
		MileDedication dedicationExist = mService.findMileDedication(id);
		if (dedicationExist == null) {
			return "editMileDedication.jsp";
		}
		if (result.hasErrors()) {
			return "editMileDedication.jsp";
		}

		dedicationExist.setWhichMile(mileDedication.getWhichMile());
		dedicationExist.setDedicationRequest(mileDedication.getDedicationRequest());
		mService.updateMileDedication(dedicationExist);

		return "redirect:/dashboard";
	}

	@DeleteMapping("/{id}")
	public String deleteMileDedication(Model model, @PathVariable("id") Long id, HttpSession session) {

		if (session.getAttribute("loggedIn") == null) {
			return "redirect:/logout";
		}
//		Long loggedIn = (Long) session.getAttribute("loggedIn");

		mService.destroyMileDedication(id);
//		model.addAttribute("undevelopedGames", gService.getNonDeveloperUsers(uService.findUser(loggedIn)));
//		model.addAttribute("developedGames", gService.getDevelopers(uService.findUser(loggedIn)));

		return "redirect:/dashboard";
	}

}