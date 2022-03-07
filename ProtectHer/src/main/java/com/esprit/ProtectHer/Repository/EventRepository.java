package com.esprit.ProtectHer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esprit.ProtectHer.entity.Event;


public interface EventRepository extends JpaRepository<Event,Long>{

}
