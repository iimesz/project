package com.khaled.demo.security;
import com.khaled.demo.repository.PetRepository;
import com.khaled.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSecurity {

    private final UserRepository userRepository;
    private final PetRepository petRepository;

    public boolean isOwner(Long userId, Authentication authentication) {
        return userRepository.findById(userId)
                .map(user -> user.getEmail().equals(authentication.getName()))
                .orElse(false);
    }

    public boolean isPetOwner(Long petId, Authentication authentication) {
        return petRepository.findById(petId)
                .map(pet -> pet.getUser().getEmail().equals(authentication.getName()))
                .orElse(false);
    }
}
