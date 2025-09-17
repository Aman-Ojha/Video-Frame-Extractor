package com.example.VideoAnalyzer.va.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.VideoAnalyzer.va.model.MetadataDB;

public interface MetadataRepository extends MongoRepository<MetadataDB, String> {

}
