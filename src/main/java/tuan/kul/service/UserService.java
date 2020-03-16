package tuan.kul.service;

import java.util.List;

import tuan.kul.model.User;

public interface UserService {

    User save(User user);
    List<User> findAll();
    void delete(long id);
}
