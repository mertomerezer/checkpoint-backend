package com.mertezer.checkpoint.controller.impl;

import com.mertezer.checkpoint.controller.IUserController;
import com.mertezer.checkpoint.dto.RegisterRequest;
import com.mertezer.checkpoint.dto.RegisterResponse;
import com.mertezer.checkpoint.service.IUserService;
import com.mertezer.checkpoint.service.impl.UserServiceImpl;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkpoint")
public class UserControllerImpl implements IUserController {
    private final IUserService userService;

    public UserControllerImpl(IUserService userService){
        this.userService=userService;
    }

    @PostMapping("/auth/register")
   public  RegisterResponse saveUser( @RequestBody @Valid RegisterRequest registerRequest){
        return userService.registerUser(registerRequest);
    }
}
