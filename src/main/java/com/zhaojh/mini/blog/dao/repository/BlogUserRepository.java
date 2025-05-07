package com.zhaojh.mini.blog.dao.repository;

import com.zhaojh.mini.blog.dao.model.BlogUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogUserRepository extends MongoRepository<BlogUser, String> {

    List<BlogUser> findByUserNameContaining(String username);
}
