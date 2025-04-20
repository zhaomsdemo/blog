package com.zhaojh.mini.blog.api.controller;

import com.zhaojh.mini.blog.business.service.UserService;
import com.zhaojh.mini.blog.common.dto.UserDto;
import com.zhaojh.mini.blog.common.dto.UserLoginDto;
import com.zhaojh.mini.blog.common.pojo.Result;
import com.zhaojh.mini.blog.common.vo.UserVo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @PostMapping("/user/login")
    public Result<UserVo> login(@RequestBody UserLoginDto userLoginDto) {
        return Result.success(UserVo.builder().build());
    }

    @PostMapping("/user")
    public Result<UserVo> register(@RequestBody UserDto userDto) {
        return Result.success(userService.createUser(userDto));
    }

    @PutMapping("/user/{id}")
    public Result<UserVo> update(@PathVariable String id, @RequestBody UserDto userDto) {
        return Result.success(userService.updateUser(id, userDto));
    }
}
