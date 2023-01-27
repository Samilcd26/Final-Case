package com.FinalCase.business.services;

import com.FinalCase.business.dto.UserDto;
import com.FinalCase.data.models.EmployeeModel;
import com.FinalCase.data.models.UserModel;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface EmployeeServices {
    //CRUD
    public List<EmployeeModel> getAllEmployee();
    public ResponseEntity<EmployeeModel> getEmployeeById(Long id);
    public EmployeeModel CreateUser(EmployeeModel employeeModel);
    public ResponseEntity<EmployeeModel> updateEmployee(Long id, EmployeeModel employeeModel);
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(Long id);


}
