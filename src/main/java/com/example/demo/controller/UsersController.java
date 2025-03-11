package com.example.demo.controller;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
//import jsu.yym.sbexample2025.chapter03.mapper.UsersMapper;
//import jsu.yym.sbexample2025.chapter03.model.Users;
import com.example.demo.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // 用于定义该类为一个RESTful API控制器
@RequestMapping("/chapter03") // 映射路径，所有路径都将以 "/chapter02" 开头
public class UsersController {
    @Autowired
    private UsersMapper usersMapper;
    //获取所有用户
    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return usersMapper.selectList(null);
    }

    //创建新用户
    @PostMapping("/users")
    public Users createUser(@RequestBody Users user) {
        usersMapper.insert(user);
        return user;
    }
    // 更新指定ID的用户
    @PutMapping("/users/{id}")
    public Integer updateUser(@PathVariable Long id, @RequestBody Users updatedUser) {
        return usersMapper.updateById(updatedUser);
    }

    @GetMapping("/users/{id}")
    public Users getUsers(@PathVariable Long id) {
        return usersMapper.selectById(id) ;
    }


    @DeleteMapping("/users/{id}")
    public Integer deleteUsers(@PathVariable Long id){
        return usersMapper.deleteById(id) ;
    }


    // 禁用指定ID的用户
    @PutMapping("/disable/{id}")
    public String disableUser(@PathVariable Long id) {
        // 创建UpdateWrapper来设置更新条件
        UpdateWrapper<Users> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id); // 条件：ID匹配
        updateWrapper.set("status", "DISABLED"); // 更新status为DISABLED
        // 执行更新操作
        boolean updated = usersMapper.update(null, updateWrapper) > 0;
        // 返回禁用成功的消息
        if (updated) {
            return "用户ID【 " + id + " 】已禁用";
        } else {
            return "禁用用户失败，用户ID【 " + id + " 】不存在或更新失败";
        }
    }
}
