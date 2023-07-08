package com.blog.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInDto {
    @NotBlank
    private String userEmail;
    @NotBlank
    private String userPassword;
}
