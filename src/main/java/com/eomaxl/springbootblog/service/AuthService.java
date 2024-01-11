package com.eomaxl.springbootblog.service;

import com.eomaxl.springbootblog.payload.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}
