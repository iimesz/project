package com.khaled.demo.service.impl;

import com.khaled.demo.exception.customExceptions.UserNotFoundException;
import com.khaled.demo.mapper.PetMapper;
import com.khaled.demo.model.dto.request.PetDto;
import com.khaled.demo.model.dto.respone.PetInfoDto;
import com.khaled.demo.model.entity.Pet;
import com.khaled.demo.model.entity.User;
import com.khaled.demo.repository.PetRepository;
import com.khaled.demo.repository.UserRepository;
import com.khaled.demo.service.PetService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final UserRepository userRepository;
    private final PetMapper petMapper;

    @Override
    public PetInfoDto addPet(Long userId, PetDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        Pet pet = petMapper.toEntity(dto);
        pet.setUser(user);
        Pet savedPet = petRepository.save(pet);
        return petMapper.toInfoDto(savedPet);
    }

    @Override
    public List<PetInfoDto> getPetsByUserId(Long userId) {
        return petRepository.findByUserId(userId)
                .stream()
                .map(petMapper::toInfoDto)
                .toList();
    }

    @Override
    public PetInfoDto getPetById(Long petId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new UserNotFoundException("Pet not found with id: " + petId));
        return petMapper.toInfoDto(pet);
    }

    @Override
    public void updatePet(Long petId, PetDto dto) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new UserNotFoundException("Pet not found with id: " + petId));
        petMapper.updatePetFromDto(dto, pet);
        petRepository.save(pet);
    }

    @Override
    public void deletePet(Long petId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new UserNotFoundException("Pet not found with id: " + petId));
        petRepository.delete(pet);
    }
}
