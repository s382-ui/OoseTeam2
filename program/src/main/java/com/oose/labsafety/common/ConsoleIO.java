package com.oose.labsafety.common;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public final class ConsoleIO {

    private final Scanner scanner = new Scanner(System.in);

    public void println(String message) {
        System.out.println(message);
    }

    public void print(String message) {
        System.out.print(message);
    }

    public String readLine(String prompt) {
        print(prompt);
        return scanner.nextLine().trim();
    }

    public String readRequiredLine(String prompt) {
        while (true) {
            String value = readLine(prompt);
            if (!value.isBlank()) {
                return value;
            }
            println("값을 입력해 주세요.");
        }
    }

    public int readInt(String prompt, int minInclusive, int maxInclusive) {
        while (true) {
            String value = readLine(prompt);
            try {
                int number = Integer.parseInt(value);
                if (number >= minInclusive && number <= maxInclusive) {
                    return number;
                }
            } catch (NumberFormatException ignored) {
                // retry
            }
            println(minInclusive + "부터 " + maxInclusive + " 사이의 숫자를 입력해 주세요.");
        }
    }

    public LocalDate readDate(String prompt) {
        while (true) {
            String value = readRequiredLine(prompt + " (yyyy-MM-dd): ");
            try {
                return LocalDate.parse(value);
            } catch (DateTimeParseException ignored) {
                println("날짜 형식이 올바르지 않습니다.");
            }
        }
    }

    public void pause() {
        readLine("계속하려면 Enter를 누르세요...");
    }
}