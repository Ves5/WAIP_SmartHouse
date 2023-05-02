package pg.waip.smarthouse;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
public class GUI extends JPanel {

    private Main mMain;
    private JLabel mText;

    public GUI(Main main){
        mMain = main;
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        mText = new JLabel(Configuration.INSTANCE.getName());
        textPanel.add(mText);
        textPanel.setBorder(new EmptyBorder(8,8,8,8));
        /* buttons */
        setLayout(new BorderLayout());
        add(textPanel, BorderLayout.CENTER);
//        updateState();
    }
}
