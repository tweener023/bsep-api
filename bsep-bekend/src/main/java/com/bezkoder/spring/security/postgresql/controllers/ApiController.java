package com.bezkoder.spring.security.postgresql.controllers;

import com.bezkoder.spring.security.postgresql.dtos.ProjectDTO;
import com.bezkoder.spring.security.postgresql.dtos.SkillDTO;
import com.bezkoder.spring.security.postgresql.dtos.UserDTO;
import com.bezkoder.spring.security.postgresql.models.Project;
import com.bezkoder.spring.security.postgresql.models.Skill;
import com.bezkoder.spring.security.postgresql.models.User;
import com.bezkoder.spring.security.postgresql.security.services.ProjectService;
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

@CrossOrigin
@RestController
@RequestMapping("/api/test")
public class ApiController {

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private UserDetailsServiceImpl userService;

	@Autowired
	SkillService skillService;

	@Autowired
	ProjectService projectService;


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

@GetMapping(value = "/{userId}/skill")
@PreAuthorize("hasAnyRole('ENGINEER')")
public ResponseEntity<List<SkillDTO>> getUserSkills(@PathVariable String userId) {
	Long id = Long.parseLong(userId);
	User user = userService.findOne(id);
	Set<Skill> skills = user.getSkills();
	List<SkillDTO> skillsDTO = new ArrayList<>();
	for (Skill e : skills) {

		if(e.getIsDeleted() == false){
			SkillDTO appointmentDTO = new SkillDTO();
			appointmentDTO.setSkillId(e.getSkillId());
			appointmentDTO.setSkillLevel(e.getSkillLevel());
			appointmentDTO.setUser(new UserDTO(e.getUser()));
			appointmentDTO.setSkillName(e.getSkillName());

			skillsDTO.add(appointmentDTO);
		}
	}
	return new ResponseEntity<>(skillsDTO, HttpStatus.OK);
}

	@PutMapping("/{skillId}/deleteSkill")
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

	@PutMapping("/{skillId}/editSkill")
	@PreAuthorize("hasAnyRole('ENGINEER')")
	public ResponseEntity<SkillDTO> updateSkill(@PathVariable("skillId") String skillId, @RequestBody SkillDTO skillRequest) {

		// a skill must exist
		Long id = Long.parseLong(skillId);
		Skill skill = skillService.findOne(id);

		if (skill == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		skill.setSkillName(skillRequest.getSkillName());
		skill.setSkillLevel(skillRequest.getSkillLevel());
		skill.setIsDeleted(false);

		skill = skillService.save(skill);

		return new ResponseEntity<>(new SkillDTO(skill), HttpStatus.OK);
	}


	@PostMapping("/{userId}/addSkill")
	@PreAuthorize("hasAnyRole('ENGINEER')")
	public ResponseEntity<SkillDTO> addSkill(@PathVariable("userId") String userId, @RequestBody SkillDTO skillRequest) {

		// a user must exist
		Long id = Long.parseLong(userId);
		User user = userService.findOne(skillRequest.getUser().getId());

		if (user == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Skill newSkill = new Skill();
		newSkill.setSkillName(skillRequest.getSkillName());
		newSkill.setSkillLevel(skillRequest.getSkillLevel());
		newSkill.setIsDeleted(false);

		newSkill.setUser(user);

		newSkill = skillService.save(newSkill);
		return new ResponseEntity<>(new SkillDTO(newSkill), HttpStatus.CREATED);
	}


	@GetMapping(value = "/{userId}/project")
	@PreAuthorize("hasAnyRole('ENGINEER')")
	public ResponseEntity<List<ProjectDTO>> getUserProjects(@PathVariable String userId) {
		Long id = Long.parseLong(userId);
		User user = userService.findOne(id);
		Set<Project> projects = user.getProjects();
		List<ProjectDTO> projectsDTO = new ArrayList<>();
		for (Project e : projects) {

			if(e.getIsDeleted() == false){
				ProjectDTO projectDTO = new ProjectDTO();
				projectDTO.setProjectId(e.getProjectId());
				projectDTO.setProjectDescription(e.getProjectDescription());
				projectDTO.setUser(new UserDTO(e.getUser()));
				projectDTO.setProjectName(e.getProjectName());

				projectsDTO.add(projectDTO);
			}
		}
		return new ResponseEntity<>(projectsDTO, HttpStatus.OK);
	}

	@PutMapping("/{projectId}/editProject")
	@PreAuthorize("hasAnyRole('ENGINEER')")
	public ResponseEntity<ProjectDTO> updateProject(@PathVariable("projectId") String projectId, @RequestBody ProjectDTO projectRequest) {

		// a skill must exist
		Long id = Long.parseLong(projectId);
		Project project = projectService.findOne(id);

		if (project == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		project.setProjectDescription(projectRequest.getProjectDescription());

		project = projectService.save(project);

		return new ResponseEntity<>(new ProjectDTO(project), HttpStatus.OK);
	}

	@PutMapping("/{userId}/editUser")
	public ResponseEntity<UserDTO> updateUser(@PathVariable("userId") String userId,@RequestBody UserDTO userDTO) {

		// a user must exist
		Long id = Long.parseLong(userId);
		User user = userService.findOne(id);
		
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		user.setPassword(encoder.encode(userDTO.getPassword()));
		System.out.println("EVO JE SIFRA "+ user.getPassword());

		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setAddress(userDTO.getAddress());
		user.setCity(userDTO.getCity());
		user.setCountry(userDTO.getCountry());
		user.setCountry(userDTO.getCountry());
		user.setPhoneNumber(userDTO.getPhoneNumber());
		user.setTitle(userDTO.getTitle());
		user.setApproved(true);

		user = userService.save(user);
		return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
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

		user = userService.save(user);
		return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
	}



}
