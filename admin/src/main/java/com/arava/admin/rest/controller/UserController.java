package com.arava.admin.rest.controller;

import com.arava.admin.rest.dto.UserDto;
import com.arava.persistence.entity.User;
import com.arava.persistence.repository.UserRepository;
import com.arava.server.annotation.Admin;
import com.arava.server.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nidhal Dogga
 * Date : 22/01/2020 09:53
 * All rights reserved.
 */

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private Mapper<User, UserDto> userMapper;

  @Admin
  @GetMapping
  public List<UserDto> searchUser() {
    return userRepository.findAll().stream()
            .map(userMapper::deepMap)
            .collect(Collectors.toList());
  }

}
