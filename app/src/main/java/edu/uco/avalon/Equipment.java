package edu.uco.avalon;

/**
 * Created by cdcal on 2/4/2018.
 */

public class Equipment {

    private EquipmentState equipmentState;

    /*
    @Override
    public void render(Graphics2D g) {
        laserState.render(g);
    }

    @Override
    public void update() {
        laserState.update(this, super.bounce);

    }
     */


    public void setState(EquipmentState state) {
        this.equipmentState = state;
    }
}
