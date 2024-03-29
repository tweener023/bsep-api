package com.bezkoder.spring.security.postgresql.controllers;

import com.bezkoder.spring.security.postgresql.dtos.PermissionsDTO;
import com.bezkoder.spring.security.postgresql.dtos.ProjectDTO;
import com.bezkoder.spring.security.postgresql.dtos.SkillDTO;
import com.bezkoder.spring.security.postgresql.dtos.UserDTO;
import com.bezkoder.spring.security.postgresql.models.*;
import com.bezkoder.spring.security.postgresql.repository.FileRepository;
import com.bezkoder.spring.security.postgresql.security.services.PermissionService;
import com.bezkoder.spring.security.postgresql.security.services.ProjectService;
import com.bezkoder.spring.security.postgresql.security.services.SkillService;
import com.bezkoder.spring.security.postgresql.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

	@Autowired
	PermissionService permissionService;

	@Autowired
	FileRepository fileRepository;

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


	private boolean hasCreatePermission(User user) {
		Permissions userPermissions = permissionService.findOneByUser(user);
		return userPermissions.getCreate();
	}

	private boolean hasReadPermission(User user) {
		Permissions userPermissions = permissionService.findOneByUser(user);
		return userPermissions.getRead();
	}

	private boolean hasUpdatePermission(User user) {
		Permissions userPermissions = permissionService.findOneByUser(user);
		return userPermissions.getUpdate();
	}

	private boolean hasDeletePermission(User user) {
		Permissions userPermissions = permissionService.findOneByUser(user);
		return userPermissions.getDelete();
	}

	@GetMapping(value = "/{userId}/skill")
	@PreAuthorize("hasAnyRole('ENGINEER')")
	public ResponseEntity<List<SkillDTO>> getUserSkills(@PathVariable String userId) {
		Long id = Long.parseLong(userId);
		User user = userService.findOne(id);
		if(hasReadPermission(user)){
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
		else return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	@PutMapping("/{skillId}/editSkill")
	@PreAuthorize("hasAnyRole('ENGINEER')")
	public ResponseEntity<SkillDTO> updateSkill(@PathVariable("skillId") String skillId, @RequestBody SkillDTO skillRequest) {

		// a skill must exist
		Long id = Long.parseLong(skillId);
		Skill skill = skillService.findOne(id);
		User user = userService.findOne(skill.getUser().getId());

		if(hasUpdatePermission(user)){	if (skill == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

			skill.setSkillName(skillRequest.getSkillName());
			skill.setSkillLevel(skillRequest.getSkillLevel());
			skill.setIsDeleted(false);

			skill = skillService.save(skill);

			return new ResponseEntity<>(new SkillDTO(skill), HttpStatus.OK);
		}
		else return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}


	@PostMapping("/{userId}/addSkill")
	@PreAuthorize("hasAnyRole('ENGINEER')")
	public ResponseEntity<SkillDTO> addSkill(@PathVariable("userId") String userId, @RequestBody SkillDTO skillRequest) {

		// a user must exist
		Long id = Long.parseLong(userId);
		User user = userService.findOne(skillRequest.getUser().getId());

		if(hasCreatePermission(user)){
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
		else return new ResponseEntity<>(HttpStatus.FORBIDDEN);

	}

	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file) {
		if (file.isEmpty()) {
			return ResponseEntity.badRequest().body("File is required");
		}

		// Perform other validations or processing on the file as needed

		// Save the file
		try {
			FileEntity fileEntity = new FileEntity();
			fileEntity.setFileName(file.getOriginalFilename());
			fileEntity.setFileData(file.getBytes());
			fileRepository.save(fileEntity);
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload the file");
		}

		return ResponseEntity.ok("File uploaded successfully");
	}

	@PutMapping("/{skillId}/deleteSkill")
	public ResponseEntity<SkillDTO> deleteSkill(@PathVariable("skillId") String skillId) {

		// a user must exist
		Long id = Long.parseLong(skillId);
		Skill skill = skillService.findOne(id);
		User user = userService.findOne(skill.getUser().getId());

		if(hasDeletePermission(user)){
			if (skill == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			skill.setIsDeleted(true);
			skill = skillService.save(skill);

			return new ResponseEntity<>(new SkillDTO(skill), HttpStatus.OK);

		}
		else return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	@GetMapping(value = "/{userId}/project")
	@PreAuthorize("hasAnyRole('ENGINEER')")
	public ResponseEntity<List<ProjectDTO>> getUserProjects(@PathVariable String userId) {
		Long id = Long.parseLong(userId);
		User user = userService.findOne(id);
		if(hasReadPermission(user)){	Set<Project> projects = user.getProjects();
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
		else return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	@PutMapping("/{projectId}/editProject")
	@PreAuthorize("hasAnyRole('ENGINEER')")
	public ResponseEntity<ProjectDTO> updateProject(@PathVariable("projectId") String projectId, @RequestBody ProjectDTO projectRequest) {

		// a skill must exist
		Long id = Long.parseLong(projectId);
		Project project = projectService.findOne(id);

		User user = userService.findOne(project.getUser().getId());

		if(hasUpdatePermission(user)){
			if (project == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			project.setProjectDescription(projectRequest.getProjectDescription());
			project = projectService.save(project);

			return new ResponseEntity<>(new ProjectDTO(project), HttpStatus.OK);
		}
		else return new ResponseEntity<>(HttpStatus.FORBIDDEN);

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

	@PutMapping("/{userId}/giveCrudPermissions")
	public ResponseEntity<PermissionsDTO> giveCrudPermissions(@PathVariable("userId") String userId) {

		// a user must exist
		Long id = Long.parseLong(userId);
		User user = userService.findOne(id);
		Permissions permissions = permissionService.findOneByUser(user);

		if (user == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if (permissions == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		permissions.setCreate(true);
		permissions.setRead(true);
		permissions.setUpdate(true);
		permissions.setDelete(true);

		permissions = permissionService.save(permissions);

		return new ResponseEntity<>(new PermissionsDTO(permissions), HttpStatus.OK);

	}

	@PutMapping("/{userId}/unauthorize")
	public ResponseEntity<PermissionsDTO> unauthorize(@PathVariable("userId") String userId) {

		// a user must exist
		Long id = Long.parseLong(userId);
		User user = userService.findOne(id);
		Permissions permissions = permissionService.findOneByUser(user);

		if (user == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if (permissions == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		permissions.setCreate(false);
		permissions.setRead(true);
		permissions.setUpdate(false);
		permissions.setDelete(false);

		permissions = permissionService.save(permissions);

		return new ResponseEntity<>(new PermissionsDTO(permissions), HttpStatus.OK);

	}



}
