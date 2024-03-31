package com.bk.springsecurity.service.impl;

import com.bk.springsecurity.Dto.UserDTO;
import com.bk.springsecurity.entity.User;
import com.bk.springsecurity.repository.UserRepo;
import com.bk.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public UserDTO findById(Integer userId) {
        return userRepo.findById(userId).map(this::convertToDto).orElse(null);
    }

    public UserDTO convertToDto(User user) {
        // Assuming UserDTO has a constructor that accepts a User entity
        // or you can manually set each property
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        // Set other properties from user to userDTO
        return userDTO;
    }

    public List<UserDTO> findAll() {
        List<User> users = userRepo.findAll(); // Correctly calling findAll on the instance
        return users.stream().map(this::convertToDto).collect(Collectors.toList());
    }


}