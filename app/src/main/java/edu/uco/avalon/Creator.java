package edu.uco.avalon;

/**
 * Created by Michael Keller on 3/27/18.
 *
 * Make everything in one place to rule them all!
 */

public class Creator {
    private static Creator instance = null;

    public Creator() {}

    public static Creator getInstance(){
        if(instance == null){
            instance = new Creator();
            instance.generate();
        }

        return instance;
    }

    private void generate()
    {
        Equipment.typeList.add("Vehicle");
        Equipment.typeList.add("Tool");
        Equipment.typeList.add("Personal");
        Equipment.typeList.add("Other");

        //Need a blank item for the list view of the equipment
        Equipment.equipmentList.add(new Equipment(" ", " ",
                false, 0));
        Equipment.equipmentList.add(new Equipment("Loader", "Vehicle",
                false, 275.35));
        Equipment.equipmentList.add(new Equipment("Drilling Rig", "Vehicle",
                false, 2345.67));
        Equipment.equipmentList.add(new Equipment("Bulldozer", "Vehicle",
                false, 1314.57));
        Equipment.equipmentList.add(new Equipment("Water Truck", "Vehicle",
                false, 3546.96));
        Equipment.equipmentList.add(new Equipment("Bob the Builder", "Personal",
                false, 1345.21));
        Equipment.equipmentList.add(new Equipment("Generator", "Tool",
                false, 679.06));

        Project.projectList.add(new Project("Oklahoma City South", "06/27/2018",
                "07/13/2018", "", 500, "$500",
                250, "$250", "On Schedule"));
        Project.projectList.add(new Project("Oakland", "05/27/2018",
                "12/17/2019", "", 4000, "$4,000",
                500, "$500", "On Schedule"));
        Project.projectList.add(new Project("Dallas, TX", "01/15/2018",
                "03/14/2018", "", 5500, "$5,500",
                20, "$20", "Behind Schedule"));
        Project.projectList.add(new Project("New York", "02/12/2019",
                "01/23/2018", "", 999, "$999",
                999, "$999",
                "Current cost has reached its estimated cost."));
        Project.projectList.add(new Project("Las Vegas", "08/07/2018",
                "07/03/2019", "", 500, "$500",
                2050, "$2,050",
                "Current cost exceeds estimated cost."));
        Project.projectList.add(new Project("Kansas City", "01/22/2018",
                "05/09/2018", "03/15/2018", 30000,
                "$30,000", 3000, "$3,000",
                "Finished"));
        Project.projectList.add(new Project("Arkham City", "01/22/2017",
                "05/09/2018", "03/15/2018", 3000,
                "$3,000", 3000, "$3,000", "Done"));


        //Set the id's for the projects
        for (int x = 0; x < Project.projectList.size(); x++) {
            Project.projectList.get(x).setID(x);
        }

        //Create some milestones for the projects
        Project.projectList.get(1).milestones.add(new Milestone(1,
                "Drilling", Project.projectList.get(1).getName(), 55000,
                "01/22/2019", "06/12/2019"));
        Project.projectList.get(1).milestones.add(new Milestone(1,
                "Pump Building", Project.projectList.get(1).getName(), 1255000,
                "06/02/2019", "12/12/2019"));
        Project.projectList.get(1).milestones.add(new Milestone(1,
                "Fencing", Project.projectList.get(1).getName(), 67330,
                "01/12/2020", "02/12/2020"));
        Project.projectList.get(1).milestones.add(new Milestone(1,
                "Station Building", Project.projectList.get(1).getName(),
                1255000, "03/16/2020", "07/02/2020"));

        //Set the current milestone if there are milestones
        for (int x = 0; x < Project.projectList.size(); x++) {
            Project p = Project.projectList.get(x);

            //Just set it to the first milestone until it can be calculated
            if (p.milestones.size() > 0) {
                p.setCurrentMilestone(p.milestones.get(0).getMilestoneName());
            }
        }
    }
}
