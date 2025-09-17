package com.example.VideoAnalyzer.va.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Meta;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.VideoAnalyzer.va.Service.MetadataService;
import com.example.VideoAnalyzer.va.dao.FileRepository;
import com.example.VideoAnalyzer.va.model.File;
import com.example.VideoAnalyzer.va.model.MetadataDB;

@RestController
public class FileController {
    @Autowired
    FileRepository fileRepository;

    @Autowired
    MetadataService metadataService;

    @PostMapping("/saveFile")
    public String saveFile(@RequestParam("file") MultipartFile file){
        try {
        String fileName = file.getOriginalFilename();
        String contentType = file.getContentType();
        byte[] fileContent = file.getBytes();
        File savefile = new File(fileName, contentType, fileContent);
        fileRepository.save(savefile);
        
        new MetadataDB();
        MetadataDB metadata = MetadataDB.builder()
                .fileName(fileName)
                .pathString("path/to/" + fileName) // Example path, modify as needed
                .build();
        metadataService.createMetadata(metadata);
        return "File saved successfully";
        }
    
        catch(Exception e) {
        return "File not saved";
        }
    }

}
