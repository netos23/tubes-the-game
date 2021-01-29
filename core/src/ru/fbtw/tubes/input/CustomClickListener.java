package ru.fbtw.tubes.input;

@FunctionalInterface
public interface CustomClickListener {
    CustomClickListener EMPTY = () -> {};

    void handle();
}
