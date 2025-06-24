package com.zhaojh.mini.blog.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhaojh.mini.blog.common.pojo.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {

    String id;
    String userName;
    String email;
    String passwordHash;
    String avatarUrl;
    String bio;
    Role role;
}
