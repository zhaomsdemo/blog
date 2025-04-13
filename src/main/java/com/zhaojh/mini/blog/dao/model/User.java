package com.zhaojh.mini.blog.dao.model;

import com.zhaojh.mini.blog.common.pojo.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "blog_user")
public class User {

    @MongoId
    String id;
    String userName;
    String email;
    String passwordHash;
    String avatarUrl;
    String bio;
    Role role;

    @CreatedBy
    String createdBy;
    @LastModifiedBy
    String updatedBy;
    @CreatedDate
    Instant createdAt;
    @LastModifiedDate
    Instant updatedAt;
}
