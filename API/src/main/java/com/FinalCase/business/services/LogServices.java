package com.FinalCase.business.services;

import com.FinalCase.business.dto.UserDto;
import com.FinalCase.data.models.LogModel;
import com.FinalCase.data.models.UserModel;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface LogServices {
    //CRUD
    public List<LogModel> getAllLog();
    public ResponseEntity<LogModel> getLogById(Long id);
    public void CreateUser(LogModel logModel);

}
