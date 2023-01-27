package com.FinalCase.business.services.impl;

import com.FinalCase.business.dto.UserDto;
import com.FinalCase.business.services.LogServices;
import com.FinalCase.business.services.UserServices;
import com.FinalCase.data.models.LogModel;
import com.FinalCase.data.models.MessageModel;
import com.FinalCase.data.models.QueueModel;
import com.FinalCase.data.models.UserModel;
import com.FinalCase.data.repository.QueueRepository;
import com.FinalCase.data.repository.UserRepository;
import com.FinalCase.helpers.exception.ResourceNotFoundException;
import com.FinalCase.helpers.messager.MessageProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.*;


@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ObjectMapper objectMapper;

    //RabbitMQ
    @Autowired
    MessageProducer messageProducer;

    @Autowired
    QueueRepository queueRepository;

    //LogProcess
    @Autowired
    LogServices logServices;

    //Date formatter

    //Credit limit multiplier
    private int CLMultiplier=4;


    //GET_ALL_USER
    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> listAllUser = new ArrayList<>();
        try {

            Iterable<UserModel> teacherList = userRepository.findAll();
            for(UserModel user:teacherList){
                UserDto userOne=UserToDto(user);
                listAllUser.add(userOne);
            }
            return listAllUser;
        }catch (Exception e){
            logServices.CreateUser(new LogModel("getAllUsers", LocalDate.now(),false));
            return listAllUser;
        }
    }


    //GET_BY_ID
    @Override
    public ResponseEntity<UserDto> getEmployeeById(Long id) {
        UserModel user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee "+id+" numaralı product id bulunamadı !!!!","getEmployeeById"));
        UserDto userDto = UserToDto(user);

        logServices.CreateUser(new LogModel("getAllUsers", LocalDate.now(),true));
        return ResponseEntity.ok(userDto);
    }



    //CREATE
    @Override
    public UserDto CreateUser(@RequestBody UserDto userDto) {
        MessageModel statusMessage= new MessageModel();



        UserModel newUser = DtoToUser(creditCheck(userDto));

        statusMessage.setFirstName(newUser.getFirstName());
        statusMessage.setLastName(newUser.getLastName());
        statusMessage.setIdNumber(newUser.getIdNumber());
        statusMessage.setEmail(newUser.getEmail());
        statusMessage.setCreditLimit(newUser.getCreditLimit());
        statusMessage.setCreditStatus(newUser.isCreditStatus());

        try {
            String statusMessageString = objectMapper.writeValueAsString(statusMessage);
            messageProducer.sendToQueue(statusMessageString);

        }catch (Exception e){
            logServices.CreateUser(new LogModel("CreateUser", LocalDate.now(),false));
            System.out.println("Error:"+e);
        }
        userRepository.save(newUser);
        logServices.CreateUser(new LogModel("CreateUser", LocalDate.now(),true));
        return userDto;
    }


    //DELETE
    @Override
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(Long id) {
        Map<String,Boolean> response = new HashMap<>();

        try {
            UserModel user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not exist with id :" + id,"deleteEmployee"));
            user.setIdNumber(user.getIdNumber());
            userRepository.delete(user);

            response.put("Deleted:",Boolean.TRUE);
            logServices.CreateUser(new LogModel("deleteEmployee", LocalDate.now(),true));
            return ResponseEntity.ok(response);
        }catch (Exception e){
            logServices.CreateUser(new LogModel("deleteEmployee", LocalDate.now(),false));
            response.put("Deleted:",Boolean.FALSE);
            return ResponseEntity.ok(response);
        }

    }


    //SEND MESSAGE TO PHONE




    //UPDATE
    @Override
    public ResponseEntity<UserDto> updateEmployee(Long id,@RequestBody UserDto userDetails) {

        //convert incoming data to dto because this data is type userDto
        UserModel userEntity = DtoToUser(userDetails);


        UserModel user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not exist with id :" + id,"updateEmployee"));

        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setMonthlySalary(userEntity.getMonthlySalary());
        user.setTelNumber(userEntity.getTelNumber());
        user.setBirthDate(userEntity.getBirthDate());

        UserDto convertData = UserToDto(userEntity);
        UserDto checkData=creditCheck(convertData);
        UserModel reverseData = DtoToUser(checkData);
        try {
            userRepository.save(reverseData);
            logServices.CreateUser(new LogModel("deleteEmployee", LocalDate.now(),true));
            userRepository.save(reverseData);
        }catch (Exception e){
            logServices.CreateUser(new LogModel("deleteEmployee", LocalDate.now(),false));
        }
        return ResponseEntity.ok(convertData);
    }


    /////////////////////////////////////////////////////////////////
    ////Check////////////////////////////////////////////////////////
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
                    newUser.setCreditLimit((long) ((newUser.getGuaranteeAmount())*0.25+(newUser.getMonthlySalary()*CLMultiplier)/2));
                }else newUser.setCreditLimit((newUser.getMonthlySalary()*CLMultiplier)/2);
            }

        } else if (newUser.getCreditScore()>=1000) {
            newUser.setCreditStatus(true);
            if (newUser.getGuaranteeAmount()!=0) newUser.setCreditLimit((long) ((newUser.getMonthlySalary()*CLMultiplier)+(newUser.getGuaranteeAmount()*0.50)));
        }

        return newUser;
    }

    /////////////////////////////////////////////////////////////////
    ////Queue////////////////////////////////////////////////////////
    @Override
    public QueueModel sendQueue(QueueModel queueModel) {
        UserModel user = userRepository.findById(queueModel.getIdNumber()).orElseThrow(()->new ResourceNotFoundException("Employee "+queueModel.getIdNumber()+" numaralı product id bulunamadı !!!!","getEmployeeById"));
        try {
            String message = objectMapper.writeValueAsString(user);
            messageProducer.sendToQueue(message);
        }catch (Exception e){
            System.out.println(e);
        }
        queueRepository.save(queueModel);
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> deleteQueue(Long id) {
        Map<String,Boolean> response = new HashMap<>();

        try {
            QueueModel user = queueRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not exist with id :" + id,"deleteEmployee"));
            user.setIdNumber(user.getIdNumber());
            queueRepository.delete(user);

            response.put("Deleted:",Boolean.TRUE);
            logServices.CreateUser(new LogModel("deleteEmployee", LocalDate.now(),true));
            return ResponseEntity.ok(response);
        }catch (Exception e){
            logServices.CreateUser(new LogModel("deleteEmployee", LocalDate.now(),false));
            response.put("Deleted:",Boolean.FALSE);
            return ResponseEntity.ok(response);
        }
    }

    @Override
    public List<QueueModel> getAllQueue() {
        List<QueueModel> listAllQueue = new ArrayList<>();

        try {
            List<QueueModel> teacherList = queueRepository.findAll();

            return teacherList;
        }catch (Exception e){
            logServices.CreateUser(new LogModel("getAllUsers", LocalDate.now(),false));
            return listAllQueue;
        }
    }

    @Override
    public List<QueueModel> getQueueById(Long id,LocalDate data) {

        List<QueueModel> user = queueRepository.findByIdNumberAndBirthDate(id,data);
        System.out.println(id+" "+data);
        return user;
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
