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
public class SerieController {
    private SerieDao seriedao;

    public SerieController() {
        seriedao=new SerieDao();
    }
    
    public ArrayList<Serie> getListaSeries() throws SQLException{
        return seriedao.listarAllSeries();
    }
    
    public ArrayList<Serie> getListaSeries(int codRutina) throws SQLException{
        return seriedao.listarAllSeries(codRutina);
    }
    
    public boolean isEmplyListaSerie(){
        return getCantidadAllSerie() == 0;
    }
    
    public boolean isEmplyListaSerie(int codRutina){
        return getCantidadAllSerie(codRutina) == 0;
    }
    
    public void añadirSerie(Serie serie,int codRutina) throws SQLException{
        seriedao.addSerie(serie, codRutina);
    }
    
    public Serie getSerie(short codigo) throws SQLException{
        return seriedao.getSerie(codigo);
    }
    
    public void editarSerie(Serie serie) throws SQLException{
        seriedao.editSerie(serie);
    }
    
    public void eliminarSerie(short codigo) throws SQLException{
        seriedao.removeSerie(codigo);
    }
    
    public int getCantidadAllSerie(){
        return seriedao.getSerieCount();
    }
    
    public int getCantidadAllSerie(int codRutina){
        return seriedao.getSerieCount(codRutina);
    }
    
    public ArrayList<Serie> getSerieNombre(String nomnbre) throws SQLException{
        return seriedao.getSerieName(nomnbre);
    }
}
