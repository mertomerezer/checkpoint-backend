package com.mertezer.checkpoint.service;

import com.mertezer.checkpoint.dto.RegisterRequest;
import com.mertezer.checkpoint.dto.RegisterResponse;

public interface IUserService {
    RegisterResponse registerUser(RegisterRequest registerRequest);
}
