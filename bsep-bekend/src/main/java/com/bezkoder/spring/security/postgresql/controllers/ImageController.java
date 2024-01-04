package com.bezkoder.spring.security.postgresql.controllers;

import com.bezkoder.spring.security.postgresql.dtos.GuitarDTO;
import com.bezkoder.spring.security.postgresql.dtos.ImageDTO;
import com.bezkoder.spring.security.postgresql.dtos.TopImageDTO;
import com.bezkoder.spring.security.postgresql.models.Guitar;
import com.bezkoder.spring.security.postgresql.models.Image;
import com.bezkoder.spring.security.postgresql.models.TopImage;
import com.bezkoder.spring.security.postgresql.security.services.GuitarService;
import com.bezkoder.spring.security.postgresql.security.services.TopImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("api/horder/images")
public class ImageController {

    @Autowired
    TopImageService topImageService;

    @Autowired
    TopImageService imageService;


    @Autowired
    GuitarService guitarService;

    @GetMapping(value = "/{guitarId}/getTopImage", produces = "application/json")
    public ResponseEntity<TopImageDTO> getGuitarTopImage(@PathVariable Long guitarId) {
        Guitar guitar = guitarService.findOne(guitarId);

        if(guitar == null ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        TopImage topImage = topImageService.findOneByGuitar(guitar);

        if(topImage == null ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        TopImageDTO topImageDTO = new TopImageDTO(topImage);

        return new ResponseEntity<>(topImageDTO, HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = "/{guitarId}/getAllPhotos")
    public ResponseEntity<List<ImageDTO>> getGuitarPhotos(@PathVariable Long guitarId) {
        Guitar guitar = guitarService.findOne(guitarId);
        Set<Image> images = guitar.getImages();
        List<ImageDTO> imageDTOs = new ArrayList<>();
        for (Image e : images) {
            ImageDTO imageDTO = new ImageDTO();
            imageDTO.setGuitar(new GuitarDTO(e.getGuitar()));
            imageDTO.setId(e.getId());
            imageDTO.setUrlPath(e.getUrlPath());

            imageDTOs.add(imageDTO);
        }
        return new ResponseEntity<>(imageDTOs, HttpStatus.OK);
    }
}
