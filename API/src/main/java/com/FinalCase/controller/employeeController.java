package com.FinalCase.controller;

import com.FinalCase.business.dto.UserDto;
import com.FinalCase.business.services.EmployeeServices;
import com.FinalCase.data.models.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class employeeController {

    @Autowired
    EmployeeServices employeeServices;

    //LIST
    //http://localhost:8080/api/v1/user
    @GetMapping("/employee")
    public List<EmployeeModel> getAllUsers(){
        List<EmployeeModel> allUser = employeeServices.getAllEmployee();
        return allUser;
    }

    //FIND
    //http://localhost:8080/api/v1/user/1
    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeModel> getEmployeeById(@PathVariable Long id){
        ResponseEntity<EmployeeModel> findUser=employeeServices.getEmployeeById(id);
        return findUser;
    }

    //SAVE
    //http://localhost:8080/api/v1/user
    @PostMapping("/employee")
    public EmployeeModel CreateUser(@RequestBody EmployeeModel employeeModel){
        EmployeeModel newUser= employeeServices.CreateUser(employeeModel);
        return newUser;
    }

    //UPDATE
    //http://localhost:8080/api/v1/user/1
    @PutMapping("/employee/{id}")
    public ResponseEntity<EmployeeModel> updateEmployee(@PathVariable Long id, @RequestBody EmployeeModel employeeModel){
        ResponseEntity<EmployeeModel> updatedUser = employeeServices.updateEmployee(Long.valueOf(id),employeeModel);
        return updatedUser;
    }

    //DELETE
    //http://localhost:8080/api/v1/user/1
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable String id){
        Map<String, Boolean> response = new HashMap<>();
        try {
            employeeServices.deleteEmployee(Long.valueOf(id));
            response.put("deleted",Boolean.TRUE);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            response.put("deleted",Boolean.FALSE);
            return ResponseEntity.ok(response);
        }

    }
}
