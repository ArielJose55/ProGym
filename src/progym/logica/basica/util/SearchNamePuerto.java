/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.logica.basica.util;

import gnu.io.CommPortIdentifier;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 *
 * @author Ariel Jose Arnedo
 */
public class SearchNamePuerto {

    private SearchNamePuerto() {
        listaPort = CommPortIdentifier.getPortIdentifiers();
    }
    
    public static SearchNamePuerto getSearchNamePuerto(){
        return new SearchNamePuerto();
    }
    
    public ArrayList<String> getListaPorts(){
        ArrayList<String> lista=new ArrayList<>();
        CommPortIdentifier port;
        while(listaPort.hasMoreElements()){
            port=(CommPortIdentifier)listaPort.nextElement();
            if(port.getPortType()==CommPortIdentifier.PORT_SERIAL){
                lista.add(port.getName());
            }
        
        }
        return lista;
    }
    
    
    private Enumeration<Object> listaPort;
}
