package com.oose.labsafety.user.domain;

import com.oose.labsafety.common.domain.Identifiable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record User(
        @NotBlank(message = "사용자 ID는 필수입니다.") String userId,
        @NotBlank(message = "이름은 필수입니다.") String userName,
        @NotBlank(message = "소속은 필수입니다.") String department,
        @NotBlank(message = "역할은 필수입니다.") String role,
        @NotBlank(message = "연락처는 필수입니다.") String contact,
        @Email(message = "이메일 형식이 올바르지 않습니다.") String email,
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
