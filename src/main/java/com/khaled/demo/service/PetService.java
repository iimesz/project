package com.khaled.demo.service;

import com.khaled.demo.model.dto.request.PetDto;
import com.khaled.demo.model.dto.respone.PetInfoDto;

import java.util.List;

public interface PetService {

    PetInfoDto addPet(Long userId, PetDto dto);
    List<PetInfoDto> getPetsByUserId(Long userId);
    PetInfoDto getPetById(Long petId);
    void updatePet(Long petId, PetDto dto);
    void deletePet(Long petId);
}
