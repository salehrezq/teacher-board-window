/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painter;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author S
 */
public class DialogClientsControl extends JDialog {

    private final ServerBroadcast server;
    private final JPanel panel_main;
    private final JScrollPane scrollPane;
    private final DataClients dataClients;

    public DialogClientsControl(JFrame parentFrame, String title, ServerBroadcast serverBroadcast) {
        super(parentFrame, title, false);
        this.setSize(new Dimension(495, 470));
        
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent event) {
                setSize(495, 470);
            }
        });

        server = serverBroadcast;
        dataClients = server.getDataClients();

        scrollPane = new JScrollPane(dataClients.getClientsTable());

        panel_main = new JPanel();
        panel_main.add(scrollPane);

        this.add(panel_main);

        this.setLocationRelativeTo(parentFrame);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.setVisible(true);
    }

}
