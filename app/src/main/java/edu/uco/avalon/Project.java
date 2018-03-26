package edu.uco.avalon;


import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by Michael Keller on 2/3/18.
 * Edited by Callie Driver 3/6/2018.
 * Store the basic info needed for a project overview
 */


public class Project implements Serializable {
public class Project {

    public static ArrayList<Project> projectList = new ArrayList<>();
    private String name;
    private String startDate;
    private String estEndDate;
    private String status;

    private String actualEndDate;
    private double estCost;
    private double currentCost;
    private String estCostString, currentCostString; //store the cost as a string so it is less processing later.
    private int id;

    public Project(String name, String startDate, String estEndDate, String status){
        this.name = name;
        this.startDate = startDate;
        this.estEndDate = estEndDate;
        this.status = status;
    }

    public Project(String name, String startDate, String estEndDate, String actualEndDate, double estCost, double currentCost){
        this.name = name;
        this.startDate = startDate;
        this.estEndDate = estEndDate;
        this.actualEndDate = actualEndDate;
        this.estCost = estCost;
        this.currentCost = currentCost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEstEndDate() {
        return estEndDate;
    }

    public void setEstEndDate(String estEndDate) {
        this.estEndDate = estEndDate;
    }

    public String getActualEndDate() {
        return actualEndDate;
    }

    public void setActualEndDate(String ActualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    public double getEstCost() {
        return estCost;
    }

    public void setEstCost(double estCost) {
        this.estCost = estCost;
    }

    public double getCurrentCost() {
        return currentCost;
    }

    public void setCurrentCost(double currentCost) {
        this.currentCost = currentCost;
    }


    public String getEstCostString() {
        return estCostString;
    }

    public void setEstCostString(String estCostString) {
        this.estCostString = estCostString;
    }

    public String getCurrentCostString() {
        return currentCostString;
    }

    public void setCurrentCostString(String currentCostString) {
        this.currentCostString = currentCostString;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }





}
