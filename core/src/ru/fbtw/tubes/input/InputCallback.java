package ru.fbtw.tubes.input;

@FunctionalInterface
public interface InputCallback {
    void handle(int r, int c);
}
