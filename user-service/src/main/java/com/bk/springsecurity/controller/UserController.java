package com.bk.springsecurity.controller;

import com.bk.springsecurity.Dto.LoginDTO;
import com.bk.springsecurity.Dto.UpdateProfileRequest;
import com.bk.springsecurity.Dto.UserDTO;
import com.bk.springsecurity.models.User;
import com.bk.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/e-social")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/users/id/{id}")
    public Optional<User> getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        System.out.println("Received user: " + user.toString());
        User savedUser = userService.saveUser(user);
        System.out.println("Saved user: " + savedUser.toString());
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/users/{userId}/upload-profile-image")
    public ResponseEntity<String> uploadProfileImage(
            @PathVariable String userId,
            @RequestParam("profileImage") MultipartFile profileImage) {

        userService.uploadProfileImage(userId, profileImage);

        System.out.println("Received profile image for user ID: " + userId);

        return ResponseEntity.ok("Profile image uploaded successfully.");
    }

    @PutMapping("/users/{userId}/upload-background-image")
    public ResponseEntity<String> uploadBackgroundImage(
            @PathVariable String userId,
            @RequestParam("backgroundImage") MultipartFile backgroundImage) {

        userService.uploadBackgroundImage(userId, backgroundImage);

        System.out.println("Received background image for user ID: " + userId);

        return ResponseEntity.ok("Background image uploaded successfully.");
    }


    @PutMapping("/users/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable String userId) {
        Optional<User> userOptional = userService.getUserById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Update the user as needed
            //userService.saveUser(user); // Assuming you have a method to save the user

            // Return the profileImage
            return ResponseEntity.ok(user.getProfileImage());
        } else {
            return null;
        }
    }


    @GetMapping("/user/imagePath/{userId}")
    public ResponseEntity<String> getUserImagePath(@PathVariable String userId) {
        // Fetch the user from the database
        User user = userService.getUserById(userId).orElse(null); // Replace "userId" with the actual user ID

        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        // Get the image path from the user object
        String imagePath = user.getProfileImage(); // or user.getBackgroundImage() based on your requirement

        return new ResponseEntity<>(imagePath, HttpStatus.OK);
    }


    @PutMapping("/users/{userId}/update-{attribue}")
    public ResponseEntity<User> updateGender(
            @PathVariable String userId,
            @RequestBody User updateuser) {
        System.out.println(updateuser);

        User updatedUser = userService.updateGender(userId, updateuser);

        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping(path = "/save")
    public String saveEmployee(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO);
        String id = userService.addUser(userDTO);
        return id;
    }

    @PostMapping(path = "/login")
    public Object login(@RequestBody LoginDTO loginDTO) {
        boolean isAuthenticated = userService.authenticateUser(loginDTO.getEmail(), loginDTO.getPassword());
        if (isAuthenticated) {
            System.out.println("Login success");
            // Fetch user details from userService based on loginDTO
            User user = userService.getUserByEmail(loginDTO.getEmail());
            if (user != null) {
                return user;
            } else {
                // In case user not found, you may return an error message or handle it as per your application logic
                return "User details not found";
            }
        } else {
            System.out.println("Login fail");
            return null;
        }
    }

    @CrossOrigin
    @PostMapping(path = "/searchid/{email}")
    public String getUserIdByEmail(@RequestParam String email) {
        User user = userService.getUserByEmail(email);
        if (user != null) {
            System.out.println("user id is : " + user.getId());
            return user.getId();
        }
        return null; // Or handle as necessary
    }

    @PostMapping(path = "/updateimage")
    public ResponseEntity<String> updateProfilePicture(@RequestBody UpdateProfileRequest request) {
        try {
            String result = userService.updateProfilePicture(request.getProfile(), request.getUserId());
            System.out.println(result);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update profile picture: " + e.getMessage());
        }
    }
}
