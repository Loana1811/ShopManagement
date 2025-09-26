package com.example.shopmanagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

public class DTO {

    @Getter
    @Setter
    public static class AuthRequest {
        private String email;   // thay username bằng email
        private String password;
        private String fullName;
//        private Set<Long> roleIds; // hoặc List<Long>
// thay roleName thành id
private Set<String> roleNames;
    }



    @Getter @Setter
    public static class AuthResponse {
        private String token;
        public AuthResponse(String token) {
            this.token = token;
        }
    }

}
