package com.bk.springsecurity.service;

import com.bk.springsecurity.Dto.UserDTO;
import com.bk.springsecurity.models.User;
import com.bk.springsecurity.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    public User getUserByUsername(String Username){
        return  userRepository.findByUsername(Username);
    }
    public Optional<User> getUserById(String id){    return userRepository.findById(id);}
    public User saveUser(User user){
        return userRepository.save(user);
    }



    public void uploadProfileImage(String userId, MultipartFile profileImage) {
        uploadImage(String.valueOf(userId), profileImage, "profileImage");
    }

    public void uploadBackgroundImage(String userId, MultipartFile backgroundImage) {
        uploadImage(String.valueOf(userId), backgroundImage, "backgroundImage");
    }

    private void uploadImage(String userId, MultipartFile image, String fieldName) {

    }
    public User updateUser(String userId, User updatedUserData) {
        Optional<User> optionalUser = userRepository.findById(String.valueOf(userId));

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            // Update only the fields with new values
            if (updatedUserData.getGender() != null) {
                existingUser.setGender(updatedUserData.getGender());
            }
            if (updatedUserData.getBirthDate() != null) {
                existingUser.setBirthDate(updatedUserData.getBirthDate());
            }
            if (updatedUserData.getBirthYear() != null) {
                existingUser.setBirthYear(updatedUserData.getBirthYear());
            }
            if (updatedUserData.getHometown() != null) {
                existingUser.setHometown(updatedUserData.getHometown());
            }
            if (updatedUserData.getMaritalStatus() != null) {
                existingUser.setMaritalStatus(updatedUserData.getMaritalStatus());
            }
            if (updatedUserData.getAddress() != null) {
                existingUser.setAddress(updatedUserData.getAddress());
            }
            if (updatedUserData.getPhone() != null) {
                existingUser.setPhone(updatedUserData.getPhone());
            }
            if (updatedUserData.getWork() != null) {
                existingUser.setWork(updatedUserData.getWork());
            }
            if (updatedUserData.getWebSite() != null) {
                existingUser.setWebSite(updatedUserData.getWebSite());
            }
            if (updatedUserData.getAboutYou() != null) {
                existingUser.setAboutYou(updatedUserData.getAboutYou());
            }
            if (updatedUserData.getFavourite() != null) {
                existingUser.setFavourite(updatedUserData.getFavourite());
            }
            if (updatedUserData.getUsername() != null) {
                existingUser.setUsername(updatedUserData.getUsername());
            }



            return userRepository.save(existingUser);
        } else {
            // Handle user not found
            return null;
        }
    }

    public String updateProfilePicture(String profile, String userId) {

        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setProfileImage(profile);
            userRepository.save(existingUser); // Save the updated user
            return "Profile picture updated successfully";
        } else {
            return "User not found"; // Handle user not found
        }
    }

    public User updateGender(String userId,  User updateuser) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setGender(updateuser.getGender());  // Mettez simplement la valeur du genre sans l'envelopper dans un objet JSON
            return userRepository.save(user);
        } else {
            return null;
        }
    }
    public User updateBirthDate(String userId,  User updateuser) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setBirthDate(updateuser.getBirthDate());  // Mettez simplement la valeur du genre sans l'envelopper dans un objet JSON
            return userRepository.save(user);
        } else {
            return null;
        }
    }
    public User updateBirthYear(String userId,  User updateuser) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setBirthYear(updateuser.getBirthYear());  // Mettez simplement la valeur du genre sans l'envelopper dans un objet JSON
            return userRepository.save(user);
        } else {
            return null;
        }
    }
    public User updateHometown(String userId,  User updateuser) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setHometown(updateuser.getHometown());  // Mettez simplement la valeur du genre sans l'envelopper dans un objet JSON
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    public User getUserByEmail(String email) {

        User user = userRepository.findByEmail(email);

        return user;
    }





    public String addUser(UserDTO userDTO) {
        // Check if the username or email already exists
        if (userRepository.existsByEmail(userDTO.getEmail()) || userRepository.existsByUsername(userDTO.getUsername())) {
            return "Email or username already taken...";
        }

        User user = new User (
                userDTO.getId(),
                userDTO.getUsername(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                "active",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );

        userRepository.save(user);

        return user.getUsername();
    }

    public Boolean authenticateUser(String email, String password) {
        // Authenticate using UserRepository
        Optional<User> userOptional = userRepository.findByEmailAndPassword(email, password);
        return userOptional.isPresent();
    }




}
