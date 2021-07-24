package com.example.study.repository;

import com.example.study.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findFirstByPhoneNumberOrderByIdDesc(String phoneNumber);

//    // select * from where account = ?
//    Optional<User> findByAccount(String account);
//    // select * from where email = ?
//    Optional<User>  findByEmail(String email);
//    // select * from where account = ? and email = ?
//    Optional<User> findByAccountAndEmail(String account, String email);
}
