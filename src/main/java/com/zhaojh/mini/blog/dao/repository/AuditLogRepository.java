package com.zhaojh.mini.blog.dao.repository;

import com.zhaojh.mini.blog.dao.model.AuditLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditLogRepository extends MongoRepository<AuditLog, String> {

    List<AuditLog> findAllByModelName(String modelName);
}
