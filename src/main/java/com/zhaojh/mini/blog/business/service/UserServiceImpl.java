package com.zhaojh.mini.blog.business.service;

import com.zhaojh.mini.blog.common.dto.UserDto;
import com.zhaojh.mini.blog.common.exception.DataNotFoundException;
import com.zhaojh.mini.blog.common.mapper.UserMapper;
import com.zhaojh.mini.blog.common.vo.UserVo;
import com.zhaojh.mini.blog.dao.model.BlogUser;
import com.zhaojh.mini.blog.dao.repository.BlogUserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    BlogUserRepository blogUserRepository;
    UserMapper userMapper;

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public UserVo createUser(UserDto userDto) {
        BlogUser user = userMapper.toUser(userDto);
        user = blogUserRepository.save(user);
        return userMapper.toUserVo(user);
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public UserVo updateUser(String id, UserDto userDto) {
        BlogUser user = blogUserRepository.findById(id).orElseThrow(() -> new DataNotFoundException("User not found : " + id));
        userMapper.updateUser(userDto, user);
        blogUserRepository.save(user);
        return userMapper.toUserVo(user);
    }


    @Override
    @Cacheable(value = "users", key = "#name")
    public List<UserVo> findUsersByName(String name) {
        return blogUserRepository.findByUserNameContaining(name).stream()
                .map(userMapper::toUserVo)
                .collect(Collectors.toList());
    }
}
