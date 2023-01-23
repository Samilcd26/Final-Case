package com.FinalCase.business.services.impl;

import com.FinalCase.business.dto.UserDto;
import com.FinalCase.business.services.UserServices;
import com.FinalCase.data.models.UserModel;
import com.FinalCase.data.repository.UserRepository;
import jakarta.websocket.Endpoint;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;



    //Credit limit multiplier
    private int CLMultiplier=4;


    //GET_ALL_USER
    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> listAllUser = new ArrayList<>();
        Iterable<UserModel> teacherList = userRepository.findAll();
        for(UserModel user:teacherList){
            UserDto userOne=UserToDto(user);
            listAllUser.add(userOne);
        }

        return listAllUser;
    }


    //GET_BY_ID
    @Override
    public ResponseEntity<UserDto> getEmployeeById(Long id) {
        UserModel user = userRepository.findById(id).orElseThrow(()->new RuntimeException("Employee "+id+" numaralı product id bulunamadı !!!!"));
        UserDto userDto = UserToDto(user);
        return ResponseEntity.ok(userDto);
    }



    //CREATE
    @Override
    public UserDto CreateUser(@RequestBody UserDto userDto) {
        UserModel newUser = DtoToUser(creditCheck(userDto));
        userRepository.save(newUser);
        return userDto;
    }



    //DELETE
    @Override
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(Long id) {
        UserModel user = userRepository.findById(id).orElseThrow(()->new RuntimeException("Employee not exist with id :" + id));
        userRepository.delete(user);
        Map<String,Boolean> response = new HashMap<>();
        response.put("Deleted:",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


    //SEND MESSAGE TO PHONE

    public void sendMessage(UserDto userDto){
        System.out.println(userDto.getTelNumber());
    }


    //UPDATE
    @Override
    public ResponseEntity<UserDto> updateEmployee(Long id,@RequestBody UserDto userDetails) {
        //convert incoming data to dto because this data is type userDto
        UserModel userEntity = DtoToUser(userDetails);


        UserModel user = userRepository.findById(id).orElseThrow(()->new RuntimeException("Employee not exist with id :" + id));

        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setMonthlySalary(userEntity.getMonthlySalary());
        user.setTelNumber(userEntity.getTelNumber());
        user.setBirthDate(userEntity.getBirthDate());

        UserDto checkData = UserToDto(userEntity);
        UserModel reverseData = DtoToUser(checkData);
        userRepository.save(reverseData);

        return ResponseEntity.ok(checkData);
    }



    //Method of organizing credit information
    @Override
    public UserDto creditCheck(UserDto newUser) {
        if (newUser.getCreditScore()<500) newUser.setCreditStatus(false);

        if (newUser.getCreditScore()>500 && newUser.getCreditScore()<1000){
            newUser.setCreditStatus(true);
            if (newUser.getMonthlySalary()<5000){
                if (newUser.getGuaranteeAmount()!=0){
                    newUser.setCreditLimit((long) (newUser.getGuaranteeAmount()*0.10+10000L));
                }else newUser.setCreditLimit(10000L);

            } else if (newUser.getMonthlySalary()<10000 && newUser.getMonthlySalary()>5000) {

                if (newUser.getGuaranteeAmount()!=0){
                    newUser.setCreditLimit((long) (newUser.getGuaranteeAmount()*0.20+20000L));
                }else newUser.setCreditLimit(20000L);


            } else if (newUser.getMonthlySalary()>10000){
                if (newUser.getGuaranteeAmount()!=0){
                    newUser.setCreditLimit((long) (((newUser.getMonthlySalary()*CLMultiplier)/2)*0.25+(newUser.getMonthlySalary()*CLMultiplier)/2));
                }else newUser.setCreditLimit((newUser.getMonthlySalary()*CLMultiplier)/2);
            }

        } else if (newUser.getCreditScore()>=1000) {
            if (newUser.getGuaranteeAmount()!=0) newUser.setCreditLimit((long) ((newUser.getMonthlySalary()*CLMultiplier)*0.5+(newUser.getMonthlySalary()*CLMultiplier)));
        }

        return newUser;
    }


    ////////////////////////////////////
    //USER==>DTO
    @Override
    public UserDto UserToDto(UserModel userModel) {
        UserDto userDto = modelMapper.map(userModel,UserDto.class);
        return  userDto;
    }

    //DTO==>USER
    @Override
    public UserModel DtoToUser(UserDto userDto) {
        UserModel userModel = modelMapper.map(userDto,UserModel.class);
        return userModel;
    }


}
