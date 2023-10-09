package com.github.angel.raa.module.persistence.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotBlank
    private String username;
    @NotBlank
    private String name;
    @NotEmpty
    private String password;


}
