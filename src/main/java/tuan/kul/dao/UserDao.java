package tuan.kul.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tuan.kul.model.User;

public interface UserDao extends JpaRepository<User, Long> {
    
}
