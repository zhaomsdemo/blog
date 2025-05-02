package com.zhaojh.mini.blog.dao.repository;

import com.zhaojh.mini.blog.dao.model.BlogUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogUserRepository extends MongoRepository<BlogUser, String> {

    Optional<BlogUser> findByUserName(String username);
}
