/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.logica.basica.reproductor;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.media.Codec;
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.Format;
import javax.media.Manager;
import javax.media.Player;
import javax.media.PlugInManager;
import javax.media.format.AudioFormat;
import progym.logica.basica.util.Notificador;

/**
 *
 * @author Ariel Jose Arnedo
 */
public class ThreadReproductorMP3 implements ControllerListener,StateReproductor{
    
    private ArrayList<Mp3Cancion> fabricaMP3 = FileMP3Factory.getFileMP3S();
    
    private static int indexMusic = -1;
   
    public ThreadReproductorMP3() throws Exception{
        if(fabricaMP3 == null) throw new Exception("Lista de Reproducción Vacía");
        indexMusic = 0;
        estado = DETENIDO;
    }
    
    
    
    public void reproducirMP3() throws Exception{
        
        String attributeCodec = "net.sourceforge.jffmpeg.AudioDecoder";
        
        Codec attributeAudio = (Codec) Class.forName(attributeCodec).newInstance();
        
        PlugInManager.addPlugIn(attributeCodec, attributeAudio.getSupportedInputFormats(), new Format[]{new AudioFormat("LINEAR")}, PlugInManager.CODEC);
        selectMp3AtProducir();
        reproductor.addControllerListener(this);
    }
    
    private void selectMp3AtProducir() throws Exception{
        if(estado != REPRODUCIENDO){
            if(estado == DETENIDO){
                reproductor = Manager.createPlayer(getURLMP3(fabricaMP3.get(indexMusic).getRutaAbsoluta()));
                reproductor.start();
                estado = REPRODUCIENDO;
            }else if(estado == PAUSADO){
                continuar();
            }
        }
    }
    
    public void nextMp3() throws Exception{
        if(estado == REPRODUCIENDO){
            if(indexMusic < fabricaMP3.size()-1){
                indexMusic ++;
                stopReproductor();
                reproducirMP3();
            }
        }else if(estado == PAUSADO){
            if(indexMusic < fabricaMP3.size()-1){
                indexMusic ++;
                stopReproductor();
                reproducirMP3();
                pauseReproductor();
            }
        }else{
            if(indexMusic < fabricaMP3.size()-1){
                indexMusic ++;
            }
        }
    }
    
    public void previusMP3() throws Exception{
        if(estado == REPRODUCIENDO){
            if(indexMusic > 0){
                indexMusic --;
                stopReproductor();
                reproducirMP3();
            }
        }else if(estado == PAUSADO){
            if(indexMusic > 0){
                indexMusic --;
                stopReproductor();
                reproducirMP3();
                pauseReproductor();
            }
        }else{
            if(indexMusic > 0){
                indexMusic --;
            }
        }
    }    
    
    public void setFilesMP3s(File[] mp3s){
        if(fabricaMP3 == null){
            fabricaMP3 = new ArrayList<>();
        }
        FileMP3Factory.setFileMP3S(mp3s);
        
        fabricaMP3.addAll(FileMP3Factory.getListaWriteMp3());
    }

    public String getNombreMP3Running(){
        if(indexMusic >= 0 && indexMusic < fabricaMP3.size()){
            return fabricaMP3.get(indexMusic).getNombre();
        }else{
            return " ";
        }
    }
    
    public ArrayList<Mp3Cancion> getListMp3Repository(){
        return fabricaMP3;
    }
    
    public void pauseReproductor(){
        reproductor.stop();
        estado = PAUSADO;
    }
    
    private void stopReproductor(){
        if(!fabricaMP3.isEmpty()){
            reproductor.stop();
            reproductor.close();
            estado = DETENIDO;
        }
    }
    
    private void continuar(){
        if(estado == PAUSADO){
            reproductor.start();
            estado = REPRODUCIENDO;
        }
    }
    
    private int indexOfMP3(Mp3Cancion cancion){
        return  fabricaMP3.indexOf(cancion);
    }
    
    private URL getURLMP3(String ruta){
        try {
            return new File(ruta).toURI().toURL();
        } catch (MalformedURLException ex) {
           return null;
        }
    }
    
    @Override
    public synchronized void controllerUpdate(ControllerEvent evt) {
        if(evt instanceof EndOfMediaEvent){
            try {
                nextMp3();
                Notificador.getNotificador().notificar(fabricaMP3.get(indexMusic).getNombre());
            } catch (Exception ex) {}
        }
    }
   
    private byte estado;
    private boolean repetir=false;
    private Player reproductor; 
}
