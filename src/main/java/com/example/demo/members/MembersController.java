package com.example.demo.members;

import com.example.demo.channel.*;
import com.example.demo.profile.Profile;
import com.example.demo.profile.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class MembersController {

    @Autowired
    private MembersRepository userRepository;
    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @PostMapping
    public Members createUser(@RequestBody Members user) {
        return userRepository.save(user);
    }


    @GetMapping
    public List<Members> getAllUsers() {
        return userRepository.findAll();
    }


    @GetMapping("/{id}")
    public Members getUser(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }


    //@PutMapping("/{userId}/channels/{channelId}")
    //public Members addUserToChannel(@PathVariable Long userId, @PathVariable Long channelId) {
        //Members user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        //Channel channel = channelRepository.findById(channelId).orElseThrow(() -> new RuntimeException("Channel not found"));
        //user.getChannels().add(channel);
        //return userRepository.save(user);
    //}

    @PutMapping("/{channelId}/users/{userId}")
    public Channel addUserToChannel(
            @PathVariable Long channelId,
            @PathVariable Long userId) {


        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new RuntimeException("Channel not found"));

        Members user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));


        if (!channel.getUsers().contains(user)) {
            channel.getUsers().add(user);
        }


        if (!user.getChannels().contains(channel)) {
            user.getChannels().add(channel);
        }


        channelRepository.save(channel);
        userRepository.save(user);


        return channel;
    }

    @PutMapping("/profiles/{profileId}/users/{userId}")
    public Profile addUserToProfile(
            @PathVariable Long profileId,
            @PathVariable Long userId) {


        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        Members user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));


        profile.setUser(user);
        user.setProfile(profile);


        profileRepository.save(profile);
        userRepository.save(user);

        return profile;
    }


    @PutMapping("/{id}")
    public Members updateUser(@PathVariable Long id, @RequestBody Members updatedUser) {
        Members existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setBio(updatedUser.getBio());
        existingUser.setChannels(updatedUser.getChannels());
        existingUser.setProfile(updatedUser.getProfile());

        return userRepository.save(existingUser);
    }


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}


