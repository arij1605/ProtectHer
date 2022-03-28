package com.esprit.ProtectHer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esprit.ProtectHer.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
