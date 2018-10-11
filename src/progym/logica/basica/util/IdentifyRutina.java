/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.logica.basica.util;

import java.util.ArrayList;



/**
 *
 * @author Ariel Jose Arnedo
 */
public class IdentifyRutina {
    /**
     * 
     */
    private final int fecha=Fecha.getCodFechaRutina();
    
   
    /**
     * 
     * @throws Exception 
     */
    private IdentifyRutina() throws Exception {
        rutinas=new RecuperatorOfXMLRutina().getAllRutinas();
    }
    
    /**
     * 
     * @return
     * @throws Exception 
     */
    public static IdentifyRutina getIdentitifyRutina() throws Exception{
        return new IdentifyRutina();
    }
    /**
     * 
     * @return 
     */
    public String getRutinaActual(){
        return rutinas.get(fecha%rutinas.size());
    }
    
    /**
     * 
     * @return 
     */
    public int getCodFechaActual(){
        return fecha;
    }
    
    /**
     * 
     * @return 
     */
    public String getNameFechaActual(){
        return Fecha.getNameFechaRutina(fecha);
    }
    
    /**
     * 
     * @return 
     */
    public ArrayList<String> getNameRutinas(){
        return rutinas;
    }
    
    /**
     * 
     */
    private ArrayList<String> rutinas;
}
