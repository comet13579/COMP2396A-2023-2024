import javax.swing.JOptionPane;

public class InputDialogDemo {
    public static void main(String[] args) {
        // Simplified and correct version
        String age = JOptionPane.showInputDialog("Enter your age:");
        
        // If you want more parameters, use this form:
        String name = JOptionPane.showInputDialog(
            null,               // Parent component
            "What is your name?" // Message
        );
        
        // Check and print input
        if (age != null) {
            System.out.println("Age entered: " + age);
        }
        
        if (name != null) {
            System.out.println("Name entered: " + name);
        }
    }
}



