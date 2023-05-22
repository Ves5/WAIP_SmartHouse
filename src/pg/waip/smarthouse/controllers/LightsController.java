package pg.waip.smarthouse.controllers;

import pg.waip.smarthouse.Configuration;
import pg.waip.smarthouse.Thing;

public class LightsController extends Thing {

    private boolean[] lightsState = {false, true, true, false, true, false};


    public LightsController(){
        this.name = "Lights";
        this.id = Configuration.INSTANCE.getProperty("house.code.controller.lights");
    }

    public void action(String command, String senderData) {
        turnOffLights();
        house.replySMS(senderData, name + ": turned off.");
    }

    private void turnOffLights(){
        System.out.println("Lights: turning off lights");
    }
}
