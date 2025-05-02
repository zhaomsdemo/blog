package com.zhaojh.mini.blog.common.mapper;

import com.zhaojh.mini.blog.common.dto.UserDto;
import com.zhaojh.mini.blog.common.vo.UserVo;
import com.zhaojh.mini.blog.dao.model.BlogUser;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = org.mapstruct.NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    UserVo toUserVo(BlogUser user);
    BlogUser toUser(UserDto userDto);
    void updateUser(UserDto userDto,@MappingTarget BlogUser user);
}
