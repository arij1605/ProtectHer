package com.esprit.ProtectHer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esprit.ProtectHer.entity.PostLike;

public interface LikeRepo extends JpaRepository<PostLike, Long> {

}
