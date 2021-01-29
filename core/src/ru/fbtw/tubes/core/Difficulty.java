package ru.fbtw.tubes.core;

public enum Difficulty {
    EASY,
    MEDIUM,
    HARD;

    public int getSize() {
        return 5 + 2 * this.ordinal();
    }


}
