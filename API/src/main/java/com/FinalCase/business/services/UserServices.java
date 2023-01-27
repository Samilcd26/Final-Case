package com.FinalCase.business.services;

import com.FinalCase.business.dto.UserDto;
import com.FinalCase.data.models.QueueModel;
import com.FinalCase.data.models.UserModel;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface UserServices {

    //CRUD
    public List<UserDto> getAllUsers();
    public ResponseEntity<UserDto> getEmployeeById(Long id);
    public UserDto CreateUser(UserDto userDto);
    public ResponseEntity<UserDto> updateEmployee(Long id, UserDto userDto);
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(Long id);

    //Edit financial information
    public UserDto creditCheck(UserDto userDto);


    //Queue
    public QueueModel sendQueue(QueueModel queueModel);
    public ResponseEntity<Map<String, Boolean>> deleteQueue(Long id);
    public List<QueueModel> getAllQueue();
    public List<QueueModel> getQueueById(Long id,LocalDate data);



    //model mapper
    public UserDto UserToDto(UserModel userModel);
    public UserModel DtoToUser(UserDto userDto);
}
