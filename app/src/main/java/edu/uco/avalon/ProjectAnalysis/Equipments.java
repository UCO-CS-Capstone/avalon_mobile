package edu.uco.avalon.ProjectAnalysis;


public class Equipments {
    private int equipId;
    private int cost;

    public Equipments(int id, int cost) {
        this.equipId = id;
        this.cost = cost;
    }

    public int getEquipId() {
        return this.equipId;
    }

    public int getCost() {
        return this.cost;
    }
}
