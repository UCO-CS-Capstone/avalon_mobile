package edu.uco.avalon;

/**
 * Created by Michael Keller on 2/3/18.
 * Store the basic info needed for a project overview
 */

public class Project {
    private String name;
    private String startDate;
    private String estEndDate;
    private String status;

    public Project(String name, String startDate, String estEndDate, String status){
        this.name = name;
        this.startDate = startDate;
        this.estEndDate = estEndDate;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
