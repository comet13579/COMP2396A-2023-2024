import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;

/**
 * The BigTwoGUI class implements the CardGameUI interface. 
 * It is used to build a GUI for the Big Two card game and handle all user actions.
 * 
 * @author Sonny Wong
 */
public class BigTwoGUI implements CardGameUI {
    private final BigTwo game;
    private final boolean[] selected;
    private int activePlayer;
    private JFrame frame;
    private BigTwoPanel bigTwoPanel;
    private JButton playButton;
    private JButton passButton;
    private JTextArea msgArea;
    private JTextArea chatArea;
    private JTextField chatInput;
    private JMenuItem restartMenuItem;
    private JMenuItem quitMenuItem;

    /**
     * a constructor for creating a BigTwoGUI.
     * @param game BigTwo card game
     */
    public BigTwoGUI(BigTwo game) {
        this.game = game;
        selected = new boolean[13];
        initializeComponents();
    }
    
    private void initializeComponents() {
        // Create main frame
        frame = new JFrame("Big Two");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        restartMenuItem = new JMenuItem("Restart");
        quitMenuItem = new JMenuItem("Quit");
        
        restartMenuItem.addActionListener(new RestartMenuItemListener());
        quitMenuItem.addActionListener(new QuitMenuItemListener());
        
        gameMenu.add(restartMenuItem);
        gameMenu.add(quitMenuItem);
        menuBar.add(gameMenu);
        frame.setJMenuBar(menuBar);
        
        // Create main game panel
        bigTwoPanel = new BigTwoPanel();
        frame.add(bigTwoPanel, BorderLayout.CENTER);
        
        // Create button panel
        JPanel buttonPanel = new JPanel();
        playButton = new JButton("Play");
        passButton = new JButton("Pass");
        
        playButton.addActionListener(new PlayButtonListner());
        passButton.addActionListener(new PassButtonListener());

        buttonPanel.add(playButton);
        buttonPanel.add(passButton);
        
        // Create message area
        msgArea = new JTextArea(20, 20);
        msgArea.setEditable(false);
        JScrollPane msgScroll = new JScrollPane(msgArea);
        
        // Create chat area
        chatArea = new JTextArea(40, 20);
        chatArea.setEditable(false);
        JScrollPane chatScroll = new JScrollPane(chatArea);
        
        // Create chat input
        chatInput = new JTextField(20);
        chatInput.addActionListener(e -> {
            String msg = chatInput.getText();
            if (!msg.isEmpty()) {
                chatArea.append(game.getPlayerList().get(activePlayer).getName() + ": " + msg + "\n");
                chatInput.setText("");
            }
        });
        chatInput.setSize(20,5);
        
        // Create right panel for messages and chat
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.add(msgScroll);
        rightPanel.add(chatScroll);
        rightPanel.add(chatInput);
        
        frame.add(rightPanel, BorderLayout.EAST);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        
        // Set frame properties
        frame.setMinimumSize(new Dimension(800, 780));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }
    
    /**
     * a method for setting the index of the active player (i.e., the player having control of the GUI).
     * @param activePlayer the index of the active player
     */
    @Override
    public void setActivePlayer(int activePlayer) {
        this.activePlayer = activePlayer;
        if (activePlayer >= 0) {
            enable();
        } else {
            disable();
        }
        frame.repaint();
    }
    
    /**
     * a method for repainting the GUI.
     */
    @Override
    public void repaint() {
        frame.repaint();
    }
    
    /**
     * a method for printing the specified string to the message area of the GUI.
     * @param msg the string to be printed
     */
    @Override
    public void printMsg(String msg) {
        msgArea.append(msg + "\n");
    }
    
    /**
     * a method for clearing the message area of the GUI.
     */
    @Override
    public void clearMsgArea() {
        msgArea.setText("");
    }

    /**
     * a method for resetting the GUI. 
     */
    @Override
    public void reset() {
        // First clear all UI elements
        clearMsgArea();
        chatArea.setText("");
        chatInput.setText("");
        Arrays.fill(selected, false);
        
        // Create and shuffle new deck
        BigTwoDeck deck = new BigTwoDeck();
        deck.shuffle();
        
        // Reset game state
        game.getPlayerList().forEach(player -> player.removeAllCards());
        game.start(deck);
        
        // Refresh the UI
        repaint();
    }
    
    /**
     * a method for enabling user interactions with the GUI.
     */
    @Override
    public void enable() {
        playButton.setEnabled(true);
        passButton.setEnabled(true);
        chatInput.setEnabled(true);
        bigTwoPanel.setEnabled(true);
    }
    
    /**
     * a method for disabling user interactions with the GUI.
     */
    @Override
    public void disable() {
        playButton.setEnabled(false);
        passButton.setEnabled(false);
        chatInput.setEnabled(false);
        bigTwoPanel.setEnabled(false);
    }
    
    /**
     * a method for prompting the active player to select cards and make his/her move. 
     * A message should be displayed in the message area showing it is the active player’s turn.
     */
    @Override
    public void promptActivePlayer() {
        printMsg(game.getPlayerList().get(activePlayer).getName() + "'s turn:");
    }
    
    private int[] getSelected() {
        int count = 0;
        for (boolean b : selected) if (b) count++;
        
        int[] selectedCards = new int[count];
        int idx = 0;
        for (int i = 0; i < selected.length; i++) {
            if (selected[i]) {
                selectedCards[idx++] = i;
            }
        }
        return selectedCards;
    }

    private class BigTwoPanel extends JPanel {
        private static final int CARD_WIDTH = 73;
        private static final int CARD_HEIGHT = 97;
        private static final int CARD_OVERLAP = 20;
        private static final int CARD_X = 120;
        private static final int CARD_Y = 30;
        
        public BigTwoPanel() {
            addMouseListener(new panelMouseAdapter());
        }

        private class panelMouseAdapter extends MouseAdapter {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (activePlayer >= 0) {
                    int x = e.getX();
                    int y = e.getY();
                    int playerY = activePlayer * (CARD_HEIGHT + CARD_Y) + CARD_Y;
                    
                    // Check if click is in active player's row
                    if (y >= playerY && y <= playerY + CARD_HEIGHT) {
                        CardList cards = game.getPlayerList().get(activePlayer).getCardsInHand();
                        // Check cards from right to left for better overlap handling
                        for (int i = cards.size() - 1; i >= 0; i--) {
                            int cardX = CARD_X + (i * CARD_OVERLAP);
                            if (x >= cardX && x <= cardX + CARD_WIDTH) {
                                selected[i] = !selected[i];
                                repaint();
                                break;
                            }
                        }
                    }
                }
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            // Draw players' cards
            for (int i = 0; i < game.getPlayerList().size(); i++) {
                int y = i * (CARD_HEIGHT + CARD_Y);
                
                // Draw player name and avatar
                g.drawString(game.getPlayerList().get(i).getName(), 10, y + 20);
                Image icon = new ImageIcon("icons/" + i + ".jpg").getImage();
                g.drawImage(icon, 10, y + CARD_Y, 90, 90, null);
                
                // Draw cards
                int x = CARD_X;
                CardList cards = game.getPlayerList().get(i).getCardsInHand();
                
                for (int j = 0; j < cards.size(); j++) {
                    int cardY = y + CARD_Y;
                    if (i == activePlayer && selected[j]) {
                        cardY -= 20; // Raise selected cards
                    }
                    
                    if (i == activePlayer) {
                        drawCard(g, cards.getCard(j), x + (j * CARD_OVERLAP), cardY);
                    } else {
                        drawCardBack(g, x + (j * CARD_OVERLAP), cardY);
                    }
                }
            }
            
            // Draw last hand on table
            if (!game.getHandsOnTable().isEmpty()) {
                Hand lastHand = game.getHandsOnTable().get(game.getHandsOnTable().size() - 1);
                int tableY = 4 * (CARD_HEIGHT + CARD_Y);
                g.drawString("Last played by: " + lastHand.getPlayer().getName(), 10, tableY + CARD_Y);
                
                int x = CARD_X;
                for (int i = 0; i < lastHand.size(); i++) {
                    drawCard(g, lastHand.getCard(i), x + (i * CARD_OVERLAP), tableY + CARD_Y + 10);
                }
            }
        }
        
        private void drawCard(Graphics g, Card card, int x, int y) {
            Image cardImage = new ImageIcon("cards/" + card.getSuit() + card.getRank() + ".gif").getImage();
            g.drawImage(cardImage, x, y, CARD_WIDTH, CARD_HEIGHT, null);
        }
        
        private void drawCardBack(Graphics g, int x, int y) {
            Image back = new ImageIcon("cards/back.gif").getImage();
            g.drawImage(back, x, y, CARD_WIDTH, CARD_HEIGHT, null);
        }
    }

    private class PlayButtonListner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            if (getSelected().length > 0) {
                game.makeMove(activePlayer, getSelected());
                Arrays.fill(selected, false);
            }
        }
    }

    private class PassButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            game.makeMove(activePlayer, null);
            Arrays.fill(selected, false);
        }
    }
    
    private class RestartMenuItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            reset();
        }
    }

    private class QuitMenuItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}