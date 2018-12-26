/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painter;

import java.awt.Color;
import java.awt.Point;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedHashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author S
 */
public class ServerBroadcast {

    private static final int PORT = 48317;
    private Thread instanceConnection;
    private LinkedHashSet<ServerManager> connectedClients;
    private LinkedHashSet<String> client_addresses;

    private Thread serverThread;
    private Painter painter;
    private DataClients dataClients;

    public ServerBroadcast() {
        connectedClients = new LinkedHashSet<>();
        painter = new Painter(this);
        painter.createPainterGUI(painter);
        client_addresses = new LinkedHashSet();

        dataClients = new DataClients();

        serverThread = new Thread(acceptingClients);
        serverThread.start();
    }

    public DataClients getDataClients() {
        return this.dataClients;
    }

    public void broadcast_mousePressed(Object drawObj) {
        for (ServerManager connectedClient : connectedClients) {
            connectedClient.send_drawData_mousePressed(drawObj);
        }
    }

    public void broadcast_mouseDragged(Point p) {
        for (ServerManager connectedClient : connectedClients) {
            connectedClient.send_point(p);
        }
    }

    public void broadcast_mouseReleased(Object mouseReleased_Data) {
        for (ServerManager connectedClient : connectedClients) {
            connectedClient.send_mouseReleased(mouseReleased_Data);
        }
    }

    public void broadcast_newPage_command() {
        for (ServerManager connectedClient : connectedClients) {
            connectedClient.send_newPage_command("newAll");
        }
    }

    public void broadcast_undo_command() {
        for (ServerManager connectedClient : connectedClients) {
            connectedClient.send_undo_command();
        }
    }

    public void broadcast_redo_command() {
        for (ServerManager connectedClient : connectedClients) {
            connectedClient.send_redo_command();
        }
    }

    public void broadcast_background(Color background) {
        for (ServerManager connectedClient : connectedClients) {
            connectedClient.send_background(background);
        }
    }

    public void broadcast_maximize_command(String maximize) {
        for (ServerManager connectedClient : connectedClients) {
            connectedClient.send_maximize_command(maximize);
        }
    }

    public synchronized int addClientAddress(String fullAddress) {

        int row = -1;

        if (client_addresses.add(fullAddress)) {
            //If added OK to client_addresses
            // Then it is OK to add it to dataClients
            row = dataClients.addClient(fullAddress);
            painter.seFrametTitle(client_addresses.size());
        }
        return row;
    }

    public LinkedHashSet<String> getClintsFullAddresses() {
        return this.client_addresses;
    }

    public synchronized void updateCell(Object value, int row, int col) {
        dataClients.updateCell(value, row, col);
    }

    Runnable acceptingClients = new Runnable() {
        @Override
        public void run() {
            try {
                ServerSocket serverSocket = new ServerSocket(PORT);
                while (true) {
                    Socket threadedSocket = serverSocket.accept();
                    ServerManager instanceManager = new ServerManager(threadedSocket, painter, ServerBroadcast.this);
                    connectedClients.add(instanceManager);
                    instanceConnection = new Thread(instanceManager);
                    instanceConnection.start();
                }
            } catch (IOException ex) {
                Logger.getLogger(ServerBroadcast.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    public void removeServerInstance_dueTo_ClientExit(ServerManager serverManager) {
        // Every serverManager has a variable stores its corressponding client address.
        // 1- Kill receiving thread
        // 2- Remove client_addresses of current serverManager thread.
        // 3- Remove serverManager thread itself from connectedClients collection.
        // 4- Update table model to reflect this removal fact.
        // 5- Set current serverManager object to null.

       
        serverManager.killRunningReceivingThread();

        if (client_addresses.remove(serverManager.getClientIP()));
        {
            dataClients.removeClient(serverManager.getClientRow());
             painter.seFrametTitle(client_addresses.size());
        }

        connectedClients.remove(serverManager);

        serverManager = null;
    }

    public void removeServerInstance_dueTo_ClientDuplicate(ServerManager serverManager) {

        serverManager.killRunningReceivingThread();
        connectedClients.remove(serverManager);
        serverManager = null;
    }

    public static void main(String[] args) {
        ServerBroadcast serverBroadcast = new ServerBroadcast();
    }

}
