package com.zhaojh.mini.business.service;

import com.zhaojh.mini.common.dto.UserDto;
import com.zhaojh.mini.common.vo.UserVo;

public interface UserService {

    UserVo createUser(UserDto userDto);
}
