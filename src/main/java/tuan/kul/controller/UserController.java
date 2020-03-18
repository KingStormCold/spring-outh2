package tuan.kul.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tuan.kul.entity.UserEntity;
import tuan.kul.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("#oauth2.hasScope('read')")
    @GetMapping(value = "/users/user")
    public List listUser(){
        return userService.findAll();
    }

    @PreAuthorize("#oauth2.hasScope('read')")
    @PostMapping(value = "/users/user")
    public UserEntity create(@RequestBody UserEntity user){
        return userService.save(user);
    }

    @PreAuthorize("#oauth2.hasScope('read')")
    @DeleteMapping(value = "/users/user")
    public String delete(@PathVariable(value = "id") String id){
        userService.delete(id);
        return "success";
    }

}