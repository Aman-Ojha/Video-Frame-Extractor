package com.example.VideoAnalyzer.va.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "VideoAnalyzerMetadata")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MetadataDB {
    
    @Id
    private String id;
    private String fileName;
    private String pathString;
    private String uploadTime;
    private String lastUpdatedTime;
    private String status;

}
