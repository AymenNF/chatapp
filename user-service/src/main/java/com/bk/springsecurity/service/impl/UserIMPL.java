package com.bk.springsecurity.service.impl;

import com.bk.springsecurity.Dto.UserDTO;
import com.bk.springsecurity.entity.User;
import com.bk.springsecurity.repository.UserRepo;
import com.bk.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserIMPL implements UserService {

    @Autowired
    private UserRepo userRepo;



    @Override
    public String addEmployee(UserDTO userDTO) {

        User user = new User(

                userDTO.getId(),
                userDTO.getUsername(),
                userDTO.getEmail(),
                userDTO.getPassword()

        );

        userRepo.save(user);

        return user.getUsername();
    }


    public Boolean authenticateUser(String email, String password) {
        // Authenticate using UserRepository
        Optional<User> userOptional = userRepo.findByEmailAndPassword(email, password);
        return userOptional.isPresent();
    }


}