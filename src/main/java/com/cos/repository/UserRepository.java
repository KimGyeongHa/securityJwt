package com.cos.repository;

import com.cos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// @Repository가 없어도 loC가 되는 이유는 JpaRepository를 상속했기 떄문이다.
public interface UserRepository extends JpaRepository<User,Integer> {
    public User findByUserName(String username);
}
