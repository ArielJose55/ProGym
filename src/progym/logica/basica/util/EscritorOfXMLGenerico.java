/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.logica.basica.util;
import java.io.FileWriter;
import java.io.File;
import org.jdom2.Document;
import org.jdom2.Element;
/**
 *
 * @author Klac
 */
public class EscritorOfXMLGenerico {

    public EscritorOfXMLGenerico() {
    }
    
    private void iniciarWriter(){
        Element elemento=new Element("rootMusic");
        Document documento=new Document(elemento);
        documento.setRootElement(elemento);
        
        
    }
}
