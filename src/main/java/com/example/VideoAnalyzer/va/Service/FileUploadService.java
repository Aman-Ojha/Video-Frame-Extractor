package com.example.VideoAnalyzer.va.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.VideoAnalyzer.va.dao.FileRepository;
import com.example.VideoAnalyzer.va.model.File;

@Service
@Component
public class FileUploadService {

    @Autowired
    FileRepository fileRepository;

    public String saveFile (MultipartFile file){
        try {
        String fileName = file.getOriginalFilename();
        String contentType = file.getContentType();
        byte[] fileContent = file.getBytes();
        File savefile = new File(fileName, contentType, fileContent);
        fileRepository.save(savefile);
        
        return "File saved successfully";
        }
    
        catch(Exception e) {
        return "File not saved";
        }
    }

}
