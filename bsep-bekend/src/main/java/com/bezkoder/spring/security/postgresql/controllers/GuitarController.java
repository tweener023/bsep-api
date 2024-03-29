package com.bezkoder.spring.security.postgresql.controllers;


import com.bezkoder.spring.security.postgresql.dtos.GuitarDTO;
import com.bezkoder.spring.security.postgresql.dtos.SkillDTO;
import com.bezkoder.spring.security.postgresql.dtos.UserDTO;
import com.bezkoder.spring.security.postgresql.models.Guitar;
import com.bezkoder.spring.security.postgresql.models.Skill;
import com.bezkoder.spring.security.postgresql.models.User;
import com.bezkoder.spring.security.postgresql.security.services.GuitarService;
import com.bezkoder.spring.security.postgresql.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("api/horder/guitars/")
public class GuitarController {

    @Autowired
    GuitarService guitarService;

    @Autowired
    UserDetailsServiceImpl userService;


    /* get all guitars */
    @GetMapping(value = "/all")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<GuitarDTO>> getAllGuitars() {
        List<GuitarDTO> guitarsDTO = new ArrayList<>();
        List<Guitar> guitars = guitarService.findAll();
        for (Guitar g : guitars) {
            GuitarDTO guitarDTO = new GuitarDTO();

            guitarDTO.setId(g.getId());
            guitarDTO.setManufacturerOfGuitar(g.getManufacturerOfGuitar());
            guitarDTO.setModelOfGuitar(g.getModelOfGuitar());
            guitarDTO.setYearOfProduction(g.getYearOfProduction());
            guitarDTO.setPrice(g.getPrice());
            guitarDTO.setStateOfGuitar(g.getStateOfGuitar());
            guitarDTO.setTypeOfGuitar(g.getTypeOfGuitar());
            guitarDTO.setTypeOfMagnets(g.getTypeOfMagnets());
            guitarDTO.setTuners(g.getTuners());
            guitarDTO.setTypeOfWood(g.getTypeOfWood());
            guitarDTO.setDescription(g.getDescription());
            guitarDTO.setUserId(g.getUser().getId());

            guitarsDTO.add(guitarDTO);
        }
        return new ResponseEntity<>(guitarsDTO, HttpStatus.OK);
    }

    @GetMapping("/{guitarId}")
    public ResponseEntity<Guitar> getGuitarById(@PathVariable Long guitarId) {
        Guitar guitar = guitarService.findOne(guitarId);
        return ResponseEntity.ok(guitar);
    }

    @PostMapping(value = "/order/{guitarId}/user/{userId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<String> orderGuitar(@PathVariable Long guitarId, @PathVariable Long userId) {
        try {
            // Retrieve the guitar by ID
            Optional<Guitar> optionalGuitar = Optional.ofNullable(guitarService.findOne(guitarId));

            if (optionalGuitar.isPresent()) {
                // Retrieve the user by ID
                Optional<User> optionalUser = Optional.ofNullable(userService.findOne(userId));

                if (optionalUser.isPresent()) {
                    Guitar guitar = optionalGuitar.get();
                    User user = optionalUser.get();

                    guitar.setUser(user);
                    guitarService.save(guitar);

                    return ResponseEntity.ok("Guitar ordered successfully for user with ID: " + userId);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID " + userId + " not found.");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Guitar with ID " + guitarId + " not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping(value = "/{userId}/orders")
    public ResponseEntity<List<GuitarDTO>> getUserOrders(@PathVariable String userId) {
        Long id = Long.parseLong(userId);
        User user = userService.findOne(id);
        Set<Guitar> guitars = user.getGuitars();
        List<GuitarDTO> guitarsDTO = new ArrayList<>();
        for (Guitar g : guitars) {
            GuitarDTO guitarDTO = new GuitarDTO();

            guitarDTO.setId(g.getId());
            guitarDTO.setManufacturerOfGuitar(g.getManufacturerOfGuitar());
            guitarDTO.setModelOfGuitar(g.getModelOfGuitar());
            guitarDTO.setYearOfProduction(g.getYearOfProduction());
            guitarDTO.setPrice(g.getPrice());
            guitarDTO.setStateOfGuitar(g.getStateOfGuitar());
            guitarDTO.setTypeOfGuitar(g.getTypeOfGuitar());
            guitarDTO.setTypeOfMagnets(g.getTypeOfMagnets());
            guitarDTO.setTuners(g.getTuners());
            guitarDTO.setTypeOfWood(g.getTypeOfWood());
            guitarDTO.setDescription(g.getDescription());
            guitarDTO.setUserId(g.getUser().getId());


            guitarsDTO.add(guitarDTO);

        }
        return new ResponseEntity<>(guitarsDTO, HttpStatus.OK);
    }

    @PostMapping("/{userId}/addGuitar")
    public ResponseEntity<GuitarDTO> addGuitar(@PathVariable("userId") String userId, @RequestBody GuitarDTO g) {

        Long id = Long.parseLong(userId);
        User user = userService.findOne(id);


        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Guitar newGuitar = new Guitar();
        newGuitar.setId(g.getId());
        newGuitar.setManufacturerOfGuitar(g.getManufacturerOfGuitar());
        newGuitar.setModelOfGuitar(g.getModelOfGuitar());
        newGuitar.setYearOfProduction(g.getYearOfProduction());
        newGuitar.setPrice(g.getPrice());
        newGuitar.setStateOfGuitar(g.getStateOfGuitar());
        newGuitar.setTypeOfGuitar(g.getTypeOfGuitar());
        newGuitar.setTypeOfMagnets(g.getTypeOfMagnets());
        newGuitar.setTuners(g.getTuners());
        newGuitar.setTypeOfWood(g.getTypeOfWood());
        newGuitar.setDescription(g.getDescription());

        newGuitar.setUser(user);

        newGuitar = guitarService.save(newGuitar);
        return new ResponseEntity<>(new GuitarDTO(newGuitar), HttpStatus.CREATED);

    }
}