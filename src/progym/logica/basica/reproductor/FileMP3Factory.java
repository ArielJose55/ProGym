/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.logica.basica.reproductor;




/**
 *
 * @author Ariel Jose Arnedo
 */
public class FileMP3Factory {

    private static final java.util.ArrayList<Mp3Cancion> listaWriteMp3 = new java.util.ArrayList<>();
    private static EscritorXMLReproductor escritorXML;


    public static java.util.ArrayList<Mp3Cancion> getFileMP3S() {
        final java.util.ArrayList<Mp3Cancion> mp3s = new java.util.ArrayList<>();
        try {
            
            for(String file:LectorXMLReproductor.getLectorXMLReproductor().getPathMusic()){ 
                mp3s.add(new Mp3Cancion(file));
                FileMP3Factory.listaWriteMp3.add(new Mp3Cancion(file));
            }
            escritorXML = new EscritorXMLReproductor();
        }finally{
            return mp3s;
        }
        
    }
    
    public static java.util.ArrayList<Mp3Cancion> getListaWriteMp3(){
        return FileMP3Factory.listaWriteMp3;
    }

    public static void setFileMP3S(java.io.File[] mp3s) {
       for(int i = 0 ; i < mp3s.length ; i++){
           boolean noIguales = true;
           int index = listaWriteMp3.size();
           for(int j = 0; j < index  ; j++){
               if(mp3s[i].getName().compareTo(listaWriteMp3.get(j).getNombre()) == 0){
                   noIguales = false;
               }
           }
           if(noIguales){
               FileMP3Factory.listaWriteMp3.add(new Mp3Cancion(mp3s[i].getAbsolutePath().replace('\\', '/')));
           }
       }
       
    }
    
    
}
