package com.itproger.blog.repos;

import com.itproger.blog.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepo extends CrudRepository<Post, Long> {

}
