package edu.uco.avalon;

/**
 * Created by cdcal on 2/4/2018.
 */

public class EquipmentStateInUse implements EquipmentState {

    public void next(Equipment context){
        context.setState(new EquipmentStateOpen());
    }
}
