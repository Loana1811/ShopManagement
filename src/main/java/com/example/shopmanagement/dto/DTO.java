package com.example.shopmanagement.dto;

import lombok.Getter;
import lombok.Setter;

public class DTO {

    @Getter
    @Setter
    public static class AuthRequest {
        private String email;   // thay username bằng email
        private String password;
        private String fullName;
        private String roleName;
    }



    @Getter @Setter
    public static class AuthResponse {
        private String token;
        public AuthResponse(String token) {
            this.token = token;
        }
    }

}
