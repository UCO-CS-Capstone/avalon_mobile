package edu.uco.avalon;

import java.io.Serializable;

/**
 * Created by mk on 3/16/18.
 */

public class MileStone implements Serializable {
    private int projectID;
    private String projectName;
    private double cost;
    private String startDate;
    private String endDate;

    public MileStone(int projectID, String projectName, double cost, String startDate, String endDate){
        this.projectID = projectID;
        this.projectName = projectName;
        this.cost = cost;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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
}
