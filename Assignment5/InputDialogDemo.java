import javax.swing.JOptionPane;

public class InputDialogDemo {
    public static void main(String[] args) {
        // Simplified and correct version
        String age = JOptionPane.showInputDialog("Enter your age:");
        
        // Check and print input
        if (age != null) {
            System.out.println("Age entered: " + age);
        }

    }
}