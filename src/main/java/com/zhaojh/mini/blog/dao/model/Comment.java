package com.zhaojh.mini.blog.dao.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;

@Data
@Builder
@Document
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment {

    @MongoId
    String id;
    @DBRef
    Post post;
    @DBRef
    BlogUser user;
    @DBRef(lazy = true)
    Comment parentComment;
    String content;

    @CreatedBy
    String createdBy;
    @LastModifiedBy
    String updatedBy;
    @CreatedDate
    Instant createdAt;
    @LastModifiedDate
    Instant updatedAt;
}
