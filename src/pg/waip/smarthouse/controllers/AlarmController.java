package pg.waip.smarthouse.controllers;

import pg.waip.smarthouse.Configuration;
import pg.waip.smarthouse.Thing;

public class AlarmController extends Thing {

    private boolean state = false;

    public AlarmController(){
        this.name = "Alarm";
        this.id = Configuration.INSTANCE.getProperty("house.code.controller.alarm");
    }

    public void action(String command, String senderData) {
        if(command.equals("ON")){
            if(state){
                /* Alarm already armed */
                this.house.replySMS(senderData, this.name + ": Alarm was already armed.");
            } else {
                /* Alarm turning on */
                state = true;
                turnOnAlarm();
                this.house.replySMS(senderData, this.name + ": Turning on.");
            }
        } else if (command.equals("OFF")) {
            if (state){
                /* Alarm turning off */
                state = false;
                turnOffAlarm();
                this.house.replySMS(senderData, this.name + ": Turning off.");
            } else {
                /* Alarm already disarmed */
                this.house.replySMS(senderData, this.name + ": Alarm is already disarmed.");
            }
        } else {
            this.house.replySMS(senderData, this.name + ": Unknown argument: " + command + ".\nPossible arguments: ON, OFF");
        }
    }

    private void turnOnAlarm(){
        // dummy method
        System.out.println("Alarm: turning on");
    }

    private void turnOffAlarm(){
        // dummy method
        System.out.println("Alarm: turning on");
    }
}
