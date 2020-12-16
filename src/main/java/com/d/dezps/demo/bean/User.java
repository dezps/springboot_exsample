package com.d.dezps.demo.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {

    private Long id;
    private String username;
    private String name;
    private String password;
    private Boolean isadmin;
}