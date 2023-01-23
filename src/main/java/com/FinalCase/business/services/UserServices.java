package com.FinalCase.business.services;

import com.FinalCase.business.dto.UserDto;
import com.FinalCase.data.models.UserModel;
import org.springframework.http.ResponseEntity;

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



    //model mapper
    public UserDto UserToDto(UserModel userModel);
    public UserModel DtoToUser(UserDto userDto);
}
