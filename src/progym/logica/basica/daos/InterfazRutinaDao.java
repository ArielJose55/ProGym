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
interface InterfazRutinaDao {
    
    public ArrayList<Rutina> listarAllRutinas() throws SQLException;
    
    public void addRutina(Rutina rutina) throws SQLException;
    
    public Rutina getRutina(int codigo) throws SQLException;
    
    public void editRutina(Rutina codigo) throws SQLException;
    
    public void removeRutina(int codigo) throws SQLException;
    
    public int getRutinaCount();
    
    public ArrayList<Rutina> getRutinaName(String nombre) throws SQLException;
    
    public ArrayList<Rutina> getRutinaRango(int fistCodigo,int endCodigo) throws SQLException;
}
