package com.bk.springsecurity.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String id;
    @Field("username")
    private String username;
    private String email;
    private String password;
    private String active;
    private String gender;
    private String birthDate;
    private Long birthYear;
    private String hometown;
    private String maritalStatus;
    private String address;
    private String phone;
    private String work;
    private String webSite;
    private String aboutYou;
    private String favourite;
    private String profileImage;
    private String backgroundImage;


}
