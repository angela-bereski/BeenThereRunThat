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

import com.angelabereski.marathonconnection.models.Support;
import com.angelabereski.marathonconnection.models.TrainingLog;
import com.angelabereski.marathonconnection.services.SupportService;
import com.angelabereski.marathonconnection.services.TrainingLogService;
import com.angelabereski.marathonconnection.services.UserService;

@Controller
@RequestMapping("/support")
public class SupportController {

	@Autowired
	UserService uService;

	@Autowired
	TrainingLogService tService;

	@Autowired
	SupportService sService;

	@GetMapping("/{id}")
	public String addSupport(Model model, @PathVariable("id") Long id, @ModelAttribute("support") Support support,
			HttpSession session) {
		if (session.getAttribute("loggedIn") == null) {
			return "redirect:/logout";
		}

		TrainingLog trainingLog = tService.findTrainingLog(id);
		model.addAttribute("trainingLog", trainingLog);

		return "addSupport.jsp";
	}

//	@PostMapping("/add")
//	public String createSupport(@Valid @ModelAttribute("support") Support support, BindingResult result,
//			HttpSession session) {
//		if (session.getAttribute("loggedIn") == null) {
//			return "redirect:/logout";
//		}
//
//		Long loggedIn = (Long) session.getAttribute("loggedIn");
//
//		if (result.hasErrors()) {
//			return "addSupport.jsp";
//		} else {
//			User user = uService.findUser(loggedIn);
//			Support newSupport = new Support(support.getSupport(), support.getSupportCreator());
//			newSupport.setSupportCreator(user);
//			sService.createSupport(newSupport);
//			user.getCreatedSupport().add(newSupport);
//			uService.updateUser(user);
//
//			return "redirect:/dashboard";
//		}
//
//	}

	@PostMapping("/{id}")
	public String createSupport(Model model, @PathVariable("id") Long id, HttpSession session,
			@Valid @ModelAttribute("supports") Support support, BindingResult result) {
		if (session.getAttribute("loggedIn") == null) {
			return "redirect:/logout";
		}

		Long loggedIn = (Long) session.getAttribute("loggedIn");
		TrainingLog trainingLog = tService.findTrainingLog(id);

		if (result.hasErrors()) {
			model.addAttribute("trainingLog", trainingLog);
			model.addAttribute("supports", sService.allSupports());
			return "addSupport.jsp";
		} else {
			Support newSupport = new Support(support.getSupportWords(), support.getSupportCreator());
			newSupport.setSupportCreator(uService.findUser(loggedIn));
			newSupport.setTrainingSupport(trainingLog);
			trainingLog.getSupports().add(newSupport);
			sService.createSupport(newSupport);

//			List<Support> supports = sService.allSupports();
//			model.addAttribute("supports", supports);

//			List<Support> supports = sService.supportsByTrainingLog(id);
//			model.addAttribute("supports", supports);

			return "redirect:/dashboard";
		}

	}

//	@GetMapping("/{id}")
//	public String viewMileDedication(Model model, @PathVariable("id") Long id, HttpSession session) {
//		if (session.getAttribute("loggedIn") == null) {
//			return "redirect:/logout";
//		}
//
//		Long loggedIn = (Long) session.getAttribute("loggedIn");
//
//		model.addAttribute("user", uService.findUser(loggedIn));
////		model.addAttribute("undevelopedGames", gService.getNonDeveloperUsers(uService.findUser(loggedIn)));
////		model.addAttribute("developedGames", gService.getDevelopers(uService.findUser(loggedIn)));
//
//		MileDedication mileDedication = mService.findMileDedication(id);
//		model.addAttribute("mileDedication", mileDedication);
//
//		RunSchedule runSchedule = rService.findRunSchedule(id);
//		model.addAttribute("runSchedule", runSchedule);
//
//		return "viewMileDedication.jsp";
//	}

//	@GetMapping("/edit/{id}")
//	public String editMileDedication(@PathVariable("id") Long id, Model model) {
//		MileDedication mileDedication = mService.findMileDedication(id);
//		model.addAttribute("mileDedication", mileDedication);
//
//		return "editMileDedication.jsp";
//	}
//
//	@PostMapping("/edit/{id}")
//	public String updateMileDedication(@PathVariable("id") Long id,
//			@Valid @ModelAttribute("mileDedication") MileDedication mileDedication, BindingResult result) {
//		MileDedication dedicationExist = mService.findMileDedication(id);
//		if (dedicationExist == null) {
//			return "editMileDedication.jsp";
//		}
//		if (result.hasErrors()) {
//			return "editMileDedication.jsp";
//		}
//
//		dedicationExist.setWhichMile(mileDedication.getWhichMile());
//		dedicationExist.setDedicationRequest(mileDedication.getDedicationRequest());
//		mService.updateMileDedication(dedicationExist);
//
//		return "redirect:/dashboard";
//	}

	@DeleteMapping("/{id}")
	public String deleteSupport(Model model, @PathVariable("id") Long id, HttpSession session) {

		if (session.getAttribute("loggedIn") == null) {
			return "redirect:/logout";
		}
//		Long loggedIn = (Long) session.getAttribute("loggedIn");

		sService.destroySupport(id);
//		model.addAttribute("undevelopedGames", gService.getNonDeveloperUsers(uService.findUser(loggedIn)));
//		model.addAttribute("developedGames", gService.getDevelopers(uService.findUser(loggedIn)));

		return "redirect:/dashboard";
	}

}