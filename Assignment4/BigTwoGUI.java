import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

public class BigTwoGUI implements CardGameUI, ActionListener{
    private BigTwo game;
    private boolean[] selected;
    private int activePlayer;
    private JFrame frame;
    private JPanel bigTwoPanel;
    private JButton playButton;
    private JButton passButton;
    private JTextArea msgArea;
    private JTextArea chatArea;
    private JTextField chatInput;

    public BigTwoGUI(BigTwo game){
        this.game = game;
        selected = new boolean[13];
        Arrays.fill(selected, false);
        frame = new JFrame();
        bigTwoPanel = new BigTwoPanel();
        playButton = new JButton("Play");
        passButton = new JButton("Pass");
        msgArea = new JTextArea();
        chatArea = new JTextArea();
        chatInput = new JTextField();
        
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Big Two");
        frame.setLayout(null);
        frame.setVisible(true);
        
    }

    public void setActivePlayer(int activePlayer){
        this.activePlayer = activePlayer;
    }

    public void repaint(){
        frame.repaint();
    }

    public void printMsg(String msg){
        chatArea.append(msg +  "\n");
    }

    public void clearMsgArea(){
        chatArea.setText("");
    }

    public void reset(){

    }

    public void enable(){
        playButton.setEnabled(true);
        passButton.setEnabled(true);
        chatInput.setEnabled(true);
    }

    public void disable(){
        playButton.setEnabled(false);
        passButton.setEnabled(false);
        chatInput.setEnabled(false);
    }

    public void promptActivePlayer(){

    }

    private class BigTwoPanel extends JPanel implements MouseListener{
        
    }

    private class PlayButtonListener{

    }

    private class RestartMenuItemListener{

    }

    private class QuitMenuItemListener{

    }
}