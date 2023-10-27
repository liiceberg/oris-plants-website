package ru.kpfu.itis.gimaletdinova.model.enam;

public enum CausativeAgent {
    PEST(0), DISEASE(1);
    private final int value;

    CausativeAgent(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CausativeAgent valueOf(int value) throws IllegalEnumValueException {
        switch (value) {
            case 0:
                return CausativeAgent.PEST;
            case 1:
                return CausativeAgent.DISEASE;
            default:
                throw new IllegalEnumValueException();
        }

    }
}
