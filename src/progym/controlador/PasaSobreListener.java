/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;

/**
 *
 * @author Klac
 */
public class PasaSobreListener extends MouseAdapter{
    
    private final ResourceBundle rb=ResourceBundle.getBundle("progym/controlador/URLImagenes");
    public final ImageIcon[] image=new ImageIcon[2];
    
    public PasaSobreListener(String urlON){
        image[0]=new ImageIcon(getClass().getResource(rb.getString(urlON)));
        image[1]=new ImageIcon(getClass().getResource(rb.getString(urlON+"OFF")));
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        AbstractButton boton=(AbstractButton)e.getSource();
        boton.setIcon(image[1]);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        AbstractButton boton=(AbstractButton)e.getSource();
        boton.setIcon(image[0]);
    }
}
