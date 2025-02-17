package org.projects.collabedge.postservice.controller;

import lombok.RequiredArgsConstructor;
import org.projects.collabedge.postservice.dto.PostDto;
import org.projects.collabedge.postservice.service.PostLikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class PostLikeController {

    private final PostLikeService postLikeService;

    @PostMapping("/{postId}")
    public ResponseEntity<Void> postLike(@PathVariable Long postId) {
        System.out.println("Post Id: " + postId);
        postLikeService.likePost(postId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> unlikePost(@PathVariable Long postId) {
        postLikeService.unlikePost(postId);
        return ResponseEntity.noContent().build();
    }
}
