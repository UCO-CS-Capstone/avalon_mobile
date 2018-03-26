package edu.uco.avalon;

import java.util.ArrayList;

/**
 * Created by cdcal on 2/4/2018.
 */

public class UserMgmt {

    public static ArrayList<String> typeList = new ArrayList<>(); //should be accessed from db
    public static ArrayList<UserMgmt> userlist = new ArrayList<>(); //should be accessed from db
    public static UserMgmt editOption;
    public static int selected;

    public static int id;

    private String Fname;
    private String Lname;
    private String Email;
    private String flagID;
    private String password;


    UserMgmt(String F, String L, String E, String flagID, String pass){
        this.Fname = F;
        this.Lname = L;
        this.Email = E;
        this.flagID = flagID;
        this.password = pass;
    }

    public String getFname(){
        return Fname;
    }

    public String getLname(){
        return Lname;
    }

    public String getEmail() { return Email;}

    public String getFlagID() {return flagID;}

    public String getName(){ return "First Name: " +Fname +" \nLast Name: " + Lname ;}
    public String getEmailandFlag() {return "Email: " +Email + " User Type: " + flagID;}


    public int compareto(UserMgmt t){

        return 0;
    }


}
