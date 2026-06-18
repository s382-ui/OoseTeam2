package com.oose.labsafety.user.domain;

import com.oose.labsafety.common.domain.Identifiable;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record User(
        @NotBlank(message = "사용자 ID는 필수입니다.") String userId,
        @NotBlank(message = "이름은 필수입니다.") String userName,
        String department,
        @NotBlank(message = "역할은 필수입니다.") String role,
        String contact,
        String email,
        @NotBlank(message = "계정 상태는 필수입니다.") String accountStatus,
        String registeredAt
) implements Identifiable {
    public User {
        registeredAt = registeredAt == null || registeredAt.isBlank()
                ? LocalDate.now().toString()
                : registeredAt;
    }

    @Override
    public String id() {
        return userId;
    }
}
