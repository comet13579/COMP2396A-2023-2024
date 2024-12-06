
import java.io.*;
import java.net.*;

/**
 * The BigTwoClient class implements the NetworkGame interface. It is used to model a Big
 * Two game client that is responsible for establishing a connection and communicating with
 * the Big Two game server.
 * 
 * @author Sonny Wong
 */
public class BigTwoClient implements NetworkGame{
    private final BigTwo game;
    private final BigTwoGUI gui;
    private Socket sock;
    private ObjectOutputStream oos;
    private int playerID;
    private String playerName;
    private String serverIP;
    private int serverPort;

    /**
     * a constructor for creating a Big Two client.
     * @param game BigTwo object
     * @param gui BigTwoGUI object
     */
    public BigTwoClient(BigTwo game, BigTwoGUI gui){
        this.game = game;
        this.gui = gui;
    }

    /**
     * a method for getting the playerID (i.e., index) of the local player.
     * @return index of the player
     */
    @Override
    public int getPlayerID() {
        return this.playerID;
    }

    /**
     * a method for setting the playerID (i.e., index) of
     * the local player. This method should be called from the parseMessage() method when a
     * message of the type PLAYER_LIST is received from the game server. 
     * @param playerID index of the player
     */
    @Override
    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    /**
     * a method for getting the name of the local player.
     * @return name of the player
     */
    @Override
    public String getPlayerName() {
        return this.playerName;
    }

    /**
     * a method for setting the name of the local player. 
     * @param playerName name of the local player
     */
    @Override
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * a method for getting the IP address of the game server.
     * @return IP address of the game server
     */
    @Override
    public String getServerIP() {
        return this.serverIP;
    }

    /**
     * a method for setting the IP address of the game server.
     * @param serverIP IP address of the game server
     */
    @Override
    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    /**
     * a method for getting the TCP port of the game server
     * @return TCP port of the game server
     */
    @Override
    public int getServerPort() {
        return this.serverPort;
    }

    /**
     * a method for setting the TCP port of the game server.
     * @param serverPort TCP port of the game server
     */
    @Override
    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    /**
     * a method for making a socket connection with the game server.
     * Upon successful connection, you should (i) create an ObjectOutputStream for sending
     * messages to the game server; (ii) create a new thread for receiving messages from the
     * game server.
     */
    @Override
    public void connect() {
        try {
            // when press this button again it will bug
            this.sock = new Socket(this.serverIP, this.serverPort);
            this.oos = new ObjectOutputStream(sock.getOutputStream());
            sendMessage(new CardGameMessage(-1,CardGameMessage.JOIN,""));
            Thread thread = new Thread(new ServerHandler());
            thread.start();
            this.gui.disableConnect();
        } catch(IOException ex) {
            gui.printMsg("Error connecting to server!");
	 	}
        
    }

    /**
     * a method for parsing the messages
     * received from the game server. This method should be called from the thread
     * responsible for receiving messages from the game server. Based on the message type,
     * different actions will be carried out
     * @param message message received from the game server
     */
    @Override
    public void parseMessage(GameMessage message) {
        switch (message.getType()) {
            case CardGameMessage.PLAYER_LIST -> {
                this.game.updatePlayerNames((String[]) message.getData());
                this.playerID = message.getPlayerID();
                this.gui.printMsg("Connected to the server successfully!");
                sendMessage(new CardGameMessage(CardGameMessage.JOIN,-1,this.playerName));
            }

            case CardGameMessage.JOIN -> {
                this.game.addPlayerNames((String) message.getData(), message.getPlayerID());
                if (message.getPlayerID() == this.playerID) {
                    sendMessage(new CardGameMessage(CardGameMessage.READY,-1,null));
                    this.gui.printMsg("You are " + (String) message.getData() + ".");
                } else {
                    this.gui.printMsg( (String) message.getData() + " has joined the game.");
                }
                gui.repaint();
            }
            case CardGameMessage.FULL -> this.gui.printMsg("Server is full. Please try again later or reconnect to another server.");

            case CardGameMessage.QUIT -> {
                this.gui.printMsg(this.game.getPlayerList().get(message.getPlayerID()).getName() + " has left the game.");
                this.playerName = "";
                this.game.addPlayerNames("", message.getPlayerID());
                gui.repaint();
                if (this.game.getPlayerCount() == 4){
                    gui.disable();
                    sendMessage(new CardGameMessage(CardGameMessage.READY, -1, null));
                    this.gui.printMsg("Game stopped, waiting for new players to restart");
                }
            }
            case CardGameMessage.READY -> this.gui.printMsg("Player " + this.game.getPlayerList().get(message.getPlayerID()).getName() + " is ready.");
            
            case CardGameMessage.START -> {
                this.game.start((BigTwoDeck) message.getData());
                this.gui.printMsg("Game started.");
            }
            case CardGameMessage.MOVE -> this.game.checkMove(message.getPlayerID(), (int[]) message.getData());
            
            case CardGameMessage.MSG -> this.gui.appendChatArea((String) message.getData());
        }
    }

    /**
     * a method for sending the specified
     * message to the game server. This method should be called whenever the client wants to
     * communicate with the game server or other clients. 
     * @param message message to be sent to the game server
     */
    @Override
    public void sendMessage(GameMessage message) {
        try {
            this.oos.writeObject(message);
        } catch (IOException e) {
            this.gui.printMsg("Error in sending message to server.");
        }
    }
    /**
     * an inner class that implements the Runnable interface. You
     * should implement the run() method from the Runnable interface and create a thread
     * with an instance of this class as its job in the connect() method from the NetworkGame
     * interface for receiving messages from the game server. Upon receiving a message, the
     * parseMessage() method from the NetworkGame interface should be called to parse the
     * messages accordingly.
     */
    private class ServerHandler implements Runnable {
        private ObjectInputStream reader = null;
        public ServerHandler() {
			try {
				this.reader = new ObjectInputStream(sock.getInputStream());
			} catch (IOException ex) {
                gui.printMsg("Error in connecting to the server, disconnecting...");
			}
		} 

        @Override
        public void run() {
            GameMessage message;
            try {
                while ((message = (CardGameMessage) reader.readObject()) != null) {
                    parseMessage(message);
                }
            }
            catch (IOException | ClassNotFoundException ex) {
                gui.printMsg("Error in receiving messages from the server, disconnecting...");
            }
        }    
    }
}