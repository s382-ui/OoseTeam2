package com.oose.labsafety.ui;

import com.oose.labsafety.bootstrap.ApplicationContext;
import com.oose.labsafety.common.ConsoleIO;
import com.oose.labsafety.common.FeatureModule;

import java.util.List;

public final class MainMenu {

    private final ApplicationContext context;

    public MainMenu(ApplicationContext context) {
        this.context = context;
    }

    public void run() {
        ConsoleIO io = context.consoleIO();
        List<FeatureModule> modules = context.modules();

        boolean running = true;
        while (running) {
            io.println("");
            io.println("연구실 안전관리 시스템");
            io.println("모듈을 선택하세요.");

            if (modules.isEmpty()) {
                io.println("현재 등록된 모듈이 없습니다.");
                io.println("0. 종료");
                int choice = io.readInt("선택: ", 0, 0);
                if (choice == 0) {
                    running = false;
                }
                continue;
            }

            for (int index = 0; index < modules.size(); index++) {
                FeatureModule module = modules.get(index);
                io.println((index + 1) + ". " + module.title() + " [" + module.code() + "]");
            }
            io.println("0. 종료");

            int choice = io.readInt("선택: ", 0, modules.size());
            if (choice == 0) {
                running = false;
                continue;
            }

            modules.get(choice - 1).open(io);
        }

        io.println("프로그램을 종료합니다.");
    }
}