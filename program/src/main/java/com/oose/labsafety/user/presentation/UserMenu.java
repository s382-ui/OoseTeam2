package com.oose.labsafety.user.presentation;

import com.oose.labsafety.common.AbstractCatalogModule;
import com.oose.labsafety.common.ConsoleIO;
import com.oose.labsafety.user.application.UserService;
import com.oose.labsafety.user.domain.UserAccount;

import java.time.LocalDateTime;
import java.util.List;

public final class UserMenu extends AbstractCatalogModule<UserAccount> {

    public UserMenu(UserService service) {
        super("USR", "사용자 관리", "사용자 정보 등록/조회와 계정 상태를 관리한다.", service);
    }

    @Override
    protected UserAccount createRecord(ConsoleIO io) {
        return new UserAccount(
                io.readRequiredLine("사용자 ID: "),
                io.readRequiredLine("이름: "),
                io.readRequiredLine("소속(학과/연구실): "),
                io.readRequiredLine("권한: "),
                io.readRequiredLine("연락처: "),
                io.readRequiredLine("이메일: "),
                io.readRequiredLine("계정 상태: "),
                io.readDateTime("등록 일시")
        );
    }

    @Override
    protected List<UserAccount> sampleRecords() {
        return List.of(
            new UserAccount("admin001", "관리자", "안전관리팀", "ADMIN", "010-0000-0000", "admin@lab.com", "ACTIVE", LocalDateTime.of(2026, 5, 7, 9, 0)),
            new UserAccount("user001", "김연구", "화학공학과", "USER", "010-1111-2222", "kim@lab.com", "ACTIVE", LocalDateTime.of(2026, 5, 8, 9, 30))
        );
    }
}
