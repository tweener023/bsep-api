package com.bezkoder.spring.security.postgresql.controllers;

import com.bezkoder.spring.security.postgresql.dtos.SkillDTO;
import com.bezkoder.spring.security.postgresql.dtos.UserDTO;
import com.bezkoder.spring.security.postgresql.models.Skill;
import com.bezkoder.spring.security.postgresql.models.User;
import com.bezkoder.spring.security.postgresql.security.services.SkillService;
import com.bezkoder.spring.security.postgresql.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private UserDetailsServiceImpl userService;

	@Autowired
	SkillService skillService;


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
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<UserDTO> activateUser(@PathVariable("profileId") String profileId) {

		// a user must exist
		Long id = Long.parseLong(profileId);
		User user = userService.findOne(id);

		if (user == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		user.setApproved(true);

		user = userService.save(user);
		return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
	}


	//ne diraj
	@GetMapping(value = "/unactivated")
	public ResponseEntity<List<UserDTO>> getUnactivatedUsers() {

		List<User> users = (List<User>) userService.findAll();

		// convert users to DTOs
		List<UserDTO> usersDTO = new ArrayList<>();
		for (User u : users) {
			if((u.getApproved() == null) ||(u.getApproved() == false) ){
				usersDTO.add(new UserDTO(u));
			}
		}

		return new ResponseEntity<>(usersDTO, HttpStatus.OK);
	}

/*
	@GetMapping("/{profileId}/skill")
	public ResponseEntity<List<SkillDTO>> getUserSkills(@PathVariable("profileId") String profileId) {

		// a user must exist
		Long id = Long.parseLong(profileId);
		User user = userService.findOne(id);

		Set<Skill> skills = user.getSkills();

		List<SkillDTO> skillDTO = new ArrayList<>();
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		for (Skill e : skills) {
			SkillDTO appointmentDTO = new SkillDTO();
			appointmentDTO.setSkillId(e.getSkillId());
			appointmentDTO.setSkillName(e.getSkillName());
			appointmentDTO.setSkillLevel(e.getSkillLevel());

			skillDTO.add(appointmentDTO);
		}
		return new ResponseEntity<>(skillDTO, HttpStatus.OK);
	}
*/
@GetMapping(value = "/{userId}/skill")
public ResponseEntity<List<SkillDTO>> getUserSkills(@PathVariable String userId) {
	Long id = Long.parseLong(userId);
	User user = userService.findOne(id);
	Set<Skill> appointments = user.getSkills();
	List<SkillDTO> skillsDTO = new ArrayList<>();
	for (Skill e : appointments) {
		SkillDTO appointmentDTO = new SkillDTO();
		appointmentDTO.setSkillId(e.getSkillId());
		appointmentDTO.setSkillLevel(e.getSkillLevel());
		appointmentDTO.setUser(new UserDTO(e.getUser()));
		appointmentDTO.setSkillName(e.getSkillName());

		skillsDTO.add(appointmentDTO);
	}
	return new ResponseEntity<>(skillsDTO, HttpStatus.OK);
}


	@PutMapping("/{skillId}/deleteSkill")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<SkillDTO> deleteSkill(@PathVariable("skillId") String skillId) {

		// a user must exist
		Long id = Long.parseLong(skillId);
		Skill skill = skillService.findOne(id);

		if (skill == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		skill.setIsDeleted(true);

		skill = skillService.save(skill);

		return new ResponseEntity<>(new SkillDTO(skill), HttpStatus.OK);
	}

}
