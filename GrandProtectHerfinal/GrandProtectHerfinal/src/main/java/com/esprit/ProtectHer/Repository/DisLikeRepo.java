package com.esprit.ProtectHer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esprit.ProtectHer.entity.PostDislike;

public interface DisLikeRepo extends JpaRepository<PostDislike,Long> {

}
