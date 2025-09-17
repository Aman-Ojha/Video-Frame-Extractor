package com.example.VideoAnalyzer.va.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.VideoAnalyzer.va.dao.MetadataRepository;
import com.example.VideoAnalyzer.va.model.MetadataDB;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class MetadataService {

    @Autowired
    private MetadataRepository metadataRepository;

    public List<MetadataDB> getAllMetadatas() {
        return metadataRepository.findAll();
    }

    public MetadataDB getMetadataById(String id) {
        return metadataRepository.findById(id).orElse(null);
    }

    public MetadataDB createMetadata(MetadataDB metadata) {
        return metadataRepository.save(metadata);
    }

    public MetadataDB updateMetadata(String id, MetadataDB metadata) {
        Optional<MetadataDB> existingMetadata = metadataRepository.findById(id);
        if (existingMetadata.isPresent()) {
            MetadataDB updatedMetadata = existingMetadata.get();
            updatedMetadata.setFileName(metadata.getFileName());
            updatedMetadata.setPathString(metadata.getPathString());

            return metadataRepository.save(updatedMetadata);
        } else {
            return null;
        }
    }

    public void deleteMetadata(String id) {
        metadataRepository.deleteById(id);
    }
    

}
