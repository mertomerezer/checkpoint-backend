package com.mertezer.checkpoint.service.impl;

import com.mertezer.checkpoint.dto.RegisterRequest;
import com.mertezer.checkpoint.dto.RegisterResponse;
import com.mertezer.checkpoint.entity.User;
import com.mertezer.checkpoint.repository.UserRepository;
import com.mertezer.checkpoint.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }
    @Override
    public RegisterResponse registerUser(RegisterRequest registerRequest){
        if (userRepository.existsByEmail(registerRequest.getEmail())) { //Springin web katmanda ki hazır exception sınıfı
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Email is already exists");
        }

        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Username is already exists");
        }

        //Yeni kullanıcı kayıt etme aşaması
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPasswordHash(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        User dbUser = userRepository.save(user);

        RegisterResponse responseRegister = new RegisterResponse();
        BeanUtils.copyProperties(dbUser,responseRegister);

        return responseRegister;
        
    }
}
