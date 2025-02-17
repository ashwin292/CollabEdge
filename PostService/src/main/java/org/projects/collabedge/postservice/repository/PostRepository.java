package org.projects.collabedge.postservice.repository;

import org.projects.collabedge.postservice.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> getPostsByUserId(Long userId);
}
