package com.khaled.demo.repository;

import com.khaled.demo.model.entity.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findByUserId(Long userId);
    Page<Pet> findByUserId(Long userId, Pageable pageable);

}
