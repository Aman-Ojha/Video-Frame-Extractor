package com.example.VideoAnalyzer.va.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MetadataInternal {
    @Id
    private String id;
    private String fileName;
    private String pathString;
    private LocalDateTime uploadTime;
    private LocalDateTime lastUpdatedTime;
    private Status status;
}
