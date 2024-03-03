package com.bk.springsecurity.service;


import com.bk.springsecurity.Dto.UserDTO;

public interface UserService {
    String addEmployee(UserDTO userDTO);
    Boolean authenticateUser (String email, String password);

    // LoginMesage loginEmployee(LoginDTO loginDTO);

}