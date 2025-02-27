package com.example.demo.channel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/channels")
public class ChannelController {

    @Autowired
    private ChannelRepository channelRepository;


    @PostMapping
    public Channel createChannel(@RequestBody Channel channel) {
        return channelRepository.save(channel);
    }


    @GetMapping
    public List<Channel> getAllChannels() {
        return channelRepository.findAll();
    }


    @GetMapping("/{id}")
    public Channel getChannel(@PathVariable Long id) {
        return channelRepository.findById(id).orElseThrow(() -> new RuntimeException("Channel not found"));
    }


    @PutMapping("/{id}")
    public Channel updateChannel(@PathVariable Long id, @RequestBody Channel updatedChannel) {
        Channel channel = channelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Channel not found"));

        channel.setName(updatedChannel.getName());
        channel.setDescription(updatedChannel.getDescription());

        return channelRepository.save(channel);
    }


    @DeleteMapping("/{id}")
    public void deleteChannel(@PathVariable Long id) {
        channelRepository.deleteById(id);
    }
}


