package com.example.VideoAnalyzer.va.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.VideoAnalyzer.va.Service.FileRetrieveService;

@RestController
public class FileRetrieveController {

    @Autowired
    FileRetrieveService fileRetrieveService;

    @GetMapping("/getFile/{fileName}")
    public ResponseEntity<byte[]> getFile(@PathVariable String fileName) {
        return fileRetrieveService.getFile(fileName);
    }
}
