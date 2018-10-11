/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.logica.modelos;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Klac
 */
public class TableCeldaRenderer extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(column == 4){
            
        }
        return this;
    }   
}
