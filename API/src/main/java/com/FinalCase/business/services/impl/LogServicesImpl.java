package com.FinalCase.business.services.impl;

import com.FinalCase.business.services.LogServices;
import com.FinalCase.data.models.LogModel;
import com.FinalCase.data.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServicesImpl implements LogServices {
    @Autowired
    LogRepository logRepository;


    @Override
    public List<LogModel> getAllLog() {
        return null;
    }

    @Override
    public ResponseEntity<LogModel> getLogById(Long id) {
        return null;
    }

    @Override
    public void CreateUser(LogModel logModel) {
        logRepository.save(logModel);
    }
}
