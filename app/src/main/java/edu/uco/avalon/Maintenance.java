package edu.uco.avalon;

import java.util.ArrayList;

/**
 * Created by cdcal on 2/4/2018.
 */

public class Maintenance {

    public static ArrayList<String> typeList = new ArrayList<>(); //should be accessed from db
    public static ArrayList<Maintenance> maintenancelist = new ArrayList<>(); //should be accessed from db
    public static Maintenance editOption;
    public static int selected;

    public static int id;

    private String date;
    private String type;

    Maintenance(String date, String type){
        this.date = date;
        this.type = type;
    }

    public String getDate(){
        return date;
    }

    public String getType(){
        return type;
    }

    public int compareto(Maintenance t){

        return date.compareTo(t.getDate());
    }


}
