package org.projects.collabedge.userservice.dto;

import lombok.Data;

@Data
public class SignupRequestDto {
    private String name, email, password;
}
