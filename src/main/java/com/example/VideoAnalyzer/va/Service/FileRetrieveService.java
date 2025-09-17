package com.example.VideoAnalyzer.va.Service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.VideoAnalyzer.va.model.File;


import com.example.VideoAnalyzer.va.dao.FileRepository;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;


@Service
@Component
public class FileRetrieveService {

    @Autowired
    FileRepository fileRepository;
    
    public ResponseEntity<byte[]> getFile(String fileName){
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
