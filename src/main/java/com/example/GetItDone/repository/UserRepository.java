package com.example.GetItDone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.GetItDone.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User>findByUsername(String username); //사용자 이름으로 조회하기
	Optional<User>findByEmail(String email); //이메일로 조회하기
}
