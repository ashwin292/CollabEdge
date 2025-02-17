package org.projects.collabedge.postservice.controller;

import lombok.RequiredArgsConstructor;
import org.projects.collabedge.postservice.dto.PostCreateRequestDto;
import org.projects.collabedge.postservice.dto.PostDto;
import org.projects.collabedge.postservice.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/core")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostCreateRequestDto postCreateRequestDto) {
        PostDto postDto = postService.createPost(postCreateRequestDto, 1L);
        return new ResponseEntity<>(postDto, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPosts(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @GetMapping("/users/{userId}/allPosts")
    public ResponseEntity<List<PostDto>> getAllPosts(@PathVariable Long userId) {
        return ResponseEntity.ok(postService.getPostsByUserId(userId));
    }

}
