package pg.waip.smarthouse.controllers;

import pg.waip.smarthouse.Configuration;
import pg.waip.smarthouse.Thing;

public class WmController extends Thing {

    private boolean state = false;

    public WmController(){
        this.name = "WM";
        this.id = Configuration.INSTANCE.getProperty("house.code.controller.wm");
    }

    public void action(String command, String senderData) {
        if (!state){
            // turning on WM
            state = true;
            System.out.println("WM: Washing machine turned on.");
        } else {
            // WM finished
            System.out.println("WM: Washing machine finished washing.");
            this.house.notifySMS(name + ": washing finished.");
        }
    }
}
