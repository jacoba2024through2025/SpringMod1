package com.example.demo.channel;

import com.example.demo.members.Members;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;


    @ManyToMany(mappedBy = "channels")
    private Set<Members> users = new HashSet<>();

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Set<Members> getUsers() {
        return users;
    }
    public void setUsers(Set<Members> users) {
        this.users = users;
    }
}


