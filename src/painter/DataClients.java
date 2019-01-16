/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painter;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DataClients {


    private DefaultTableModel tableModel;
    private final JTable tableClients;

    public DataClients() {
        tableClients = new JTable(modelSetUp());
        tableClients.getColumnModel().getColumn(2).setCellRenderer(new StateRenderer());
        tableClients.getColumnModel().getColumn(3).setCellRenderer(new StateRenderer());
        tableClients.getColumnModel().getColumn(4).setCellRenderer(new StateRenderer());
    }

    public DefaultTableModel modelSetUp() {

        tableModel = new DefaultTableModel(new String[]{"name", "IP", "Active", "Upfront", "FullScreen"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return tableModel;
    }

    public JTable getClientsTable() {

        return tableClients;
    }

    public int addClient(String fullAddress) {

        int row = tableClients.getRowCount();

        String[] addressSegments = Utility.extractClientAddressSegments(fullAddress);

        String name = addressSegments[0];
        String IP = addressSegments[1];
        //"name", "IP", "Active", "Upfront", "FullScreen"
        tableModel.insertRow(row, new Object[]{name, IP, true, true, false});

        return row;
    }

    public void removeClient(int row) {
        tableModel.removeRow(row);
    }

    public void updateCell(Object value, int row, int col) {
        tableModel.setValueAt(value, row, col);
    }

}
