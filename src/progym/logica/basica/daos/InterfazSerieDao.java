/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.logica.basica.daos;

import java.sql.SQLException;
import java.util.ArrayList;
import progym.logica.basica.entidades.Serie;

/**
 *
 * @author Klac
 */
interface InterfazSerieDao {
    
    public ArrayList<Serie> listarAllSeries() throws SQLException;
    
    public ArrayList<Serie> listarAllSeries(int codRutina) throws SQLException;
    
    public void addSerie(Serie rutina,int codRutina) throws SQLException;
    
    public Serie getSerie(int codigo) throws SQLException;
    
    public void editSerie(Serie serie) throws SQLException;
    
    public void removeSerie(int codigo) throws SQLException;
    
    public int getSerieCount();
    
    public int getSerieCount(int codRutina);
    
    public ArrayList<Serie> getSerieName(String nombre) throws SQLException;
}
