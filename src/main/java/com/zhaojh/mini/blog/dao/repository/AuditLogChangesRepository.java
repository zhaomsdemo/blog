package com.zhaojh.mini.blog.dao.repository;

import com.zhaojh.mini.blog.dao.model.AuditLogChanges;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLogChangesRepository extends MongoRepository<AuditLogChanges, String> {
}
