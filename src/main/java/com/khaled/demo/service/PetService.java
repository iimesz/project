package com.khaled.demo.service;

import com.khaled.demo.model.dto.request.PetDto;
import com.khaled.demo.model.dto.respone.PetInfoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PetService {

    PetInfoDto addPet(Long userId, PetDto dto);
    List<PetInfoDto> getPetsByUserId(Long userId);
    Page<PetInfoDto> getPetsByUserId(Long userId, Pageable pageable);
    PetInfoDto getPetById(Long petId);
    void updatePet(Long petId, PetDto dto);
    void deletePet(Long petId);
}
