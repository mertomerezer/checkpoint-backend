package com.mertezer.checkpoint.service.impl;

import com.mertezer.checkpoint.dto.RegisterRequest;
import com.mertezer.checkpoint.dto.RegisterResponse;
import com.mertezer.checkpoint.repository.UserRepository;
import com.mertezer.checkpoint.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public RegisterResponse registerUser(RegisterRequest registerRequest){
        return null;
        
    }
}
