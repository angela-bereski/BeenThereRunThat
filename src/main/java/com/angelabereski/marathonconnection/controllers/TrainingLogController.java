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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.angelabereski.marathonconnection.models.Support;
import com.angelabereski.marathonconnection.models.TrainingLog;
import com.angelabereski.marathonconnection.models.User;
import com.angelabereski.marathonconnection.services.FilesService;
import com.angelabereski.marathonconnection.services.MileDedicationService;
import com.angelabereski.marathonconnection.services.SupportService;
import com.angelabereski.marathonconnection.services.TrainingLogService;
import com.angelabereski.marathonconnection.services.UserService;

@Controller
@RequestMapping("/training")
public class TrainingLogController {

	@Autowired
	TrainingLogService tService;

	@Autowired
	UserService uService;

	@Autowired
	SupportService sService;

	@Autowired
	MileDedicationService mService;

	@Autowired
	FilesService fService;

	@GetMapping("/add")
	public String addTrainingLog(Model model, @ModelAttribute("trainingLog") TrainingLog trainingLog,
			HttpSession session) {
		if (session.getAttribute("loggedIn") == null) {
			return "redirect:/logout";
		}

		return "addTrainingLog.jsp";
	}

	@PostMapping("/add")
	public String createTrainingLog(@RequestParam("file") MultipartFile file,
			@Valid @ModelAttribute("trainingLog") TrainingLog trainingLog, BindingResult result, HttpSession session) {
		if (session.getAttribute("loggedIn") == null) {
			return "redirect:/logout";
		}

//		trainingLog.setImagePath(this.fService.save(file));
//		this.tService.createTrainingLog(trainingLog);

		Long loggedIn = (Long) session.getAttribute("loggedIn");

		if (result.hasErrors()) {
			return "addTrainingLog.jsp";
		} else {
			User user = uService.findUser(loggedIn);
			TrainingLog newTrainingLog = new TrainingLog(trainingLog.getName(), trainingLog.getDateOfRun(),
					trainingLog.getLocation(), trainingLog.getNumMiles(), trainingLog.getRanWith(),
					trainingLog.getRunReflection(), trainingLog.getRunNutrition(), trainingLog.getRaceRunner());
			newTrainingLog.setRaceRunner(user);
			newTrainingLog.setImagePath(this.fService.save(file));
			this.tService.createTrainingLog(newTrainingLog);
			user.getRunnerLogs().add(newTrainingLog);
			uService.updateUser(user);

			return "redirect:/dashboard";
		}

	}

	@GetMapping("/{id}")
	public String viewTrainingLog(Model model, @PathVariable("id") Long id, HttpSession session) {
		if (session.getAttribute("loggedIn") == null) {
			return "redirect:/logout";
		}

		Long loggedIn = (Long) session.getAttribute("loggedIn");

		model.addAttribute("user", uService.findUser(loggedIn));
//		model.addAttribute("undevelopedGames", gService.getNonDeveloperUsers(uService.findUser(loggedIn)));
//		model.addAttribute("developedGames", gService.getDevelopers(uService.findUser(loggedIn)));

		TrainingLog trainingLog = tService.findTrainingLog(id);
		model.addAttribute("trainingLog", trainingLog);
//
//		Support support = sService.findSupport(id);
//		model.addAttribute("support", support);
//
//		List<Support> allSupports = sService.supportsByTrainingLog(id);
//		model.addAttribute("supports", allSupports);

		List<Support> supports = sService.allSupports();
		model.addAttribute("supports", supports);

		return "viewTrainingLog.jsp";
	}

	@GetMapping("/edit/{id}")
	public String editTrainingLog(@PathVariable("id") Long id, Model model) {
		TrainingLog trainingLog = tService.findTrainingLog(id);
		model.addAttribute("trainingLog", trainingLog);

		return "editTrainingLog.jsp";
	}

	@PostMapping("/edit/{id}")
	public String updateTrainingLog(@PathVariable("id") Long id,
			@Valid @ModelAttribute("trainingLog") TrainingLog trainingLog, BindingResult result) {
		TrainingLog logExist = tService.findTrainingLog(id);
		if (logExist == null) {
			return "editTrainingLog.jsp";
		}
		if (result.hasErrors()) {
			return "editTrainingLog.jsp";
		}

		logExist.setName(trainingLog.getName());
		logExist.setDateOfRun(trainingLog.getDateOfRun());
		logExist.setLocation(trainingLog.getLocation());
		logExist.setNumMiles(trainingLog.getNumMiles());
		logExist.setRanWith(trainingLog.getRanWith());
		logExist.setRunReflection(trainingLog.getRunReflection());
		logExist.setRunNutrition(trainingLog.getRunNutrition());
		tService.updateTrainingLog(logExist);

		return "redirect:/dashboard";
	}

	@DeleteMapping("/{id}")
	public String deleteTrainingLog(Model model, @PathVariable("id") Long id, HttpSession session) {

		if (session.getAttribute("loggedIn") == null) {
			return "redirect:/logout";
		}
//		Long loggedIn = (Long) session.getAttribute("loggedIn");

		tService.destroyTrainingLog(id);
//		model.addAttribute("undevelopedGames", gService.getNonDeveloperUsers(uService.findUser(loggedIn)));
//		model.addAttribute("developedGames", gService.getDevelopers(uService.findUser(loggedIn)));

		return "redirect:/dashboard";
	}

}