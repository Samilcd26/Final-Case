package com.FinalCase.business.services.impl;


import com.FinalCase.business.services.EmployeeServices;
import com.FinalCase.business.services.LogServices;
import com.FinalCase.data.models.EmployeeModel;
import com.FinalCase.data.models.LogModel;
import com.FinalCase.data.repository.EmployeeRepository;
import com.FinalCase.helpers.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServicesImpl implements EmployeeServices {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    LogServices logServices;


    @Override
    public List<EmployeeModel> getAllEmployee() {
        List<EmployeeModel> listAllUser = new ArrayList<>();
        try {

            Iterable<EmployeeModel> teacherList = employeeRepository.findAll();

            return listAllUser;
        }catch (Exception e){
            logServices.CreateUser(new LogModel("LOGINgetAllUsers", LocalDate.now(),false));
            return listAllUser;
        }
    }

    @Override
    public ResponseEntity<EmployeeModel> getEmployeeById(Long id) {

        EmployeeModel user = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee "+id+" numaralı product id bulunamadı !!!!","getEmployeeById"));
        try {

        }catch (Exception e){
            System.out.println("Error var ="+e);
        }

        logServices.CreateUser(new LogModel("getAllUsers", LocalDate.now(),true));
        return ResponseEntity.ok(user);
    }

    @Override
    public EmployeeModel CreateUser(EmployeeModel employeeModel) {
        employeeRepository.save(employeeModel);
        logServices.CreateUser(new LogModel("CreateUser", LocalDate.now(),true));
        return employeeModel;
    }

    @Override
    public ResponseEntity<EmployeeModel> updateEmployee(Long id, EmployeeModel employeeModel) {

        return ResponseEntity.ok(employeeModel);
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(Long id) {
        Map<String,Boolean> response = new HashMap<>();

        try {
            EmployeeModel user = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not exist with id :" + id,"deleteEmployee"));
            user.setId(user.getId());
            employeeRepository.delete(user);

            response.put("Deleted:",Boolean.TRUE);
            logServices.CreateUser(new LogModel("deleteEmployee", LocalDate.now(),true));
            return ResponseEntity.ok(response);
        }catch (Exception e){
            logServices.CreateUser(new LogModel("deleteEmployee", LocalDate.now(),false));
            response.put("Deleted:",Boolean.FALSE);
            return ResponseEntity.ok(response);
        }
    }
}
