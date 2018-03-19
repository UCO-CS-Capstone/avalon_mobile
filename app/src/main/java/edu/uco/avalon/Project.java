package edu.uco.avalon;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by Michael Keller on 2/3/18.
 * Edited by Callie Driver 3/6/2018.
 * Edited by Michael Keller 3/16/2018.
 *      Cleaned up the code. Added milestones to a project.
 *
 * Store the basic info needed for a project overview
 */


public class Project implements Serializable {
    public static ArrayList<Project> projectList = new ArrayList<>();

    public static ArrayList<Milestone> milestones = new ArrayList<>();

    private String name;
    private String startDate;
    private String estEndDate;
    private String status;

    private String actualEndDate;
    private double estCost;
    private double currentCost;
    private String estCostString,
                   currentCostString; //store the cost as a string so it is less processing later.
    private int id;

    public Project(String name, String startDate, String estEndDate, String status){
        this.name = name;
        this.startDate = startDate;
        this.estEndDate = estEndDate;
        this.status = status;
    }

    public Project(String name, String startDate, String estEndDate, String actualEndDate,
                   double estCost, String estCostString, double currentCost,
                   String currentCostString){
        this.name = name;
        this.startDate = startDate;
        this.estEndDate = estEndDate;
        this.actualEndDate = actualEndDate;
        this.estCost = estCost;
        this.estCostString = estCostString;
        this.currentCost = currentCost;
        this.currentCostString = currentCostString;
        this.status = "";
    }

    public Project(String name, String startDate, String estEndDate, String actualEndDate,
                   double estCost, String estCostString, double currentCost,
                   String currentCostString, String status){
        this.name = name;
        this.startDate = startDate;
        this.estEndDate = estEndDate;
        this.actualEndDate = actualEndDate;
        this.estCost = estCost;
        this.estCostString = estCostString;
        this.currentCost = currentCost;
        this.currentCostString = currentCostString;
        this.status = status;
    }


    public int checkStatus(){

        // 0          = no errors                         = BLUE
        // 1 to 9     = warning                           = ORANGE
        // 10 to 99   = important warning                 = RED

        int warning = 0;
        status = "";

        //Check date stuff first
        Date start_date, est_end_date, current_date = Calendar.getInstance().getTime();

        try {
            //have to be if/else or else java complains.
            if(!startDate.equals("")) {
                start_date = new SimpleDateFormat("MM/dd/yyyy").parse(startDate);
            }
            else{
                start_date = new Date();
            }

            if(!estEndDate.equals("")) {
                est_end_date = new SimpleDateFormat("MM/dd/yyyy").parse(estEndDate);
            }
            else{
                est_end_date = new Date();
            }

            //if the current date is past the project's est date
            if(!estEndDate.equals("") && current_date.after(est_end_date))
            {
                status += "Behind Schedule.\n\n";
                warning++;
            }
            //if the project started after estDate, give a warning.
            if(!startDate.equals("") && !estEndDate.equals("") && start_date.after(est_end_date))
            {
                status += "The start date for this project is after the estimated date.\n\n";
                warning++;
            }
            if(estCost == currentCost){
                status += "Current cost has reached its estimated cost. (" + currentCostString + " = " + estCostString + ")\n\n";
                warning++;
            }
            else if(estCost < currentCost){
                status += "Current cost exceeds estimated cost. (" + currentCostString + " < " + estCostString + ")\n\n";
                warning += 10;
            }


            // 0          = no errors                         = BLUE
            // 1          = warning                           = ORANGE
            // 2          = important warning                 = RED
            // 3          = finished project with errors      = YELLOW
            // 4          = finished project with no errors.  = GREEN
            if(!actualEndDate.equals("") && warning == 0){//if there is an actual end date and no errors.
                status = "Project Finished.";
                warning = 4;
            }
            else if(!actualEndDate.equals("") && warning > 0){ //if there is an actual end date with errors
                status += "Project Finished.";
                warning = 3;
            }
            else if(warning >= 10){ //if there is a major warning
                warning = 2;
            }
            else if(warning > 0){//if there are only lesser warnings
                warning = 1;
            }
            else if (warning == 0){
                status = "On Schedule.";
            }

        }
        catch(Exception e){}

        return warning;
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

    public int getID(){
        return id;
    }

    public void setID(int id){
        this.id = id;
    }
}
