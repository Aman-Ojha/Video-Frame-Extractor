package com.example.VideoAnalyzer.va.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.VideoAnalyzer.va.dao.FileRepository;
import com.example.VideoAnalyzer.va.model.File;

import java.util.Optional;

@RestController
public class FileRetrieveController {
    @Autowired
    FileRepository fileRepository;

    @GetMapping("/getFile/{fileName}")
    public ResponseEntity<byte[]> getFile(@PathVariable String fileName) {
        Optional<File> fileOptional = fileRepository.findByName(fileName);
        if (fileOptional.isPresent()) {
            File file = fileOptional.get();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                    .contentType(MediaType.parseMediaType(file.getType()))
                    .body(file.getData());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
