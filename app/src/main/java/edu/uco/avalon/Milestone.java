package edu.uco.avalon;

import java.io.Serializable;

/**
 * Created by mk on 3/16/18.
 */

public class Milestone implements Serializable {
    private int projectID;
    private int id;
    private String milestoneName;
    private String projectName;
    private double cost;
    private String startDate;
    private String estEndDate;

    public Milestone(int projectID, String milestoneName, String projectName, double cost,
                     String startDate, String estEndDate) {
        this.projectID = projectID;
        this.milestoneName = milestoneName;
        this.projectName = projectName;
        this.cost = cost;
        this.startDate = startDate;
        this.estEndDate = estEndDate;
    }

    public String getEstEndDate() {
        return estEndDate;
    }

    public void setEstEndDate(String endDate) {
        this.estEndDate = endDate;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getMilestoneName() {
        return milestoneName;
    }

    public void setMilestoneName(String milestoneName) {
        this.milestoneName = milestoneName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
