package org.projects.collabedge.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.projects.collabedge.userservice.dto.LoginRequestDto;
import org.projects.collabedge.userservice.dto.SignupRequestDto;
import org.projects.collabedge.userservice.dto.UserDto;
import org.projects.collabedge.userservice.repository.UserRepository;
import org.projects.collabedge.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupRequestDto signupRequestDto) {
        return new ResponseEntity<>(userService.createUser(signupRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return new ResponseEntity<UserDto>(userService.loginUser(loginRequestDto), HttpStatus.OK);
    }

}
