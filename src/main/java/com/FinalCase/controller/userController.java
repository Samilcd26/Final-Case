package com.FinalCase.controller;

import com.FinalCase.business.dto.UserDto;
import com.FinalCase.business.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class userController {

    //MySql repository
    @Autowired
    UserServices userServices;

    //HomeURL

    @GetMapping("/index")
    public String index(){

        return "index";
    }


    //LIST
    //http://localhost:8080/api/v1/user
    @GetMapping("/user")
    public List<UserDto> getAllUsers(){
        List<UserDto> allUser = userServices.getAllUsers();
        return allUser;
    }

    //FIND
    //http://localhost:8080/api/v1/user/1
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getEmployeeById(@PathVariable Long id){
        ResponseEntity<UserDto> findUser=userServices.getEmployeeById(id);
        return findUser;
    }

    //SAVE
    //http://localhost:8080/api/v1/user
    @PostMapping("/user")
    public UserDto CreateUser(@RequestBody UserDto userDto){
        UserDto newUser= userServices.CreateUser(userDto);
        return newUser;
    }

    //UPDATE
    //http://localhost:8080/api/v1/user/1
    @PutMapping("/user/{id}")
    public ResponseEntity<UserDto> updateEmployee(@PathVariable Long id,@RequestBody UserDto userDto){
        ResponseEntity<UserDto> updatedUser = userServices.updateEmployee(Long.valueOf(id),userDto);
        return updatedUser;
    }

    //DELETE
    //http://localhost:8080/api/v1/user/1
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable String id){
        Map<String, Boolean> response = new HashMap<>();
        try {
            userServices.deleteEmployee(Long.valueOf(id));
            response.put("deleted",Boolean.TRUE);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            response.put("deleted",Boolean.FALSE);
            return ResponseEntity.ok(response);
        }

    }
}
