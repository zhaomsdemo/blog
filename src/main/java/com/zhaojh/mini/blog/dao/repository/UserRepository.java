package com.zhaojh.mini.blog.dao.repository;

import com.zhaojh.mini.blog.dao.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
