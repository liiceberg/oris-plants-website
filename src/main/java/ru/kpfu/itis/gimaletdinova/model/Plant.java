package ru.kpfu.itis.gimaletdinova.model;

import ru.kpfu.itis.gimaletdinova.model.enam.Category;
import ru.kpfu.itis.gimaletdinova.model.enam.Level;

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

    public Plant(int id, Category category, String name, String img, String origin, String description, int high,
                 Level light, Level watering, Boolean toxicity, Level careDifficulty) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.img = img;
        this.origin = origin;
        this.description = description;
        this.high = high;
        this.light = light;
        this.watering = watering;
        this.toxicity = toxicity;
        this.careDifficulty = careDifficulty;
    }

    public Plant(Category category, String name, String img, String origin, String description, int high,
                 Level light, Level watering, Boolean toxicity, Level careDifficulty) {
        this.category = category;
        this.name = name;
        this.img = img;
        this.origin = origin;
        this.description = description;
        this.high = high;
        this.light = light;
        this.watering = watering;
        this.toxicity = toxicity;
        this.careDifficulty = careDifficulty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public Level getLight() {
        return light;
    }

    public void setLight(Level light) {
        this.light = light;
    }

    public Level getWatering() {
        return watering;
    }

    public void setWatering(Level watering) {
        this.watering = watering;
    }

    public Boolean getToxicity() {
        return toxicity;
    }

    public void setToxicity(Boolean toxicity) {
        this.toxicity = toxicity;
    }

    public Level getCareDifficulty() {
        return careDifficulty;
    }

    public void setCareDifficulty(Level careDifficulty) {
        this.careDifficulty = careDifficulty;
    }
}
