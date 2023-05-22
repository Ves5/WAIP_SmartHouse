package pg.waip.smarthouse.controllers;

import pg.waip.smarthouse.Configuration;
import pg.waip.smarthouse.Thing;

public class GateController extends Thing {

    private boolean state = false;

    public GateController(){
        this.name = "Gate";
        this.id = Configuration.INSTANCE.getProperty("house.code.controller.gate");
    }

    public void action(String command, String senderData) {
        if(command.equals("UP")){
            if(state){
                /* Gate already raised */
                this.house.replySMS(senderData, this.name + ": Gate is already raised");
            } else {
                /* Raising Gate */
                state = true;
                raiseGate();
                this.house.replySMS(senderData, this.name + ": Raised.");
            }
        } else if (command.equals("DOWN")) {
            if (state){
                /* Lowering Gate */
                state = false;
                lowerGate();
                this.house.replySMS(senderData, this.name + ": Lowered.");
            } else {
                /* Gate already lowered */
                this.house.replySMS(senderData, this.name + ": Gate was already lowered.");
            }
        } else {
            this.house.replySMS(senderData, this.name + ": Unknown argument: " + command + ".\nPossible arguments: UP, DOWN");
        }
    }

    private void raiseGate(){
        System.out.println("Gate: raising gate.");
    }
    private void lowerGate(){
        System.out.println("Gate: lowering gate");
    }
}
