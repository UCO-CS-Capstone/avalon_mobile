package edu.uco.avalon;

import java.util.ArrayList;

/**
 * Created by cdcal on 2/4/2018.
 *
 * Michael Keller on 3/27/2018.
 * Added equipment cost and calculate cost
 *
 */

public class Equipment {

    public static ArrayList<String> typeList = new ArrayList<>(); //should be accessed from db
    public static ArrayList<Equipment> equipmentList = new ArrayList<>(); //should be accessed from db
    //These two weren't in the individual report ^

    public static Equipment editOption;
    public static int id;

    private String name;
    private String type;
    private boolean activeProject;
    public ArrayList<Maintenance> maintenanceList ;

    private double dailyCost = 0;

    Equipment(String name, String type, boolean activeProject){
        this.name = name;
        this.type = type;
        this.activeProject = activeProject;
        maintenanceList = new ArrayList<>();
    }

    Equipment(String name, String type, boolean activeProject, double dailyCost){
        this(name, type, activeProject); //Pass it to the other constructor

        this.dailyCost = dailyCost;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public boolean getActiveProject(){ return activeProject; }

    public void AddMaintenance (Maintenance m){
            maintenanceList.add(m);
            sortMaintenance();
    }

    private void sortMaintenance(){

        for(int i=0; i< maintenanceList.size(); i++){
            for(int j=0; j<maintenanceList.size(); j++){
                if(maintenanceList.get(i).compareto(maintenanceList.get(j)) <0) {
                    Maintenance t= maintenanceList.get(i);
                    maintenanceList.set(i, maintenanceList.get(j));
                    maintenanceList.set(j, t);
                }
            }
        }
    }

    //Find the cost for the equipment for the time it is on a job site
    public double generateCost(int numberOfDays){
        return dailyCost * numberOfDays;
    }

    //In a spinner it will show the name of the equipment
    public String toString(){
        return name;
    }

    public double getDaileyCost(){
        return dailyCost;
    }
}
