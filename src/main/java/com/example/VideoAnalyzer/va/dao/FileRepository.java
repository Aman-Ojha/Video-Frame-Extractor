package com.example.VideoAnalyzer.va.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.VideoAnalyzer.va.model.File;
import java.lang.String;

@Repository
public interface FileRepository extends CrudRepository<File, String>{
	java.util.Optional<File> findByName(String name);
}
