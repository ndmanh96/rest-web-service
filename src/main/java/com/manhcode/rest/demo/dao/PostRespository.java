package com.manhcode.rest.demo.dao;

import com.manhcode.rest.demo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRespository extends JpaRepository<Post, Integer> {
}
