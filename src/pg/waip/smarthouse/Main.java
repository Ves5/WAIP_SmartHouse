package pg.waip.smarthouse;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Main {

    private Feature feature;
    private GUI gui;

    private boolean isStarted = false;
    public boolean isStarted(){
        return isStarted;
    }

    public static void main(String[] args) throws Exception {
        new Main();
    }

    public Main() throws IOException{
        System.out.println("Starting SmartHouse application initialization");
        // loading .ini file from /resources (no clue how it knows where or which file but works)
        Configuration.INSTANCE.load(this);
        initGUI();
    }

    /* This should be moved to GUI class
    * Was used for testing if it works with simulator */
    private void initGUI(){
        gui = new GUI(this);
        gui.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("Closing SmartHouse application.");
                System.exit(0);
            }
        });
    }

    public void start(){
        System.out.println("Starting SmartHouse service");
        isStarted = true;
        gui.updateState();
    }
    public void stop(){
        System.out.println("Stopping SmartHouse service");
        isStarted = false;
        gui.updateState();
    }

    public void triggerAC(){
        System.out.println("Triggered AC prompt");
    }

    public void triggerWM(){
        System.out.println("Triggered WashingMachine prompt");
    }
}
