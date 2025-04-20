package com.zhaojh.mini.blog.dao.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;

@Data
@Builder
@Document
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuditLog {

    @MongoId
    String id;
    String modelName;
    String modelId;
    String action;

    @CreatedDate
    Instant createdAt;
}
