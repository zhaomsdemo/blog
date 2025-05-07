package com.zhaojh.mini.blog.business.service;

import com.zhaojh.mini.blog.common.dto.UserDto;
import com.zhaojh.mini.blog.common.vo.UserVo;

import java.util.List;

public interface UserService {

    UserVo createUser(UserDto userDto);
    UserVo updateUser(String id, UserDto userDto);
    List<UserVo> findUsersByName(String name);
}
