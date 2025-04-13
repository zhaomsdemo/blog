package com.zhaojh.mini.blog.business.service;

import com.zhaojh.mini.blog.common.dto.UserDto;
import com.zhaojh.mini.blog.common.vo.UserVo;

public interface UserService {

    UserVo createUser(UserDto userDto);
}
