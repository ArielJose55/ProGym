/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.vista.dialogos;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;


/**
 *
 * @author Klac
 */
public class ThreadOpenApplicationWindow extends JWindow {
    
    private final static ArrayList<String> PROCESOS = new ArrayList<>();
    
    private  ChildrenThread tareaBarra;
    
    static{
        PROCESOS.add("Iniciando Aplicación");
        PROCESOS.add("Actualizando Rutina de Hoy");
        PROCESOS.add("Cargando Modelos");
        PROCESOS.add("Iniciando Conexión con Arduino");
        PROCESOS.add("Iniciando Intefaz Grafica de Ususario");
    }
    
    public ThreadOpenApplicationWindow(){
        iniComponentes();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        tareaBarra = new ChildrenThread();
    }
    
  
    private void iniComponentes(){       
        
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new AbsoluteLayout());
 
        etiProgreso = new JLabel();
        barraProgreso = new JProgressBar(0,4);
        ImageIcon imagefondo =  new ImageIcon(getClass().getResource("/progym/recursos/imagenes/icon_WindowOpen.png"));
       
        AbsoluteConstraints conf1 = new AbsoluteConstraints(0, 0, 500, 300);
        AbsoluteConstraints conf2 = new AbsoluteConstraints(10, 235, -1, -1);
        AbsoluteConstraints conf3 = new AbsoluteConstraints(0, 255, 500, 8);
        
        panelCenter.add(etiProgreso,conf2);
        panelCenter.add(barraProgreso, conf3);
        for ( int i = 0; i < getCredicText().length ; i++){
            panelCenter.add(getCredicText()[i], new AbsoluteConstraints(10, (265 + (i*10)) ,-1,-1));
        }
        panelCenter.add(new JLabel(imagefondo),conf1);
        
        add(panelCenter, BorderLayout.CENTER);
        pack();
    }

    private JLabel[] getCredicText(){
        final ResourceBundle rb = ResourceBundle.getBundle("progym/vista/dialogos/TextoGenerico");
        JLabel eti[] = {new JLabel(),new JLabel(),new JLabel()};
        for(int i = 0 ; i < eti.length ; i++){
            eti[i].setFont(new Font("Arial", 2, 9));
            eti[i].setText(rb.getString("TextoCredito"+i));
        }
        return eti;
    }
    
    private final class ChildrenThread extends Thread{

        public ChildrenThread() {
            this.value = 0;
            empieza();
        }    
        
        @Override
        public void run(){
            while(true){
                detener();
                tareaEjecutar();
                espera();
            }
        }
        
        private void empieza(){
            this.setDaemon(true);
            this.start();
        }
        
        private void tareaEjecutar(){
            barraProgreso.setValue(value);
            if(value <= 4) etiProgreso.setText(PROCESOS.get(value));
            value++;
        }
        
        private synchronized void espera(){
            try{
                this.wait(500L);
            }finally{
                return;
            }
        }
        
        private void detener(){
            if(value >= 5){
                ThreadOpenApplicationWindow.this.setVisible(false);
                ThreadOpenApplicationWindow.this.dispose();
            }
        }
        
        private Integer value;
    }
   
    private JLabel etiProgreso;
    private JProgressBar barraProgreso;
}
