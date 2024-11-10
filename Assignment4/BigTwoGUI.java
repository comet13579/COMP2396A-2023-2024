import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

public class BIgTwoGUI implements CardGameUI, ActionListener{
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

    public BIgTwoGUI(BigTwo game){
        this.game = game;
        selected = new boolean[13];
        Arrays.fill(selected, false);
        JFrame frame = new JFrame();
    }

    public void setActivePlayer(int activePlayer){
        this.activePlayer = activePlayer;
    }

    public void repaint(){

    }

    public void printMsg(String msg){

    }

    public void clearMsgArea(){

    }

    public void reset(){

    }

    public void enable(){
        playButton.setEnabled(true);
        passButton.setEnabled(true);
    }

    public void disable(){
        playButton.setEnabled(false);
        passButton.setEnabled(false);
    }

    public void promptActivePlayer(){

    }

    private class BIgTwoPanel{

    }

    private class PlayButtonListener{

    }

    private class RestartMenuItemListener{

    }

    private class QuitMenuItemListener{

    }
}
