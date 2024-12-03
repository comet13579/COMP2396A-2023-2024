
import java.io.*;
import java.net.*;

public class BigTwoClient {
    private BigTwo game;
    private BigTwoGUI gui;
    private Socket sock;
    private ObjectOutputStream oos;
    private int playerID;
    private String playerName;
    private String serverIP;
    private int serverPort;

    private void disconnect(){
        try{
            this.oos.close();
            this.sock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BigTwoClient(BigTwo game, BigTwoGUI gui){
        this.game = game;
        this.gui = gui;
    }

    public int getPlayerID() {
        return this.playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public String getServerIP() {
        return this.serverIP;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    public int getServerPort() {
        return this.serverPort;
    }

    public void connect() {
        try {
            this.sock = new Socket(this.serverIP, this.serverPort);
            this.oos = new ObjectOutputStream(sock.getOutputStream());
            sendMessage(new CardGameMessage(-1,CardGameMessage.JOIN,""));
            Thread thread = new Thread(new ServerHandler());
            thread.start();
        } catch(IOException ex) {
            gui.printMsg("Error connecting to server!");
			ex.printStackTrace();
	 	}
    }

    public void parseMessage(GameMessage message) {
        switch (message.getType()) {
            case CardGameMessage.PLAYER_LIST:
                this.game.updatePlayerNames((String[]) message.getData());
                this.sendMessage(new CardGameMessage(CardGameMessage.JOIN,-1,-1));
                this.gui.printMsg("Connected to the server successfully!");
                break;
            case CardGameMessage.JOIN:
                this.game.addPlayerNames((String) message.getData(), message.getPlayerID());
                if (message.getPlayerID() == this.playerID) {
                    sendMessage(new GameMessage(CardGameMessage.READY,-1,null));
                    this.gui.printMsg("You joined the game.");
                } else {
                    this.gui.printMsg("Player " + message.getPlayerID() + " joined the game.");
                }
                break;
            case CardGameMessage.FULL:
                this.gui.printMsg("Server is full. Please try again later or reconnect to another server.");
                break;
            case CardGameMessage.QUIT:
                this.playerName = "";
                this.game.addPlayerNames("", message.getPlayerID());
                break;
                
            case CardGameMessage.READY:
                this.gui.printMsg("Player " + message.getPlayerID() + " is ready.");
                break;
            case CardGameMessage.START:
                this.game.start((BigTwoDeck) message.getData());
                this.gui.printMsg("Game is starting.");
                break;
            case CardGameMessage.MOVE:
                this.game.checkMove(message.getPlayerID(), (int[]) message.getData());
            case CardGameMessage.MSG:
                this.gui.appendChatArea((String) message.getData());
            default:
                break;
        }
    }

    public void sendMessage(GameMessage message) {
        try {
            this.oos.writeObject(message);
        } catch (Exception e) {
            this.gui.printMsg("Error in sending message to server.");
        }
    }

    private class ServerHandler implements Runnable {
        private ObjectInputStream reader = null;
        public ServerHandler() {
			try {
				this.reader = new ObjectInputStream(sock.getInputStream());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} 

        public void run() {
            GameMessage message;
            try {
                while ((message = (CardGameMessage)reader.readObject()) != null) {
                    parseMessage(message);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
                gui.printMsg("Error in receiving messages from the server, disconnecting...");
                disconnect();
            }
        }
    }
}