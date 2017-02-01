package by.bytechs.view.main;

import by.bytechs.service.exception.ServiceException;
import by.bytechs.view.MainSwingJFramel;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;

/**
 * @author Romanovich Andrei
 */
public class MainApp {
    public static ClassPathXmlApplicationContext context;
    public static void main(String[] args) throws ServiceException{
        context = new ClassPathXmlApplicationContext("beans-gui.xml");
        SwingUtilities.invokeLater(() -> {
            MainSwingJFramel main = (MainSwingJFramel) context.getBean("mainSwingJFramel");
            main.init();
        });
    }
}
