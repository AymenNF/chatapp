package com.bk.springsecurity.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @Id
    private String Id;
    private String username;
    private String email;
    private String password;



}