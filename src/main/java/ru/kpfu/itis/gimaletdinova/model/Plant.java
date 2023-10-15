package ru.kpfu.itis.gimaletdinova.model;

import ru.kpfu.itis.gimaletdinova.model.plant_enam.Category;
import ru.kpfu.itis.gimaletdinova.model.plant_enam.Level;

public class Plant {
    private int id;
    private Category category;
    private String name;
    private String img;
    private String origin;
    private String description;
    private int high;
    private Level light;
    private Level watering;
    private Boolean toxicity;
    private Level careDifficulty;

}
