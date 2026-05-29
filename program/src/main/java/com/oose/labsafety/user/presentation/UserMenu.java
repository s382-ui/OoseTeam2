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
            new UserAccount("20260001", "서가연", "화학과", "관리자", "010-1234-5678", "ga-yeon@example.com", "활성", LocalDateTime.of(2026, 5, 7, 9, 0)),
            new UserAccount("20260002", "김종규", "생명과학과", "연구원", "010-2345-6789", "jong-gyu@example.com", "활성", LocalDateTime.of(2026, 5, 8, 9, 30))
        );
    }
}