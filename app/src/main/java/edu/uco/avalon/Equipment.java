package edu.uco.avalon;

import java.util.ArrayList;

/**
 * Created by cdcal on 2/4/2018.
 */

public class Equipment {

    public static ArrayList<String> typeList = new ArrayList<>(); //should be accessed from db
    public static ArrayList<Equipment> equipmentList = new ArrayList<>(); //should be accessed from db
    public static Equipment editOption;
    public static int id;

    private String name;
    private String type;
    private boolean activeProject;
    public ArrayList<Maintenance> maintenanceList ;

    Equipment(String name, String type, boolean activeProject){
        this.name = name;
        this.type = type;
        this.activeProject = activeProject;
        maintenanceList = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public void AddMaintenance (Maintenance m){
            maintenanceList.add(m);
            sortMaintenance();
    }

    public boolean getActiveProject(){ return activeProject; }

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
}
