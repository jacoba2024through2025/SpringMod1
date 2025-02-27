package com.example.demo.post;

import com.example.demo.members.Members;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Members member;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Members getMember() {
        return member;
    }
    public void setMember(Members member) {
        this.member = member;
    }
}

