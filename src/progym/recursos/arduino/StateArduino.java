/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.recursos.arduino;

import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author Ariel Jose Arnedo
 */
public interface StateArduino {
    public String ARDUINO_CONECTADO  =  "Arduino Conectado";
    public Font FONT_ARDUINO_CONECTADO  =  new Font("Tahoma", 1, 12);
    public Color COLOR_ARDUINO_CONECTADO  =  new Color(50, 150, 0);
    
    public String ARDUINO_NO_CONECTADO  =  "Arduino No Conectado";
    public Font FONT_ARDUINO_NO_CONECTADO  =  new Font("Tahoma", 1, 12);
    public Color COLOR_ARDUINO_NO_CONECTADO  =  new Color(150, 0, 0);
}
