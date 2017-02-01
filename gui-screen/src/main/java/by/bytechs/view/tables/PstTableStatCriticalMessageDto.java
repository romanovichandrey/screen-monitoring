package by.bytechs.view.tables;

import by.bytechs.screenMonitoringDto.StatCriticalMessageDto;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

/**
 * @author Romanovich Andrei
 */
public class PstTableStatCriticalMessageDto extends Application {

    private TableView<StatCriticalMessageDto> table = new TableView();
    private ObservableList<StatCriticalMessageDto> data;
    private String nameLabel;

    public PstTableStatCriticalMessageDto(List<StatCriticalMessageDto> dtoList, String nameLabel) {
        this.data = FXCollections.observableArrayList(dtoList);
        this.nameLabel = nameLabel;
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("ПСТ");
        stage.setWidth(520);
        stage.setHeight(500);

        final Label label = new Label(nameLabel);
        label.setFont(new Font("Arial", 14));

        table.setEditable(true);

        TableColumn consecutiveNumberCol = new TableColumn("№ п/п");
        consecutiveNumberCol.setMinWidth(15);
        consecutiveNumberCol.setCellValueFactory(new PropertyValueFactory<StatCriticalMessageDto, Integer>("numberMessage"));

        TableColumn logicNameCol = new TableColumn("Логическое имя");
        logicNameCol.setMinWidth(100);
        logicNameCol.setCellValueFactory(new PropertyValueFactory<StatCriticalMessageDto, String>("logicalNameTerminal"));

        TableColumn terminalIdCol = new TableColumn("Идентификатор");
        terminalIdCol.setMinWidth(100);
        terminalIdCol.setCellValueFactory(new PropertyValueFactory<StatCriticalMessageDto, String>("idTerm"));

        TableColumn cbuCol = new TableColumn("Расположение");
        cbuCol.setMinWidth(200);
        cbuCol.setCellValueFactory(new PropertyValueFactory<StatCriticalMessageDto, String>("adressTerminal"));

        table.setItems(data);
        table.getColumns().addAll(consecutiveNumberCol, logicNameCol, terminalIdCol, cbuCol);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        stage.getIcons().add(new Image("by/bytechs/icons/pst.png"));
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);
        stage.show();
    }
}
