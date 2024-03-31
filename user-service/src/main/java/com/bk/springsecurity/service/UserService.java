package com.bk.springsecurity.service;


import com.bk.springsecurity.Dto.UserDTO;
import com.bk.springsecurity.entity.User;

import java.util.List;

public interface UserService {
    String addEmployee(UserDTO userDTO);
    Boolean authenticateUser (String email, String password);
    UserDTO findById(Integer userId);
    UserDTO convertToDto(User user);
    List<UserDTO> findAll();

    // LoginMesage loginEmployee(LoginDTO loginDTO);

}