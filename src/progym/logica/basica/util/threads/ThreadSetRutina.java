/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.logica.basica.util.threads;

import java.sql.SQLException;
import progym.logica.basica.daos.RutinaController;
import progym.logica.basica.entidades.Rutina;
import progym.logica.basica.util.Fecha;
import progym.logica.basica.util.IdentifyRutina;


/**
 *
 * @author Ariel Jose Arnedo
 */
public class ThreadSetRutina extends Thread{
    
    private final Rutina rutina=new Rutina(Fecha.getCodFechaRutina());
    private IdentifyRutina idRutina;
    
    public ThreadSetRutina() throws Exception{
        idRutina = IdentifyRutina.getIdentitifyRutina();
    }
//    23000
    public void comienza() throws RuntimeException{
        this.run();
    }
    
    @Override
    public void run()throws RuntimeException{
        try {
            rutina.setNombre(idRutina.getRutinaActual());
            RutinaController rc=new RutinaController();
            rc.añadirRutina(rutina);
        } catch (SQLException ex) {
            String cause="#-1";
            if(ex.getMessage().split(" ")[0].compareToIgnoreCase("duplicate")==0) {cause="#1";}
            throw new RuntimeException(ex.getMessage()+cause);
        }
    }
    
}
