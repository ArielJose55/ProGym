/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.logica.basica.reproductor;

import java.io.File;
import java.util.ArrayList;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author Klac
 */
public class LectorXMLReproductor {

    public LectorXMLReproductor() throws Exception{
        rutas=new ArrayList<>();
        iniciaReader();
    }
    
    private  void iniciaReader() throws Exception{
        SAXBuilder lector=new SAXBuilder();
        Document documento = lector.build(new File("pathReproductor.xml"));
        Element root=documento.getRootElement();
        
        for(Element ele:root.getChildren()){
            for(Element ele2:ele.getChildren()){
                rutas.add(ele2.getValue());
            }
        }
    }
    
    public static LectorXMLReproductor getLectorXMLReproductor() throws Exception{
        return new LectorXMLReproductor();
    }
    
    public ArrayList<String> getPathMusic(){
        return rutas;
    }
    
    public String getRutaParent(){
        return (new File(rutas.get(0))).getParent();
    }
    
    private ArrayList<String> rutas;
}
