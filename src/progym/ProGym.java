/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import progym.logica.basica.util.threads.ThreadSetRutina;
import progym.vista.dialogos.ThreadOpenApplicationWindow;
import progym.vista.ventanas.VentanaPrincipal;

/**
*
* @author Ariel Jose Arnedo
*/
public class ProGym {


    public static void cargaSubstance(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        org.jvnet.substance.SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.BusinessBlueSteelSkin");
        org.jvnet.substance.SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceOliveTheme");
    }
    public static void main(String[] args) throws Exception{
        cargaSubstance();

        final ThreadOpenApplicationWindow windowOpened = new ThreadOpenApplicationWindow();
        try {
            Thread.sleep(1300L);
            ThreadSetRutina hilo=new ThreadSetRutina();
            hilo.comienza(); 
            hilo.join();
        }catch (RuntimeException ex){
            //if(ex.getMessage().split("#")[1].compareTo("-1")==0){//JOptionPane.showMessageDialog(null,"No hay conexion con la base de datos.\n"
              //      + "Por favor ingrese la rutina manualmente","Error de Conexion",JOptionPane.WARNING_MESSAGE);}
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Error para establer la Rutina para el dia de hoy\n"
                    + "por favor, ingresela manualmente");
        }
       
                
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                new VentanaPrincipal().setVisible(true);
            }
        });
        
        
    }
}
