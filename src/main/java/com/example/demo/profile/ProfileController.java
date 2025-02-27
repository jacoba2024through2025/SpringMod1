package com.example.demo.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private ProfileRepository profileRepository;


    @PostMapping
    public Profile createProfile(@RequestBody Profile profile) {
        return profileRepository.save(profile);
    }


    @GetMapping("/{userId}")
    public Profile getProfile(@PathVariable Long userId) {
        return profileRepository.findById(userId).orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    @GetMapping
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }


    @PutMapping("/{userId}")
    public Profile updateProfile(@PathVariable Long userId, @RequestBody Profile updatedProfile) {
        Profile existingProfile = profileRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        existingProfile.setBio(updatedProfile.getBio());


        return profileRepository.save(existingProfile);
    }


    @DeleteMapping("/{id}")
    public void deleteProfile(@PathVariable Long id) {
        profileRepository.deleteById(id);
    }
}


