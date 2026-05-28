package com.oose.labsafety.common;

import java.util.List;

public abstract class AbstractCatalogModule<T extends Identifiable & Displayable> implements FeatureModule {

    private final String code;
    private final String title;
    private final String description;
    private final AbstractCatalogService<T> service;

    protected AbstractCatalogModule(String code, String title, String description, AbstractCatalogService<T> service) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.service = service;
    }

    @Override
    public final String code() {
        return code;
    }

    @Override
    public final String title() {
        return title;
    }

    @Override
    public final String description() {
        return description;
    }

    @Override
    public final void open(ConsoleIO io) {
        boolean running = true;
        while (running) {
            io.println("");
            io.println("[" + title + "]");
            io.println("1. 개요 보기");
            io.println("2. 목록 보기");
            io.println("3. 신규 등록");
            io.println("4. 데모 데이터 적재");
            io.println("5. 전체 삭제");
            io.println("0. 뒤로가기");

            int choice = io.readInt("선택: ", 0, 5);
            switch (choice) {
                case 1 -> showOverview(io);
                case 2 -> showItems(io);
                case 3 -> register(io);
                case 4 -> seedDemoData(io);
                case 5 -> clearAll(io);
                case 0 -> running = false;
                default -> {
                }
            }
        }
    }

    private void showOverview(ConsoleIO io) {
        io.println("");
        io.println(title);
        io.println(description);
        io.println("등록 건수: " + service.size());
        io.pause();
    }

    private void showItems(ConsoleIO io) {
        io.println("");
        List<T> items = service.findAll();
        if (items.isEmpty()) {
            io.println("등록된 항목이 없습니다.");
        } else {
            for (int index = 0; index < items.size(); index++) {
                io.println((index + 1) + ". " + items.get(index).summary());
            }
        }
        io.pause();
    }

    private void register(ConsoleIO io) {
        T item = createRecord(io);
        service.register(item);
        io.println("등록이 완료되었습니다.");
        io.pause();
    }

    private void seedDemoData(ConsoleIO io) {
        service.seed(sampleRecords());
        io.println("데모 데이터가 적재되었습니다.");
        io.pause();
    }

    private void clearAll(ConsoleIO io) {
        service.clear();
        io.println("모든 항목이 삭제되었습니다.");
        io.pause();
    }

    protected abstract T createRecord(ConsoleIO io);

    protected abstract List<T> sampleRecords();
}