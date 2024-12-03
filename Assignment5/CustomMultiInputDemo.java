import java.awt.*;
import javax.swing.*;

public class CustomMultiInputDemo {
    public static void main(String[] args) {
        JTextField nameField = new JTextField(10);
        JTextField ageField = new JTextField(5);
        JTextField cityField = new JTextField(10);

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);
        panel.add(new JLabel("City:"));
        panel.add(cityField);

        int result = JOptionPane.showConfirmDialog(
            null, 
            panel, 
            "Enter Multiple Values", 
            JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String age = ageField.getText();
            String city = cityField.getText();

            System.out.println("Name: " + name);
            System.out.println("Age: " + age);
            System.out.println("City: " + city);
        }
    }
}
