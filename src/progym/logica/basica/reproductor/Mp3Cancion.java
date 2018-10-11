/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.logica.basica.reproductor;

import java.io.File;
import java.io.Serializable;


/**
 *
 * @author Ariel Jose Arnedo
 */
public class Mp3Cancion implements Comparable,Serializable{
    
    public Mp3Cancion(String file) {
        nombre=new File(file).getName();
        ruta= file;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public String getRutaAbsoluta(){
        return ruta;
    }

    @Override
    public int compareTo(Object o) {
        Mp3Cancion cancion = (Mp3Cancion)o;
        String nombreObjeto = cancion.getNombre().toLowerCase();
        String nombreThis = this.getNombre().toLowerCase();

        return( nombreThis.compareTo( nombreObjeto ) );
    }
    
    private String ruta;
    private String nombre;
}
