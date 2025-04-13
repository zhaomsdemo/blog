package com.zhaojh.mini.common.mapper;

import com.zhaojh.mini.common.dto.UserDto;
import com.zhaojh.mini.common.vo.UserVo;
import com.zhaojh.mini.dao.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserVo toUserVo(User user);
    User toUser(UserDto userDto);
}
