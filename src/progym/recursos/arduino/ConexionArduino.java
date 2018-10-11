/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.recursos.arduino;

import Arduino.Arduino;


/**
 *
 * @author Ariel Jose Arnedo
 */
public class ConexionArduino {
    /**
     * 
     */
    private static ConexionArduino conexion=null;
    
    /**
     * 
     */
    private ConexionArduino(){
        arduino=new Arduino();
        
    }
    
    /**
     * 
     * @return 
     */
    public static ConexionArduino getConexionArduino(){
        if(conexion==null){
            conexion=new ConexionArduino();
        }
        return conexion;
    }

    /**
     * 
     * @return 
     */
    public Arduino getArduino(){
        return arduino;
    }
    
    /**
     * 
     */
    private Arduino arduino;
}
