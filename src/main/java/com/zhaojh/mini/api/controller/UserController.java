package com.zhaojh.mini.api.controller;

import com.zhaojh.mini.business.service.UserService;
import com.zhaojh.mini.common.dto.UserDto;
import com.zhaojh.mini.common.dto.UserLoginDto;
import com.zhaojh.mini.common.pojo.Result;
import com.zhaojh.mini.common.vo.UserVo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
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
}
