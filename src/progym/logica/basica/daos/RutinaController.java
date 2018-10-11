/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.logica.basica.daos;

import java.sql.SQLException;
import java.util.ArrayList;
import progym.logica.basica.entidades.Rutina;

/**
 *
 * @author Klac
 */
public class RutinaController {
    private RutinaDao rutinadao;

    public RutinaController() {
        rutinadao= new RutinaDao();
    }
    
    public ArrayList<Rutina> getListaRutinas() throws SQLException{
        return rutinadao.listarAllRutinas();
    }
    
    public void añadirRutina(Rutina rutina) throws SQLException{
        rutinadao.addRutina(rutina);
    }
    
    public Rutina getRutina(int codigo) throws SQLException{
        return rutinadao.getRutina(codigo);
    }
    
    public void editarRutina(Rutina rutina) throws SQLException{
        rutinadao.editRutina(rutina);
    }
    
    public void removeRutina(int codRutina) throws SQLException{
        rutinadao.removeRutina(codRutina);
    }
    
    public int getCantidadAllRutinas(){
        return rutinadao.getRutinaCount();
    }
    
    public ArrayList<Rutina> getRutinaNombre(String nombre) throws SQLException{
        return rutinadao.getRutinaName(nombre);
    }
    
    public ArrayList<Rutina> getRutinasRango(int fistCodigo,int endCodigo) throws SQLException{
        return rutinadao.getRutinaRango(fistCodigo, endCodigo);
    }
    
}
