package com.FinalCase.data.repository;

import com.FinalCase.data.models.LogModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends MongoRepository<LogModel, Long>{}
