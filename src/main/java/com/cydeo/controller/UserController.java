package com.cydeo.controller;

import com.cydeo.dto.ResponseWrapper;
import com.cydeo.dto.UserDTO;
import com.cydeo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> getUsers(){
        return ResponseEntity.ok(new ResponseWrapper("Users are successfully retrieved", userService.listAllUsers(), HttpStatus.OK));
    }

    @GetMapping("/{username}")
    public ResponseEntity<ResponseWrapper> getUserByUserName(@PathVariable("username") String userName){
        return ResponseEntity.ok(new ResponseWrapper("User successfully retrieved", userService.findByUserName(userName), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper> createUser(@RequestBody UserDTO user){
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper("User successfully created", HttpStatus.CREATED));
    }

    @PutMapping
    public ResponseEntity<ResponseWrapper> updateUser(@RequestBody UserDTO user){
        userService.update(user);
        return ResponseEntity.ok(new ResponseWrapper("User successfully updated", HttpStatus.OK));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<ResponseWrapper> deleteUser(@PathVariable("username") String userName){
        userService.delete(userName);
        return ResponseEntity.ok(new ResponseWrapper("User successfully deleted", HttpStatus.OK));
    }

}
