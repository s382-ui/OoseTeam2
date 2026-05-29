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
            io.println("3. 키워드 검색");
            io.println("4. 상세 조회");
            io.println("5. 신규 등록");
            io.println("6. 식별자 수정");
            io.println("7. 데모 데이터 적재");
            io.println("8. 식별자 삭제");
            io.println("9. 전체 삭제");
            io.println("0. 뒤로가기");

            int choice = io.readInt("선택: ", 0, 9);
            switch (choice) {
                case 1 -> showOverview(io);
                case 2 -> showItems(io);
                case 3 -> searchItems(io);
                case 4 -> showDetail(io);
                case 5 -> register(io);
                case 6 -> updateById(io);
                case 7 -> seedDemoData(io);
                case 8 -> deleteById(io);
                case 9 -> clearAll(io);
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

    private void searchItems(ConsoleIO io) {
        io.println("");
        String keyword = io.readRequiredLine("검색어: ").toLowerCase();
        List<T> matched = service.findAll().stream()
                .filter(item -> item.id().toLowerCase().contains(keyword)
                        || item.summary().toLowerCase().contains(keyword))
                .toList();

        if (matched.isEmpty()) {
            io.println("검색 조건에 맞는 항목이 없습니다.");
        } else {
            for (int index = 0; index < matched.size(); index++) {
                io.println((index + 1) + ". " + matched.get(index).summary());
            }
        }
        io.pause();
    }

    private void showDetail(ConsoleIO io) {
        io.println("");
        String id = io.readRequiredLine("조회할 식별자: ");
        service.findById(id)
                .ifPresentOrElse(
                        item -> io.println(item.summary()),
                        () -> io.println("해당 식별자의 항목을 찾을 수 없습니다.")
                );
        io.pause();
    }

    private void register(ConsoleIO io) {
        T item = createRecord(io);
        if (service.existsById(item.id())) {
            io.println("이미 등록된 식별자입니다. 수정 메뉴를 사용해 주세요.");
            io.pause();
            return;
        }
        service.register(item);
        io.println("등록이 완료되었습니다.");
        io.pause();
    }

    private void updateById(ConsoleIO io) {
        io.println("");
        String id = io.readRequiredLine("수정할 식별자: ");
        service.findById(id)
                .ifPresentOrElse(
                        item -> updateExisting(io, id, item),
                        () -> io.println("해당 식별자의 항목을 찾을 수 없습니다.")
                );
        io.pause();
    }

    private void updateExisting(ConsoleIO io, String id, T currentItem) {
        io.println("현재 값: " + currentItem.summary());
        io.println("수정할 값을 전체 재입력해 주세요. 식별자는 기존 값과 같아야 합니다.");
        T updatedItem = createRecord(io);
        if (!id.equals(updatedItem.id())) {
            io.println("식별자가 기존 값과 달라 수정이 취소되었습니다.");
            return;
        }
        service.update(updatedItem);
        io.println("수정이 완료되었습니다.");
    }

    private void seedDemoData(ConsoleIO io) {
        service.seed(sampleRecords());
        io.println("데모 데이터가 적재되었습니다.");
        io.pause();
    }

    private void deleteById(ConsoleIO io) {
        io.println("");
        String id = io.readRequiredLine("삭제할 식별자: ");
        if (service.deleteById(id)) {
            io.println("삭제가 완료되었습니다.");
        } else {
            io.println("해당 식별자의 항목을 찾을 수 없습니다.");
        }
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
