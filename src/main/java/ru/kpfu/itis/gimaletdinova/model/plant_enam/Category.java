package ru.kpfu.itis.gimaletdinova.model.plant_enam;

public enum Category {
    DECORATIVE_BLOOMING("Decorative-Blooming"),
    DECORATIVE_DECIDUOUS("Decorative-Deciduous"),
    SUCCULENT("Succulent"),
    PALM_TREE("Palm Tree"),
    FERN("Fern"),
    ORCHID("Orchid"),
    BROMELIAD("Bromeliad"),
    CONIFEROUS("Coniferous"),
    FRUITING("Fruiting"),
    BULBOUS("Bulbous");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Category getCategory(String name) {
        switch (name) {
            case "Decorative-Blooming":
                return Category.DECORATIVE_BLOOMING;
            case "Decorative-Deciduous":
                return Category.DECORATIVE_DECIDUOUS;
            case "Succulent":
                return Category.SUCCULENT;
            case "Palm Tree":
                return Category.PALM_TREE;
            case "Fern":
                return Category.FERN;
            case "Orchid":
                return Category.ORCHID;
            case "Bromeliad":
                return Category.BROMELIAD;
            case "Coniferous":
                return Category.CONIFEROUS;
            case "Fruiting":
                return Category.FRUITING;
            case "Bulbous":
                return Category.BULBOUS;
        }
        return null;
    }
}
