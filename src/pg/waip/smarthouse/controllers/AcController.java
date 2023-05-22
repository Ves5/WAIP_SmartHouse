package pg.waip.smarthouse.controllers;

import pg.waip.smarthouse.Configuration;
import pg.waip.smarthouse.Thing;

public class AcController extends Thing {

    private float humidity = 0.3f;

    public AcController(){
        this.name = "AC";
        this.id = Configuration.INSTANCE.getProperty("house.code.controller.ac");
    }

    public void action(String command, String senderData) {
        if (humidity > 0.65f){
            turnOnAC();
            house.notifySMS(name + ": Turning on AC because of high humidity levels.");
        } else if (humidity < 0.45f) {
            turnOffAC();
        }
    }

    public void toggleHumidity() {
        if (humidity < 0.45f)
            humidity = 0.7f;
        else
            humidity -= 0.2f;
        System.out.println("Humidity at: " + humidity);
    }

    private void turnOnAC(){
        // dummy method
        System.out.println("AC: turning on");
    }
    private void turnOffAC(){
        // dummy method
        System.out.println("AC: turning off");
    }
}
