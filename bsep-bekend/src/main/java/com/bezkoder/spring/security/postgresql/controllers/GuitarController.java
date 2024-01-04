package com.bezkoder.spring.security.postgresql.controllers;


import com.bezkoder.spring.security.postgresql.dtos.GuitarDTO;
import com.bezkoder.spring.security.postgresql.models.Guitar;
import com.bezkoder.spring.security.postgresql.security.services.GuitarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/horder/guitars/")
public class GuitarController {

    @Autowired
    GuitarService guitarService;

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

                    guitarsDTO.add(guitarDTO);
        }
        return new ResponseEntity<>(guitarsDTO, HttpStatus.OK);
    }

    @GetMapping("/{guitarId}")
    public ResponseEntity<Guitar> getGuitarById(@PathVariable Long guitarId) {
        Guitar guitar = guitarService.findOne(guitarId);
        return ResponseEntity.ok(guitar);
    }


}
