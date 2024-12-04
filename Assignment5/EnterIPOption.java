import java.awt.*;
import javax.swing.*;

/**
 * The EnterIPOption class is used to create a pop-up panel that allows the user to enter the IP address and port number of the server.
 * @author Sonny Wong
 */
public class EnterIPOption {
    private JTextField ipField;
    private JTextField portField;
    private String ip;
    private int port;
    private JPanel panel;

    /**
     * Constructor for the EnterIPOption class.
     */
    public EnterIPOption() {
        ipField = new JTextField("127.0.0.1", 10);
        portField = new JTextField("2396", 5);
        panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Server IP:"));
        panel.add(ipField);
        panel.add(new JLabel("Server Port:"));
        panel.add(portField);
        if (showInputDialog()) {
            ip = ipField.getText();
            try {
                port = Integer.parseInt(portField.getText());
                if (port < 0 || port > 65535) {
                    JOptionPane.showMessageDialog(null, "Invalid port number");
                    port = -1;
                }
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid port number");
                port = -1;
            }
        }
        else{
            ip = "0.0.0.0";
            port = -1;
        }
    }

    /**
     * Getter methods for the IP address and port number.
     * @return IP address and port number
     */
    public String getIP() {
        return ip;
    }

    /**
     * Getter method for the port number, -1 means invalid port number
     * @return port number
     */
    public int getPort() {
        return port;
    }

    private boolean showInputDialog() {
        int result = JOptionPane.showConfirmDialog(null, panel, "Enter server IP and Port", JOptionPane.OK_CANCEL_OPTION);
        return result == JOptionPane.OK_OPTION;
    }

    private void printIPPort(){
        String ip = getIP();
        int port = getPort();
        System.out.println("IP: " + ip);
        System.out.println("Port: " + port);
    }

    /**
     * Main method to test the EnterIPOption class.
     * @param args not used
     */
    public static void main(String[] args) {
        EnterIPOption ipPort = new EnterIPOption();
        ipPort.printIPPort();
    }
}
