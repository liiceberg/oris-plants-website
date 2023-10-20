package ru.kpfu.itis.gimaletdinova.model.plant_enam;

public enum Level {
    FIRST(1), SECOND(2), THIRD(3), FOURTH(4), FIFTH(5);
    private final int value;

    Level(int value) {
    this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Level valueOf(int value) {
        switch (value) {
            case 1:
                return Level.FIRST;
            case 2:
                return Level.SECOND;
            case 3:
                return Level.THIRD;
            case 4:
                return Level.FOURTH;
            case 5:
                return Level.FIFTH;
        }
        return null;
    }
}
