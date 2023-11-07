package com.cos.auth;

import com.cos.model.User;
import com.cos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 시큐리티 설정의 loginProcessUrl("/login")
// login 요청 시 UserDetailService타입으로 loC되어있는 loadUserByUsername 함수실행
@Service
public class principalDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByusername(username);
        if(userEntity != null){
           return new principalDetails(userEntity);
        }
        return null;
    }
}
