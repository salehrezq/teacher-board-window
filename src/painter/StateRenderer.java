/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painter;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author S
 */
public class StateRenderer extends JLabel implements TableCellRenderer {

    Color green;
    Color red;
    public StateRenderer() {
        green = Color.green;
        red = new Color(255,80,80);
        setOpaque(true);
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        boolean state = Boolean.parseBoolean(String.valueOf(value));

        if (state) {
            this.setBackground(green);
        } else {
            this.setBackground(red);
        }
        return this;
    }
}
