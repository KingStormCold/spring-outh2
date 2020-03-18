package tuan.kul.service;

import java.util.List;

import tuan.kul.entity.UserEntity;

public interface UserService {

    UserEntity save(UserEntity user);
    List<UserEntity> findAll();
    void delete(String userName);
}
