package by.bytechs.view;

import by.bytechs.controller.ComponentController;
import by.bytechs.controller.PaymentController;
import by.bytechs.controller.TerminalForFineController;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;

/**
 * This is class root Pane application.
 *
 * @author Romanovich Andrei
 * @version 1.0
 * @see Pane
 */
@org.springframework.stereotype.Component
public class RootPaneApp extends Pane {
    @Autowired
    private TimePanel timePanel;
    @Autowired
    private PSTPane pstPane;
    @Autowired
    private ATMPane atmPane;
    @Autowired
    private ComponentController componentController;
    @Autowired
    private TerminalForFineController terminalForFineController;
    @Autowired
    private PaymentController paymentController;
    private final int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    private final int height = Toolkit.getDefaultToolkit().getScreenSize().height;

    public RootPaneApp() {
        setPrefSize(width, height);
    }

    public void init() {
        getChildren().add(timePanel);
        getChildren().add(pstPane);
        getChildren().add(atmPane);

        atmPane.init();
        pstPane.init();
        timePanel.init();

        terminalForFineController.init();
        componentController.init();
        paymentController.init();
    }
}
