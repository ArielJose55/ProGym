/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import progym.logica.basica.util.threads.ThreadRunArduino;

/**
 *
 * La clase <code>CambioSeleccionListener</code>
 * 
 * @author Ariel Jose Arnedo
 */
public class CambioSeleccionListener implements ActionListener{

    /**
     * 
     * @param etiqueta
     * @param runauxAduino 
     */
    public CambioSeleccionListener(JLabel etiqueta, ThreadRunArduino runauxAduino) {
        this.etiqueta = etiqueta;
        this.runauxAduino = runauxAduino;
    }

    
    /**
     * 
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e){
        JCheckBoxMenuItem itemAux=(JCheckBoxMenuItem)e.getSource();
        if(itemAux.isSelected()){

            runauxAduino.setPuerto(itemAux.getText());
            if(runauxAduino.isIsconectado()){
                runauxAduino.setIsConectado(false);
            }else{
                runauxAduino.despertar();
            }
            actualizaEtiqueta();
        }
    }
    /**
     * 
     */
    private void actualizaEtiqueta(){
        etiqueta.setText(runauxAduino.getEtiMuestraConexion().getText());
        etiqueta.setFont(runauxAduino.getEtiMuestraConexion().getFont());
        etiqueta.setForeground(runauxAduino.getEtiMuestraConexion().getForeground());
        etiqueta.repaint();
    }

    private JLabel etiqueta;
    private ThreadRunArduino runauxAduino;
}
