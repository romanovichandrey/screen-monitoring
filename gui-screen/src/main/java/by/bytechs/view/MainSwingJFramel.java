package by.bytechs.view;

import javafx.application.Platform;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.*;

/**
 * This is class main screen application in swing.
 *
 * @author Romanovich Andrei
 * @version 1.0
 */
@org.springframework.stereotype.Component
public class MainSwingJFramel extends JFrame {
    @Autowired
    private MainJFXPanel jfxPanel;
    @Autowired
    private RootPaneApp root;

    public MainSwingJFramel() throws HeadlessException {
    }

    public void init() {
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setTitle("БАЙТЕХ.IT.ПК.ЭКРАНСМАРТиС");
        Dimension toolkit = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(toolkit.width, toolkit.height);
        add(jfxPanel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        Platform.runLater(() -> jfxPanel.initFX(root));
    }
}
