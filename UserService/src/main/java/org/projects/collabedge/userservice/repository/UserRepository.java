package org.projects.collabedge.userservice.repository;

import org.projects.collabedge.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    User getUserByEmail(String email);
}
