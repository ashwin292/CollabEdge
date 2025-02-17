package org.projects.collabedge.postservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.projects.collabedge.postservice.dto.PostCreateRequestDto;
import org.projects.collabedge.postservice.dto.PostDto;
import org.projects.collabedge.postservice.entity.Post;
import org.projects.collabedge.postservice.exception.ResourceNotFoundException;
import org.projects.collabedge.postservice.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostDto createPost(PostCreateRequestDto postCreateRequestDto, Long userId) {
        log.info("Creating new post for user {}", userId);
        Post post = modelMapper.map(postCreateRequestDto, Post.class);
        post.setUserId(userId);
        return modelMapper.map(postRepository.save(post), PostDto.class);
    }

    public PostDto getPostById(Long postId) {
        log.info("Retrieving post with id {}", postId);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post with id " + postId + " not found"));
        return modelMapper.map(post, PostDto.class);
    }

    public List<PostDto> getPostsByUserId(Long userId) {
        log.info("Retrieving posts by user {}", userId);
        List<Post> posts = postRepository.getPostsByUserId(userId);
        return posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }
}
