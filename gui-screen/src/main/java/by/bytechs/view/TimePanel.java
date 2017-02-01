package by.bytechs.view;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This is class to display the date and time on main root
 * @author Romanovich Andrei
 */
@Component
public class TimePanel extends TextFlow {
    private Date currentTime;
    private final DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");
    private Timer timer;
    private Text date;

    public TimePanel() {
        date = new Text();
        setLayoutX(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 150);
        setLayoutY(0);
        getChildren().add(date);
        timer = new Timer(1000, e -> {
            currentTime = new Date();
            String dateText = dateFormat.format(currentTime);
            date.setText(dateText);
        });
        timer.start();
    }

    public void init() {
        date.setFill(Color.BLACK);
        date.setFont(Font.font("Helvetica", FontPosture.REGULAR, 25));
    }
}
