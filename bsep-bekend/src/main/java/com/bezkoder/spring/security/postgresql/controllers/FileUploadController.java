package com.bezkoder.spring.security.postgresql.controllers;
import com.bezkoder.spring.security.postgresql.models.FileEntity;
import com.bezkoder.spring.security.postgresql.repository.FileRepository;
import com.bezkoder.spring.security.postgresql.models.FileEntity;
import com.bezkoder.spring.security.postgresql.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/api/file")
public class FileUploadController {

    private final FileRepository fileRepository;


    @Autowired
    public FileUploadController(FileRepository fileRepository) {
        this.fileRepository = fileRepository;

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
}
