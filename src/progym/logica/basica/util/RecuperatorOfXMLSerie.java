/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.logica.basica.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Klac
 */
public class RecuperatorOfXMLSerie {
    
    private static String rutinaHoy;
    private final ArrayList<String> listaSeriesHoy=new ArrayList<>();
    private final ArrayList<String> listaAllSeries=new ArrayList<>();
    
    static{
        try {
            rutinaHoy=IdentifyRutina.getIdentitifyRutina().getRutinaActual();
        } catch (Exception ex) {
            Logger.getLogger(RecuperatorOfXMLSerie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public RecuperatorOfXMLSerie()throws Exception{
        SAXParserFactory sf=SAXParserFactory.newInstance();
        SAXParser sp=sf.newSAXParser();
        sp.parse(new FileInputStream(("RepositorySeries.xml")), new HojaXML());
    }

    public ArrayList<String> getAllSeriesDeHoy(){
        return listaSeriesHoy;
    }
    
    public ArrayList<String> getAllRutinas(){
        return listaAllSeries;
    }
    
    
    private class HojaXML extends DefaultHandler{

        private int cantTotalSeries=-1;
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if(qName.compareTo("cantidadRutinas")==0){
                cantTotalSeries=Integer.parseInt(attributes.getValue(0));
            }
           for(int i=0;i<attributes.getLength();i++){
               if(attributes.getValue(i).compareTo(rutinaHoy)==0){
                   tipoRutina=qName.split("_")[1];
                   break;
               }
           }
           if(tipoRutina!=null){
               if(("serie_"+tipoRutina).compareTo(qName)==0){
                   for (int i = 0; i < attributes.getLength(); i++) {
                       listaSeriesHoy.add(attributes.getValue(i));
                   }
               }
           }
           if(cantTotalSeries!=-1){
                for(int i=0;i<attributes.getLength();i++){
                    for(int j=1;j<=cantTotalSeries;j++){
                        if(("serie_"+j).compareTo(qName)==0){
                            listaAllSeries.add(attributes.getValue(0));
                        }
                    }
                }
            }
        }

        
        private String tipoRutina=null;
    }
}
