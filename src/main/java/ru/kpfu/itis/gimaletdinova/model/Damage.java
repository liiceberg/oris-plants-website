package ru.kpfu.itis.gimaletdinova.model;

import ru.kpfu.itis.gimaletdinova.model.enam.CausativeAgent;

public class Damage {
    private int id;
    private CausativeAgent causativeAgentType;
    private String causativeAgentDescription;
    private String symptoms;
    private String controlMeasures;
    private String img;

    public Damage(int id, CausativeAgent causativeAgentType, String causativeAgentDescription,
                  String symptoms, String controlMeasures, String img) {
        this.id = id;
        this.causativeAgentType = causativeAgentType;
        this.causativeAgentDescription = causativeAgentDescription;
        this.symptoms = symptoms;
        this.controlMeasures = controlMeasures;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CausativeAgent getCausativeAgentType() {
        return causativeAgentType;
    }

    public void setCausativeAgentType(CausativeAgent causativeAgentType) {
        this.causativeAgentType = causativeAgentType;
    }

    public String getCausativeAgentDescription() {
        return causativeAgentDescription;
    }

    public void setCausativeAgentDescription(String causativeAgentDescription) {
        this.causativeAgentDescription = causativeAgentDescription;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getControlMeasures() {
        return controlMeasures;
    }

    public void setControlMeasures(String controlMeasures) {
        this.controlMeasures = controlMeasures;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
