package com.example.VideoAnalyzer.va.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.VideoAnalyzer.va.Service.VideoFrameExtractorService;

@RestController
public class VideoFrameExtractorController {

    @Autowired
    private VideoFrameExtractorService videoFrameExtractorService;

    @PostMapping("/extractFrames")
    public ResponseEntity<?> extractFrames(
            @RequestParam("inputPath") String inputPath,
            @RequestParam("outputPath") String outputPath) {
        try {
            videoFrameExtractorService.extractFrames(inputPath, outputPath);
            return ResponseEntity.ok("Frames extracted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error extracting frames: " + e.getMessage());
        }
    }
}
