package com.zhaojh.mini.business.service;

import com.zhaojh.mini.common.dto.UserDto;
import com.zhaojh.mini.common.mapper.UserMapper;
import com.zhaojh.mini.common.vo.UserVo;
import com.zhaojh.mini.dao.model.User;
import com.zhaojh.mini.dao.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserMapper userMapper;

    @Override
    public UserVo createUser(UserDto userDto) {
        User user = userMapper.toUser(userDto);
        user = userRepository.save(user);
        return userMapper.toUserVo(user);
    }
}
