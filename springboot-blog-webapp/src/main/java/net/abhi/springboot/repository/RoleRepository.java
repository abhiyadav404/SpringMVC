package net.abhi.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.abhi.springboot.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
   Role findByName(String name);
}
