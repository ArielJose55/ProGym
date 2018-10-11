/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.logica.basica.util.threads;

import Arduino.Arduino;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import javax.swing.JLabel;
import progym.logica.basica.util.Notificador;
import progym.recursos.arduino.ConexionArduino;
import progym.recursos.arduino.StateArduino;

/**
 *
 * @author Ariel Jose Arnedo
 */
public class ThreadRunArduino extends Thread implements StateArduino{

    /**
     * 
     */
    private static JLabel eti=new JLabel();
    
    /**
     * 
     */
    private final SerialPortEventListener evento=new SerialPortEventListener() {

            @Override
            public void serialEvent(SerialPortEvent spe) {
                if(arduino.MessageAvailable()){
                    Notificador.getNotificador().notificar(Short.parseShort(arduino.PrintMessage()));
                }
            }
    };
    
    /**
     * 
     */
    public ThreadRunArduino(){
        this.setDaemon(true);
        arduino= ConexionArduino.getConexionArduino().getArduino();
        arduino.ShowMessageDialogs(false);
        puerto="COM7";
        empieza();
    }
  
    
    /**
     * 
     * @param puerto 
     */
    public ThreadRunArduino(String puerto) {
        this.setDaemon(true);
        arduino= ConexionArduino.getConexionArduino().getArduino();
        arduino.ShowMessageDialogs(false);
        this.puerto = puerto;
    }

    public void enviarMessage(String message) throws Exception{
        if(isconctado){
            arduino.SendData(message);
        }
    }
    
    private synchronized void empieza(){
        try{
            this.start();
        }finally{
            return;
        }
    } 
    
    /**
     * 
     * @return 
     */
    public String getPuerto() {
        return puerto;
    }

    /**
     * 
     * @param puerto 
     */
    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    /**
     * 
     * @return 
     */
    public JLabel getEtiMuestraConexion() {
        return eti;
    }

    /**
     * 
     * @return 
     */
    public boolean isIsconectado() {
        return isconctado;
    }
    /**
     * 
     * @param isconectado 
     */
    public void setIsConectado(boolean isconectado){
        isconctado=isconectado;
    }
    /**
     * 
     */
    public synchronized void despertar(){
        this.notify();
    }
    /**
     * 
     * @throws Exception 
     */
    public synchronized void desconectarArduino() throws Exception{
        arduino.KillArduinoConnection();
        parar();
    }
    
    /**
     * 
     */
    private synchronized void parar(){
        try{
            this.wait();
        }catch(InterruptedException e){}
    }
    
    /**
     * 
     */
    private void dormir(){
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {}
    }
    
    /**
     * 
     */
    @Override
    public void run(){
        while(true){
            if(!isconctado){
                try {
                    establecerConexion();
                    eti.setText(ARDUINO_CONECTADO);
                    eti.setFont(FONT_ARDUINO_CONECTADO);
                    eti.setForeground(COLOR_ARDUINO_CONECTADO);
                    isconctado=true;
                } catch (Exception ex) {
                    eti.setText(ARDUINO_NO_CONECTADO);
                    eti.setFont(FONT_ARDUINO_NO_CONECTADO);
                    eti.setForeground(COLOR_ARDUINO_NO_CONECTADO);
                    parar();
                    isconctado=false;
                }
            }
            dormir();
        }
        
    }
    
    /**
     * 
     * @throws Exception 
     */
    private void establecerConexion() throws Exception{
        arduino.ArduinoRXTX(puerto, 2000, 9600, evento);
    }
    
    private volatile boolean isconctado=false;
    public String puerto;
    private Arduino arduino;
}
