package com.blog.board.controller;

import com.blog.board.dto.ResponseDto;
import com.blog.board.dto.SignInDto;
import com.blog.board.dto.SignInResponseDto;
import com.blog.board.dto.SignUpDto;
import com.blog.board.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @PostMapping("/signUp")
    public ResponseDto<?> signUp(@RequestBody SignUpDto requestBody) {
       ResponseDto<?> result =  authService.signUp(requestBody);
        System.out.println("result = " + result);
        return result;
    }

    @PostMapping("/signIn")
    public ResponseDto<SignInResponseDto> sign(@RequestBody SignInDto requestBody) {
        ResponseDto<SignInResponseDto> result = authService.signIn(requestBody);
        return result;
    }
}
