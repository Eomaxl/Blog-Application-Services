package com.eomaxl.springbootblog.service;

import com.eomaxl.springbootblog.payload.LoginDto;
import com.eomaxl.springbootblog.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
