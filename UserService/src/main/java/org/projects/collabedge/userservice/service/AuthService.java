package org.projects.collabedge.userservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.projects.collabedge.userservice.dto.LoginRequestDto;
import org.projects.collabedge.userservice.exception.BadRequestException;
import org.modelmapper.ModelMapper;
import org.projects.collabedge.userservice.dto.SignupRequestDto;
import org.projects.collabedge.userservice.dto.UserDto;
import org.projects.collabedge.userservice.entity.User;
import org.projects.collabedge.userservice.repository.UserRepository;
import org.projects.collabedge.userservice.utils.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserDto createUser(SignupRequestDto signupRequestDto) {
        log.info("Creating new user");
        if (userRepository.existsByEmail(signupRequestDto.getEmail())) {
            throw new BadRequestException("User already exists");
        }
        User user = modelMapper.map(signupRequestDto, User.class);
        user.setPassword(BCrypt.hashPassword(signupRequestDto.getPassword()));
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    public UserDto loginUser(LoginRequestDto loginRequestDto) {
        String email = loginRequestDto.getEmail();
        log.info("Login user with email: " + email);
        if (!userRepository.existsByEmail(email)) {
            throw new BadRequestException("Incorrect email or password");
        }
        User user = userRepository.getUserByEmail(email);
        if(!BCrypt.checkPassword(loginRequestDto.getPassword(), user.getPassword())) {
            throw new BadRequestException("Incorrect email or password");
        }
        return modelMapper.map(user, UserDto.class);
    }
}
