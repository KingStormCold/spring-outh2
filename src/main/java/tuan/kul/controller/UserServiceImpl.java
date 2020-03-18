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
import org.springframework.stereotype.Service;

import tuan.kul.entity.UserEntity;
import tuan.kul.repository.UserRepository;
import tuan.kul.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if (!userEntity.isPresent()) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
		return new org.springframework.security.core.userdetails.User(userEntity.get().getUserName(),
				userEntity.get().getPassword(), getAuthority());
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public List<UserEntity> findAll() {
        List<UserEntity> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void delete(String userName) {
    	userRepository.deleteById(userName);
    }

	@Override
	public UserEntity save(UserEntity user) {
		return userRepository.save(user);
	}
    
//    public static BCryptPasswordEncoder encoder(){
//        return new BCryptPasswordEncoder();
//    }
//    
//    public static void main(String[] args) {
//		System.out.println(encoder().encode("123456"));
//	}
}
