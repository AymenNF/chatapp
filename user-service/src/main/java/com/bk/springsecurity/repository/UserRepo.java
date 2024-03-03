package com.bk.springsecurity.repository;


import com.bk.springsecurity.entity.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepo extends MongoRepository<User,Integer>
{
    Optional<User> findByEmailAndPassword(String email, String password);

    User findByEmail(String email);
}