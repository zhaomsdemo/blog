package com.zhaojh.mini.blog.common.dto;

import com.zhaojh.mini.blog.common.pojo.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    String userName;
    String email;
    String passwordHash;
    String avatarUrl;
    String bio;
    Role role;
}
