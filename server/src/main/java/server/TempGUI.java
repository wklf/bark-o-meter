package server;

import javax.swing.*;
import java.awt.*;

// this entire class should be replaced with a CLI interface (picocli?)
public class TempGUI extends JPanel {

    private JTextArea textArea = new JTextArea();

    public TempGUI(){
        this.add(textArea);
        textArea.setPreferredSize(new Dimension(200, 200));
        SwingUtilities.invokeLater(() -> {
            JFrame frameMain = new JFrame();
            frameMain.setTitle("The Server");
            frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frameMain.setResizable(true);
            frameMain.add(this);
            frameMain.pack();
            frameMain.setVisible(true);
            frameMain.setLocationRelativeTo(null);
        });
    }
}
