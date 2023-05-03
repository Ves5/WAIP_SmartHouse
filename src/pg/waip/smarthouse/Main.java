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

    public static void main(String[] args) throws Exception {
        new Main();
    }

    public Main() throws IOException{
        // loading .ini file from /resources (no clue how it knows where or which file but works)
        Configuration.INSTANCE.load(this);
        initGUI();
    }

    /* This should be moved to GUI class
    * Was used for testing if it works with simulator */
    private void initGUI(){
        JFrame f = new JFrame();
        f.setTitle(Configuration.INSTANCE.getProperty("app.name"));
        f.getContentPane().setLayout(new BorderLayout());
        f.getContentPane().add(gui = new GUI(this), BorderLayout.CENTER);
        f.pack();
        f.setLocation(100, 100);
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
