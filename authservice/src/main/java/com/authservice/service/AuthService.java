package com.authservice.service;

import com.authservice.dto.APIResponse;
import com.authservice.dto.RegistrationRequestDto;

public interface AuthService {

    public APIResponse<?> register(RegistrationRequestDto dto);
    public APIResponse<String> login(String username, String password);
}