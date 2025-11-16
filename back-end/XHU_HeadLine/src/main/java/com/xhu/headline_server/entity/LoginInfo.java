package com.xhu.headline_server.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class LoginInfo {
    private long userId;
    @Setter
    private String userName;
    @Setter
    private String password;
    @Setter
    private String token;



    public long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

}
