package com.eomaxl.springbootblog.respository;

import com.eomaxl.springbootblog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
