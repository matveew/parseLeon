package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proxy {
    private String ip;
    private String port;

    private String userLogin;
    private String userPassword;
}