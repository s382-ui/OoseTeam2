package com.oose.labsafety.user.domain;

import com.oose.labsafety.common.Displayable;
import com.oose.labsafety.common.Identifiable;

import java.time.LocalDate;

public record UserAccount(
        String userId,
        String userName,
        String department,
        String role,
        String email,
        String status,
        LocalDate registeredDate
) implements Identifiable, Displayable {

    @Override
    public String id() {
        return userId;
    }

    @Override
    public String summary() {
        return "사용자ID=%s, 이름=%s, 부서=%s, 권한=%s, 상태=%s, 등록일=%s".formatted(
                userId, userName, department, role, status, registeredDate);
    }
}