package tuan.kul.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tuan.kul.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
