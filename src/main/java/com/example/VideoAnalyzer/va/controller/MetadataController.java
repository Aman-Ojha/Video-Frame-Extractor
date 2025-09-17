package com.example.VideoAnalyzer.va.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.VideoAnalyzer.va.Service.MetadataService;
import com.example.VideoAnalyzer.va.model.MetadataDB;

@RestController
@RequestMapping("/api/metadata")
public class MetadataController {
    
    @Autowired
    private MetadataService metadataService;

    // Define your endpoints here (e.g., GET, POST, DELETE)

    @GetMapping
    public List<MetadataDB> getAllUsers() {
        return metadataService.getAllMetadatas();
    }

    @GetMapping("/{id}")
    public MetadataDB getUserById(@PathVariable String id) {
        return metadataService.getMetadataById(id);
    }

    @PostMapping
    public MetadataDB createUser(@RequestBody MetadataDB metadata) {
        return metadataService.createMetadata(metadata);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        metadataService.deleteMetadata(id);
    }
}
