package com.ra.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserRequest {
    @NotEmpty(message = "Vui long dien vafo cai user Name di")
    private String userName;
    private String fullName;
    @Size(min = 3,message = "Nhaapj cho no nhieu chu vao ko mat tai khoan day")
    private String password;
}
