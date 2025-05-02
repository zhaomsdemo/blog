package com.zhaojh.mini.blog.common.listener;

import com.zhaojh.mini.blog.common.vo.UserVo;
import com.zhaojh.mini.blog.dao.model.AuditLog;
import com.zhaojh.mini.blog.dao.model.AuditLogChanges;
import com.zhaojh.mini.blog.dao.model.BlogUser;
import com.zhaojh.mini.blog.dao.repository.AuditLogChangesRepository;
import com.zhaojh.mini.blog.dao.repository.AuditLogRepository;
import com.zhaojh.mini.blog.dao.repository.BlogUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserDataChangeListener extends AbstractMongoEventListener<BlogUser> {

    private final AuditLogRepository auditLogRepository;
    private final AuditLogChangesRepository auditLogChangesRepository;
    private final BlogUserRepository blogUserRepository;

    private final Map<String, BlogUser> userCache= new HashMap<>();
    private final ThreadLocal<Boolean> isNewCreated = new ThreadLocal<>();

    @Override
    public void onBeforeSave(BeforeSaveEvent<BlogUser> event) {
        BlogUser user = event.getSource();
        if (isNull(user.getId())) {
            isNewCreated.set(true);
        } else {
            isNewCreated.set(false);
            String userId = user.getId();
            BlogUser previousUserFromDb = blogUserRepository.findById(userId).get();
            userCache.put("previousUser", previousUserFromDb);
        }
        super.onBeforeSave(event);
    }

    @Override
    public void onAfterSave(AfterSaveEvent<BlogUser> event) {
        BlogUser savedUser = event.getSource();
        log.info("User {} saved with id {}", savedUser, savedUser.getId());
        if (isNewCreated.get()) {
            AuditLog auditLog = AuditLog.builder()
                    .modelId(savedUser.getId())
                    .modelName("USER")
                    .action("CREATE")
                    .build();
            auditLogRepository.save(auditLog);
            generateInsertAuditLogChanges(auditLog, savedUser);
        } else {
            AuditLog auditLog = AuditLog.builder()
                    .modelId(savedUser.getId())
                    .modelName("USER")
                    .action("UPDATE")
                    .build();
            auditLogRepository.save(auditLog);
            BlogUser previousUserFromDb = userCache.get("previousUser");
            generateUpdateAuditLogChanges(auditLog, previousUserFromDb, savedUser);
        }
        super.onAfterSave(event);
    }

    private void generateUpdateAuditLogChanges(AuditLog auditLog,BlogUser previousUser, BlogUser savedUser) {
        List<AuditLogChanges> auditLogChanges = new ArrayList<>();
        if (!previousUser.getUserName().equals(savedUser.getUserName())) {
            auditLogChanges.add(AuditLogChanges.builder()
                    .auditLogId(auditLog.getId())
                    .fieldName("userName")
                    .oldValue(previousUser.getUserName())
                    .newValue(savedUser.getUserName())
                    .build());
        }
        if (!previousUser.getEmail().equals(savedUser.getEmail())) {
            auditLogChanges.add(AuditLogChanges.builder()
                    .auditLogId(auditLog.getId())
                    .fieldName("email")
                    .oldValue(previousUser.getEmail())
                    .newValue(savedUser.getEmail())
                    .build());
        }
        if (!previousUser.getPasswordHash().equals(savedUser.getPasswordHash())) {
            auditLogChanges.add(AuditLogChanges.builder()
                    .auditLogId(auditLog.getId())
                    .fieldName("passwordHash")
                    .oldValue(previousUser.getPasswordHash())
                    .newValue(savedUser.getPasswordHash())
                    .build());
        }
        if (!previousUser.getAvatarUrl().equals(savedUser.getAvatarUrl())) {
            auditLogChanges.add(AuditLogChanges.builder()
                    .auditLogId(auditLog.getId())
                    .fieldName("avatarUrl")
                    .oldValue(previousUser.getAvatarUrl())
                    .newValue(savedUser.getAvatarUrl())
                    .build());
        }
        if (!previousUser.getBio().equals(savedUser.getBio())) {
            auditLogChanges.add(AuditLogChanges.builder()
                    .auditLogId(auditLog.getId())
                    .fieldName("bio")
                    .oldValue(previousUser.getBio())
                    .newValue(savedUser.getBio())
                    .build());
        }
        if (!previousUser.getRole().equals(savedUser.getRole())) {
            auditLogChanges.add(AuditLogChanges.builder()
                    .auditLogId(auditLog.getId())
                    .fieldName("role")
                    .oldValue(previousUser.getRole().toString())
                    .newValue(savedUser.getRole().toString())
                    .build());
        }
        auditLogChangesRepository.saveAll(auditLogChanges);
    }

    private void generateInsertAuditLogChanges(AuditLog auditLog, BlogUser savedUser) {

        List<AuditLogChanges> auditLogChanges = new ArrayList<>();
        auditLogChanges.add(AuditLogChanges.builder()
                .auditLogId(auditLog.getId())
                .fieldName("userName")
                .newValue(savedUser.getUserName())
                .build());
        auditLogChanges.add(AuditLogChanges.builder()
                .auditLogId(auditLog.getId())
                .fieldName("email")
                .newValue(savedUser.getEmail())
                .build());
        auditLogChanges.add(AuditLogChanges.builder()
                .auditLogId(auditLog.getId())
                .fieldName("passwordHash")
                .newValue(savedUser.getPasswordHash())
                .build());
        auditLogChanges.add(AuditLogChanges.builder()
                .auditLogId(auditLog.getId())
                .fieldName("avatarUrl")
                .newValue(savedUser.getAvatarUrl())
                .build());
        auditLogChanges.add(AuditLogChanges.builder()
                .auditLogId(auditLog.getId())
                .fieldName("bio")
                .newValue(savedUser.getBio())
                .build());
        auditLogChanges.add(AuditLogChanges.builder()
                .auditLogId(auditLog.getId())
                .fieldName("role")
                .newValue(savedUser.getRole().toString())
                .build());

        auditLogChangesRepository.saveAll(auditLogChanges);
    }

    private boolean isNewCreated(UserVo userVo) {
        return isNull(userVo) || isNull(userVo.getId());
    }
}
