package com.zhaojh.mini.blog.dao.model;

import com.zhaojh.mini.blog.common.pojo.NotificationType;
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
public class Notification {

    @MongoId
    String id;
    @DBRef
    User user;
    NotificationType type;
    String sourceId;
    boolean isRead;

    @CreatedBy
    String createdBy;
    @LastModifiedBy
    String updatedBy;
    @CreatedDate
    Instant createdAt;
    @LastModifiedDate
    Instant updatedAt;
}
