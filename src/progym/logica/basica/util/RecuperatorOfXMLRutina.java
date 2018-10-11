/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.logica.basica.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Klac
 */
public class RecuperatorOfXMLRutina {

    private final ArrayList<String> lista=new ArrayList<>();
    
    public RecuperatorOfXMLRutina() throws Exception {
        SAXParserFactory sf=SAXParserFactory.newInstance();
        SAXParser sp=sf.newSAXParser();
        sp.parse(new FileInputStream(new File("RepositoryRutinas.xml")), new HojaXML());
    }
    
    public ArrayList<String> getAllRutinas(){
        return lista;
    }
    
    private class HojaXML extends DefaultHandler{

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            for (int i = 0; i < attributes.getLength(); i++) {
                lista.add(attributes.getValue(i));
            }
        }
        
    }
}


