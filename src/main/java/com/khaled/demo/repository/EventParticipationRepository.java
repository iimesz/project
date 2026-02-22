package com.khaled.demo.repository;

import com.khaled.demo.model.entity.EventParticipation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventParticipationRepository extends JpaRepository<EventParticipation, Long> {
}
