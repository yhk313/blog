package com.blog.board.service;

import com.blog.board.dto.ResponseDto;
import com.blog.board.dto.SignInDto;
import com.blog.board.dto.SignInResponseDto;
import com.blog.board.dto.SignUpDto;
import com.blog.board.entity.User;
import com.blog.board.repository.UserRepository;
import com.blog.board.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenProvider tokenProvider;
    public ResponseDto<?> signUp(SignUpDto dto) {
        String userEmail = dto.getUserEmail();
        String userPassword = dto.getUserPassword();
        String userPasswordCheck = dto.getUserPasswordCheck();
        // email 중복 확인
        try {
            if(userRepository.existsById(userEmail))
                return ResponseDto.setFailed("존재하는 이메일");
        } catch (Exception error) {
            return ResponseDto.setFailed("db error");
        }

        // 비밀번호 불일치
        if (!userPassword.equals(userPasswordCheck))
            return ResponseDto.setFailed("password does not matched");

        // DB 저장
        User user = new User(dto);
        try {
            userRepository.save(user);
        } catch (Exception error) {
            return ResponseDto.setFailed("db error");
        }

        return ResponseDto.setSuccess("회원가입 성공", null);
    }

    public ResponseDto<SignInResponseDto> signIn(SignInDto dto) {
        String userEmail = dto.getUserEmail();
        String userPassword = dto.getUserPassword();
        try {
            boolean existed = userRepository.existsByUserEmailAndUserPassword(userEmail, userPassword);
            if(!existed) return ResponseDto.setFailed("로그인 정보가 일치하지 않습니다.");
        } catch (Exception error) {
            return  ResponseDto.setFailed("Database Error");
        }

        User user = null;
        try {
            user = userRepository.findById(userEmail).get();
        } catch (Exception error) {
            return  ResponseDto.setFailed("Database Error");
        }

        user.setUserPassword("");

        String token = tokenProvider.create(userEmail);
        int exprTime = 3600000;

        SignInResponseDto signInResponseDto = new SignInResponseDto(token, exprTime, user);
        return ResponseDto.setSuccess("로그인 성공", signInResponseDto);
    }
}
