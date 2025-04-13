package com.zhaojh.mini.blog.common.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserLoginDto {

    String username;
    String password;
    Boolean isHashedPassword;
}
