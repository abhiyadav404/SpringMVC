package net.abhi.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.abhi.springboot.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
     User findByEmail(String email);
}
