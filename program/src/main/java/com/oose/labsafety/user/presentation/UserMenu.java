package com.oose.labsafety.user.presentation;

import com.oose.labsafety.common.AbstractCatalogModule;
import com.oose.labsafety.common.ConsoleIO;
import com.oose.labsafety.user.application.UserService;
import com.oose.labsafety.user.domain.UserAccount;

import java.time.LocalDate;
import java.util.List;

public final class UserMenu extends AbstractCatalogModule<UserAccount> {

    public UserMenu(UserService service) {
        super("USR", "사용자 관리", "사용자 계정과 권한, 소속 정보를 관리한다.", service);
    }

    @Override
    protected UserAccount createRecord(ConsoleIO io) {
        return new UserAccount(
                io.readRequiredLine("사용자 ID: "),
                io.readRequiredLine("이름: "),
                io.readRequiredLine("부서/연구실: "),
                io.readRequiredLine("권한: "),
                io.readRequiredLine("이메일: "),
                io.readRequiredLine("상태: "),
                io.readDate("등록일")
        );
    }

    @Override
    protected List<UserAccount> sampleRecords() {
        return List.of(
                new UserAccount("U-001", "서가연", "안전관리팀", "ADMIN", "ga-yeon@example.com", "ACTIVE", LocalDate.of(2026, 5, 7)),
                new UserAccount("U-002", "김종규", "연구실 운영팀", "MANAGER", "jong-gyu@example.com", "ACTIVE", LocalDate.of(2026, 5, 8))
        );
    }
}