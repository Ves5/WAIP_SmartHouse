package pg.waip.smarthouse;

import com.ericsson.hosasdk.api.HOSAMonitor;
import com.ericsson.hosasdk.utility.framework.FWproxy;
import com.ericsson.nrgsdk.examples.tools.SDKToolkit;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

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

        // this is straight from examples
        HOSAMonitor.addListener(SDKToolkit.LOGGER);
        FWproxy framework = new FWproxy(Configuration.INSTANCE);
        feature = new Feature(framework);

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

        try {
            feature.start();
        } catch (RuntimeException e) {
            System.err.println(SDKToolkit.OBJECTWRITER.print(e));
        }
    }
    public void stop(){
        System.out.println("Stopping SmartHouse service");
        isStarted = false;
        gui.updateState();

        feature.stop();
    }

    public void triggerAC(){
        System.out.println("Triggered AC prompt");
    }

    public void triggerWM(){
        System.out.println("Triggered WashingMachine prompt");
    }
}
