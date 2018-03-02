package edu.uco.avalon.ProjectAnalysis;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Projects {

    private Date beginDate;
    private Date endDate;

    public Projects() {

    }

    public void setProjectDateBegin(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        try{
            this.beginDate = format.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setProjectDateEnd(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        try{
            this.endDate = format.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Date getProjectDateBegin() {
        return this.beginDate;
    }
    public Date getProjectDateEnd() {
        return this.endDate;
    }
}
