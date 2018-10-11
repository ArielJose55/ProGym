/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.logica.modelos;

import java.awt.Component;
import java.util.Enumeration;
import java.util.ResourceBundle;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JList;


/**
 *
 * @author Ariel Jose Arnedo
 */
public class ListaCeldaRenderer extends DefaultListCellRenderer{

    private final ResourceBundle rb=ResourceBundle.getBundle("progym/controlador/URLImagenes");
    public ListaCeldaRenderer(){
        image=new ImageIcon(getClass().getResource(rb.getString("imageCellRenderer")));
    }
    
    @Override
    public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component compo;
        compo= super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
        if(isSelected){
            setIcon(image);
        }
        return compo;
    }
    private ImageIcon image;
}