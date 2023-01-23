package com.angelabereski.marathonconnection.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.angelabereski.marathonconnection.models.LoginUser;
import com.angelabereski.marathonconnection.models.User;
import com.angelabereski.marathonconnection.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository uRepository;

	public User register(User u, BindingResult result) {

		User checkEmail = uRepository.findByEmail(u.getEmail()).orElse(null);

		if (checkEmail != null) {
			result.rejectValue("email", "unique", "Email already in use");
		}

		if (!u.getPassword().equals(u.getConfirm())) {
			result.rejectValue("confirm", "matches", "Passwords must match");
		}

		if (result.hasErrors()) {
			return null;
		}

		String hash = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());
		u.setPassword(hash);

		return uRepository.save(u);
	}

	public User authenticate(LoginUser logUser, BindingResult result) {

		User userDb = uRepository.findByEmail(logUser.getEmail()).orElse(null);

		if (userDb == null) {
			result.rejectValue("email", "exists", "Invalid login attempt");
		}

		if (!BCrypt.checkpw(logUser.getPassword(), userDb.getPassword())) {
			result.rejectValue("password", "auth", "Invalid login attempt");
		}

		if (result.hasErrors()) {
			return null;
		}

		return userDb;
	}

	public User findUser(Long id) {
		return uRepository.findById(id).orElse(null);
	}

	public List<User> allUsers() {
		return uRepository.findAll();
	}

	public User updateUser(User user) {
		return uRepository.save(user);
	}
}