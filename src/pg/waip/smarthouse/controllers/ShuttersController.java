package pg.waip.smarthouse.controllers;

import pg.waip.smarthouse.Configuration;
import pg.waip.smarthouse.Thing;

public class ShuttersController extends Thing {

    private boolean[] shuttersState = {false, true, false, true, false};

    public ShuttersController(){
        this.name = "Shutters";
        this.id = Configuration.INSTANCE.getProperty("house.code.controller.shutters");
    }

    public void action(String command, String senderData) {
        if (command.equals("UP")){
            raiseShutters();
            house.replySMS(senderData, name + ": Raised.");
        } else if (command.equals("DOWN")) {
            lowerShutters();
            house.replySMS(senderData, name + ": Lowered.");
        } else {
            this.house.replySMS(senderData, this.name + ": Unknown argument: " + command + ".\nPossible arguments: UP, DOWN");
        }
    }

    private void raiseShutters(){
        System.out.println("Shutters: Raising shutters.");
    }
    private void lowerShutters(){
        System.out.println("Shutters: Lowering shutters.");
    }
}
