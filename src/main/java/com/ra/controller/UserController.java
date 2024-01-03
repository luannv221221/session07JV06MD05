package com.ra.controller;

import com.ra.advice.ApplicationHandler;
import com.ra.exception.CustomException;
import com.ra.model.dto.request.UserRequest;
import com.ra.model.dto.response.UserResponse;
import com.ra.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/users")
    public ResponseEntity<Page<UserResponse>> index(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int limit){
        Pageable pageable = PageRequest.of(page,limit);
        Page<UserResponse> responses = userService.findAll(pageable);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<?> save(@RequestBody @Valid UserRequest userRequest) throws CustomException {

       UserResponse response = userService.saveOrUpdate(userRequest);
       return new ResponseEntity<>(response,HttpStatus.OK);


    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) throws CustomException {
        UserResponse userResponse = userService.findById(id);
        return new ResponseEntity<>(userResponse,HttpStatus.OK);
    }
}
