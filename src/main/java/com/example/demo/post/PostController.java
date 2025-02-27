package com.example.demo.post;

import com.example.demo.members.Members;
import com.example.demo.members.MembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MembersRepository membersRepository;




    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @PutMapping("/{postId}/users/{userId}")
    public Post addPostToUser(@PathVariable Long postId, @PathVariable Long userId) {


        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));


        Members user = membersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));


        post.setMember(user);


        if (!user.getPosts().contains(post)) {
            user.getPosts().add(post);
        }


        postRepository.save(post);
        membersRepository.save(user);

        return post;
    }


    @GetMapping
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }


    @GetMapping("/{id}")
    public Post getPost(@PathVariable Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }


    @GetMapping("/member/{memberId}")
    public List<Post> getPostsByMember(@PathVariable Long memberId) {
        Members member = membersRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        return postRepository.findAll().stream()
                .filter(post -> post.getMember().equals(member))
                .toList();
    }


    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
        Post existingPost = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        existingPost.setTitle(updatedPost.getTitle());
        existingPost.setContent(updatedPost.getContent());
        return postRepository.save(existingPost);
    }


    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
    }
}

