package com.example.demo.controller;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
//import jsu.yym.sbexample2025.chapter03.model.Users;
import com.example.demo.pojo.Users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersMapper extends BaseMapper<Users> {
}