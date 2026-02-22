package com.khaled.demo.controller;

import com.khaled.demo.model.dto.request.PetDto;
import com.khaled.demo.model.dto.respone.PetInfoDto;
import com.khaled.demo.service.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping
    @PreAuthorize("@userSecurity.isOwner(#userId, authentication) or hasRole('ADMIN')")
    public ResponseEntity<?> addPet(
            @PathVariable Long userId,
            @Valid @RequestBody PetDto request) {
        PetInfoDto pet = petService.addPet(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(pet);
    }

    @GetMapping
    @PreAuthorize("@userSecurity.isOwner(#userId, authentication) or hasRole('ADMIN')")
    public ResponseEntity<Page<PetInfoDto>> getPetsByUserId(
            @PathVariable Long userId,
            Pageable pageable) {
        Page<PetInfoDto> pets = petService.getPetsByUserId(userId, pageable);
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/{petId}")
    @PreAuthorize("@userSecurity.isPetOwner(#petId, authentication) or hasRole('ADMIN')")
    public ResponseEntity<?> getPetById(
            @PathVariable Long userId,
            @PathVariable Long petId) {
        PetInfoDto pet = petService.getPetById(petId);
        return ResponseEntity.ok(pet);
    }

    @PutMapping("/{petId}")
    @PreAuthorize("@userSecurity.isPetOwner(#petId, authentication) or hasRole('ADMIN')")
    public ResponseEntity<?> updatePet(
            @PathVariable Long userId,
            @PathVariable Long petId,
            @Valid @RequestBody PetDto request) {
        petService.updatePet(petId, request);
        return ResponseEntity.ok("Pet updated successfully");
    }

    @DeleteMapping("/{petId}")
    @PreAuthorize("@userSecurity.isPetOwner(#petId, authentication) or hasRole('ADMIN')")
    public ResponseEntity<?> deletePet(
            @PathVariable Long userId,
            @PathVariable Long petId) {
        petService.deletePet(petId);
        return ResponseEntity.noContent().build();
    }
}
