package com.example.VideoAnalyzer.va.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.VideoAnalyzer.va.Service.FileUploadService;

@RestController
public class FileController {
    @Autowired
    FileUploadService fileService;

    @PostMapping("/saveFile")
    public void saveFile(@RequestParam("file") MultipartFile file){
        fileService.saveFile(file);
    }

}
