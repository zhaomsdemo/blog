package com.zhaojh.mini.blog.common.listener;

import com.zhaojh.mini.blog.common.mapper.UserMapper;
import com.zhaojh.mini.blog.common.vo.UserVo;
import com.zhaojh.mini.blog.dao.model.AuditLog;
import com.zhaojh.mini.blog.dao.model.AuditLogChanges;
import com.zhaojh.mini.blog.dao.model.User;
import com.zhaojh.mini.blog.dao.repository.AuditLogChangesRepository;
import com.zhaojh.mini.blog.dao.repository.AuditLogRepository;
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
public class UserDataChangeListener extends AbstractMongoEventListener<User> {

    private final AuditLogRepository auditLogRepository;
    private final AuditLogChangesRepository auditLogChangesRepository;
    private final UserMapper userMapper;

    private final Map<String, UserVo> userCache= new HashMap<>();

    @Override
    public void onBeforeSave(BeforeSaveEvent<User> event) {
        User previousUser = event.getSource();
        userCache.put("previousUser", userMapper.toUserVo(previousUser));
        super.onBeforeSave(event);
    }

    @Override
    public void onAfterSave(AfterSaveEvent<User> event) {
        User savedUser = event.getSource();
        UserVo previousUserVo = userCache.get("previousUser");
        log.info("User {} saved with id {}", savedUser, savedUser.getId());

        AuditLog auditLog = AuditLog.builder()
                .modelId(savedUser.getId())
                .modelName("USER")
                .action(isNewCreated(previousUserVo) ? "CREATE" : "UPDATE")
                .build();

        auditLogRepository.save(auditLog);
        if (isNewCreated(previousUserVo)) {
            generateInsertAuditLogChanges(auditLog, savedUser);
        } else {
            generateUpdateAuditLogChanges(auditLog, previousUserVo, savedUser);
        }
        super.onAfterSave(event);
    }

    private void generateUpdateAuditLogChanges(AuditLog auditLog,UserVo previousUserVo, User savedUser) {
        List<AuditLogChanges> auditLogChanges = new ArrayList<>();
        if (!previousUserVo.getUserName().equals(savedUser.getUserName())) {
            auditLogChanges.add(AuditLogChanges.builder()
                    .auditLogId(auditLog.getId())
                    .fieldName("userName")
                    .oldValue(previousUserVo.getUserName())
                    .newValue(savedUser.getUserName())
                    .build());
        }
        if (!previousUserVo.getEmail().equals(savedUser.getEmail())) {
            auditLogChanges.add(AuditLogChanges.builder()
                    .auditLogId(auditLog.getId())
                    .fieldName("email")
                    .oldValue(previousUserVo.getEmail())
                    .newValue(savedUser.getEmail())
                    .build());
        }
        if (!previousUserVo.getPasswordHash().equals(savedUser.getPasswordHash())) {
            auditLogChanges.add(AuditLogChanges.builder()
                    .auditLogId(auditLog.getId())
                    .fieldName("passwordHash")
                    .oldValue(previousUserVo.getPasswordHash())
                    .newValue(savedUser.getPasswordHash())
                    .build());
        }
        if (!previousUserVo.getAvatarUrl().equals(savedUser.getAvatarUrl())) {
            auditLogChanges.add(AuditLogChanges.builder()
                    .auditLogId(auditLog.getId())
                    .fieldName("avatarUrl")
                    .oldValue(previousUserVo.getAvatarUrl())
                    .newValue(savedUser.getAvatarUrl())
                    .build());
        }
        if (!previousUserVo.getBio().equals(savedUser.getBio())) {
            auditLogChanges.add(AuditLogChanges.builder()
                    .auditLogId(auditLog.getId())
                    .fieldName("bio")
                    .oldValue(previousUserVo.getBio())
                    .newValue(savedUser.getBio())
                    .build());
        }
        if (!previousUserVo.getRole().equals(savedUser.getRole())) {
            auditLogChanges.add(AuditLogChanges.builder()
                    .auditLogId(auditLog.getId())
                    .fieldName("role")
                    .oldValue(previousUserVo.getRole().toString())
                    .newValue(savedUser.getRole().toString())
                    .build());
        }
        auditLogChangesRepository.saveAll(auditLogChanges);
    }

    private void generateInsertAuditLogChanges(AuditLog auditLog, User savedUser) {

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
