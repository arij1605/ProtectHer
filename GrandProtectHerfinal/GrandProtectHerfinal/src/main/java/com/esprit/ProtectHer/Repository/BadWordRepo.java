package com.esprit.ProtectHer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.esprit.ProtectHer.entity.badword;

public interface BadWordRepo extends JpaRepository<badword,Long> {

}
