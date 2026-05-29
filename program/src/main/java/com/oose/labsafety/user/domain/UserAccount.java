package com.oose.labsafety.user.domain;

import com.oose.labsafety.common.Displayable;
import com.oose.labsafety.common.Identifiable;

import java.time.LocalDateTime;

public record UserAccount(
        String userId,
        String userName,
        String department,
        String role,
    String contact,
        String email,
    String accountStatus,
    LocalDateTime registeredAt
) implements Identifiable, Displayable {

    @Override
    public String id() {
        return userId;
    }

    @Override
    public String summary() {
        return "사용자ID=%s, 이름=%s, 소속=%s, 권한=%s, 연락처=%s, 상태=%s, 등록일시=%s".formatted(
            userId, userName, department, role, contact, accountStatus, registeredAt);
    }
}