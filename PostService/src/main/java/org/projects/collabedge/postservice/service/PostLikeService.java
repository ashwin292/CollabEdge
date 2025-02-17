package org.projects.collabedge.postservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.projects.collabedge.postservice.entity.Post;
import org.projects.collabedge.postservice.entity.PostLike;
import org.projects.collabedge.postservice.exception.ResourceNotFoundException;
import org.projects.collabedge.postservice.exception.BadRequestException;
import org.projects.collabedge.postservice.repository.PostLikeRepository;
import org.projects.collabedge.postservice.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public void likePost(Long postId) {
        Long userId = 1L;
        log.info("Like post {}", postId);
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post not found with id " + postId));
        boolean hasAlreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);
        if (hasAlreadyLiked) {
            throw new BadRequestException("Post already liked with id " + postId);
        }
        else {
            PostLike postLike = new PostLike();
            postLike.setPostId(postId);
            postLike.setUserId(1L);
            postLikeRepository.save(postLike);
            // TODO: send notifications to user
        }
    }

    @Transactional
    public void unlikePost(Long postId) {
        Long userId = 1L;
        log.info("Unlike post {}", postId);
        postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post not found with id " + postId));
        if (!postLikeRepository.existsByUserIdAndPostId(userId, postId)) {
            throw new BadRequestException("Post not liked with id " + postId);
        }
        postLikeRepository.deleteByUserIdAndPostId(userId, postId);
    }
}
