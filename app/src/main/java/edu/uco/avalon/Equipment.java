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

    Equipment(String name, String type, boolean activeProject){
        this.name = name;
        this.type = type;
        this.activeProject = activeProject;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public boolean getActiveProject(){ return activeProject; }

}
