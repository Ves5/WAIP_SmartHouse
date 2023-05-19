package pg.waip.smarthouse;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class GUI extends JFrame {

    private JPanel content;
    private Main mMain;
    private JLabel mText;

    private JButton startBtn, stopBtn, acBtn, wmBtn;

    public GUI(Main main){
        // set up frame
        mMain = main;
        setTitle(Configuration.INSTANCE.getProperty("app.name"));
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(content = new JPanel(), BorderLayout.CENTER);
        // set up content
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        mText = new JLabel(Configuration.INSTANCE.getProperty("app.description"));
        textPanel.add(mText);
        textPanel.setBorder(new EmptyBorder(8,8,8,8));

        content.setLayout(new BorderLayout());
        content.add(textPanel, BorderLayout.CENTER);
        /* buttons */
        initButtons();
//        updateState();
        pack();
        setLocation(100, 100);
//        setSize(200, 100);
        setVisible(true);
        updateState();
    }

    private void initButtons(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(startBtn = new JButton(
                new AbstractAction("Start") {
                    public void actionPerformed(ActionEvent actionEvent) {
                        mMain.start();
                    }
                }
        ));
        buttonPanel.add(stopBtn = new JButton(
                new AbstractAction("Stop") {
                    public void actionPerformed(ActionEvent actionEvent) {
                        mMain.stop();
                    }
                }
        ));
        buttonPanel.add(acBtn = new JButton(
                new AbstractAction("Trigger AC") {
                    public void actionPerformed(ActionEvent actionEvent) {
                        mMain.triggerAC();
                    }
                }
        ));
        buttonPanel.add(wmBtn = new JButton(
                new AbstractAction("Trigger WM") {
                    public void actionPerformed(ActionEvent actionEvent) {
                        mMain.triggerWM();
                    }
                }
        ));
        buttonPanel.setBorder(new EmptyBorder(0, 8, 8, 8));
        content.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void updateState(){
        startBtn.setEnabled(!mMain.isStarted());
        stopBtn.setEnabled(mMain.isStarted());
        acBtn.setEnabled(mMain.isStarted());
        wmBtn.setEnabled(mMain.isStarted());
    }
}
