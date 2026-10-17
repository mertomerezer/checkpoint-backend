package com.mertezer.checkpoint.controller;

import com.mertezer.checkpoint.dto.RegisterRequest;
import com.mertezer.checkpoint.dto.RegisterResponse;

public interface IUserController {
    RegisterResponse saveUser(RegisterRequest registerRequest);

}
