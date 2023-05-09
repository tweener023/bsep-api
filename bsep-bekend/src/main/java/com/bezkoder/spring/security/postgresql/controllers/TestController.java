package com.bezkoder.spring.security.postgresql.controllers;

import com.bezkoder.spring.security.postgresql.dtos.UserDTO;
import com.bezkoder.spring.security.postgresql.models.User;
import com.bezkoder.spring.security.postgresql.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private UserDetailsServiceImpl userService;


	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('ENGINEER') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/engineer")
	@PreAuthorize("hasRole('ENGINEER')")
	public String engineerAccess() {
		return "Engineer Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}

	@PutMapping("/{profileId}/activate")
	public ResponseEntity<UserDTO> activateUser(@PathVariable("profileId") String profileId) {

		// a user must exist
		Long id = Long.parseLong(profileId);
		User user = userService.findOne(id);

		if (user == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		user.setApproved(true);
		System.out.print("-------------------------------------------------------");

		System.out.print("-------------------------------------------------------");


		System.out.print("Userov Approved je " +  user.getApproved());
		System.out.print("Userovo ime je " +  user.getUsername());

		System.out.print("-------------------------------------------------------");

		System.out.print("-------------------------------------------------------");
		user = userService.save(user);
		return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
	}



	@GetMapping(value = "/unactivated")
	public ResponseEntity<List<UserDTO>> getUnactivatedUsers() {

		List<User> users = (List<User>) userService.findAll();

		// convert users to DTOs
		List<UserDTO> usersDTO = new ArrayList<>();
		for (User u : users) {
			if(!u.getApproved()){
				usersDTO.add(new UserDTO(u));
			}
		}

		return new ResponseEntity<>(usersDTO, HttpStatus.OK);
	}

}
