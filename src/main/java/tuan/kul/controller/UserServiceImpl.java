package tuan.kul.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tuan.kul.dao.UserDao;
import tuan.kul.model.User;
import tuan.kul.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserDao userDao;

    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
//        Optional<User> user = userDao.findById(Long.valueOf(123));
//        if (user.isPresent()) {
//            throw new UsernameNotFoundException("Invalid username or password.");
//        }
        return new org.springframework.security.core.userdetails.User("tuankul", "123456",
                getAuthority());
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void delete(long id) {
        userDao.deleteById(id);
    }

    @Override
    public User save(User user) {
        return userDao.save(user);
    }
    
//    public static BCryptPasswordEncoder encoder(){
//        return new BCryptPasswordEncoder();
//    }
//    
//    public static void main(String[] args) {
//		System.out.println(encoder().encode("123456"));
//	}
}
