/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.logica.basica.reproductor;

import java.io.File;
import java.io.FileOutputStream;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author Klac
 */
public class EscritorXMLReproductor {
    
    private Document documento;
    private  FileOutputStream salidaFormatic;
    private XMLOutputter salida = null;
    private Element tagElement;
    
    public EscritorXMLReproductor() throws Exception{
        Element elemento = new Element("rootMusic");
        documento = new Document(elemento);
        salida = new XMLOutputter(Format.getPrettyFormat());
        tagElement = new Element("music");
        documento.getRootElement().addContent(tagElement);        
    }
    
    {
        Runtime.getRuntime().addShutdownHook(new EscribeArchivoXMLThread());
    }
    
    
    private final class EscribeArchivoXMLThread extends Thread{
        @Override
        public void run(){
            try{
                File file = new File("pathReproductor.xml");
                if(!file.exists()){
                    file.createNewFile();
                }
                salidaFormatic = new FileOutputStream(file);
                for(Mp3Cancion cancion:FileMP3Factory.getListaWriteMp3()){
                    tagElement.addContent(new Element("musicPath").setText(cancion.getRutaAbsoluta()));
                }
                salida.output(documento, salidaFormatic);
            }finally{
                return;
            }
        }
    }
}

