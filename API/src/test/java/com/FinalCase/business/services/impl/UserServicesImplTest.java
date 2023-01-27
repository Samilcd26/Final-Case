package com.FinalCase.business.services.impl;

import com.FinalCase.business.dto.UserDto;
import com.FinalCase.data.models.UserModel;
import com.FinalCase.data.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServicesImplTest {

    @InjectMocks
    private UserServicesImpl userServices;

    @Mock
    UserRepository userRepository;
    @Mock
    ModelMapper modelMapper;



    @Test
    void getAllUsers() {
    }

    @Test
    void getEmployeeById() {
    }

    @Test
    void createUser() {
        Date date = new Date();
        UserDto testUser = new UserDto();
        testUser.setIdNumber(12345678910L);
        testUser.setFirstName("TestFirstName");
        testUser.setLastName("TestLastName");
        testUser.setBirthDate(date);
        testUser.setMonthlySalary(8000);
        testUser.setTelNumber(5055869685L);
        testUser.setEmail("test@gmail.com");
        testUser.setGuaranteeAmount(500L);
        testUser.setCreditScore(550l);



        UserModel userModel = Mockito.mock(UserModel.class);

        Mockito.when(userRepository.save(ArgumentMatchers.any(UserModel.class))).thenReturn(userModel);
        UserDto result = userServices.CreateUser(testUser);

        //credit score test
        assertEquals(result.getCreditScore(),testUser.getCreditScore());



    }

    @Test
    void deleteEmployee() {
    }

    @Test
    void updateEmployee() {
    }

    @Test
    void creditCheck() {
    }

    @Test
    void userToDto() {
    }

    @Test
    void dtoToUser() {
    }
}