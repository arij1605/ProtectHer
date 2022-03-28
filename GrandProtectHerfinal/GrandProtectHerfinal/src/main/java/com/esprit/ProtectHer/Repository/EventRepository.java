package com.esprit.ProtectHer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.esprit.ProtectHer.entity.Event;


public interface EventRepository extends JpaRepository<Event,Long> ,PagingAndSortingRepository<Event, Long>, JpaSpecificationExecutor<Event>{

}
