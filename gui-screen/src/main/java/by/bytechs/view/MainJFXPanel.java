package by.bytechs.view;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.swing.*;

/**
 * This is class a component to embed JavaFX content into Swing applications.
 * @author Romanovich Andrei
 * @see MainSwingJFramel
 */
@Component
public class MainJFXPanel extends JFXPanel {
    @Autowired
    @Qualifier(value = "scene")
    private Scene scene;

    public MainJFXPanel() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    public void initFX(RootPaneApp root) {
        setScene(scene);
        root.init();
    }
}
