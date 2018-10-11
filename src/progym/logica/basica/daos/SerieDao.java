/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.logica.basica.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import progym.logica.basica.entidades.Serie;
import progym.recursos.conexiones.ConexionPool;

/**
 *
 * @author Klac
 */
class SerieDao implements InterfazSerieDao{

    private final ResourceBundle rb=ResourceBundle.getBundle("progym/logica/basica/util/sentencias");
    
    public SerieDao() {
    }

    @Override
    public ArrayList<Serie> listarAllSeries() throws SQLException {
        ArrayList<Serie> lista=new ArrayList<>();
        Connection conexion=null;
        try{
            conexion=ConexionPool.getConexionPool().getConnection();
            try (Statement sentencia = conexion.createStatement()) {
                ResultSet rs=sentencia.executeQuery(rb.getString("selectAllSerie"));
                while(rs.next()){
                    Serie serie=new Serie(rs.getInt(1));
                    serie.setNombre(rs.getString(2));
                    serie.setRepe1(rs.getShort(3));
                    serie.setRepe2(rs.getShort(4));
                    serie.setRepe3(rs.getShort(5));
                    serie.setRepe4(rs.getShort(6));
                    serie.setCodigoRutina(rs.getInt(7));
                    
                    lista.add(serie);
                }
                sentencia.close();
            }
            return lista;
        }finally{
            if(conexion != null) ConexionPool.getConexionPool().devolverConexion(conexion);
        }
    }

    
    @Override
    public ArrayList<Serie> listarAllSeries(int codRutina) throws SQLException {
        ArrayList<Serie> lista=new ArrayList<>();
        Connection conexion=null;
        try{
            conexion=ConexionPool.getConexionPool().getConnection();
            try (PreparedStatement sentencia = conexion.prepareStatement(rb.getString("preSerieCodRut"))) {
                sentencia.setInt(1, codRutina);
                ResultSet rs=sentencia.executeQuery();
                while(rs.next()){
                    Serie serie=new Serie(rs.getInt(1));
                    serie.setNombre(rs.getString(2));
                    serie.setRepe1(rs.getShort(3));
                    serie.setRepe2(rs.getShort(4));
                    serie.setRepe3(rs.getShort(5));
                    serie.setRepe4(rs.getShort(6));
                    serie.setCodigoRutina(rs.getInt(7));
                    
                    lista.add(serie);
                }
                sentencia.close();
            }
            return lista;
        }finally{
            if(conexion != null) ConexionPool.getConexionPool().devolverConexion(conexion);
        }
    }

    @Override
    public void addSerie(Serie rutina, int codRutina) throws SQLException {
        Connection conexion=null;
        try{
            conexion=ConexionPool.getConexionPool().getConnection();
            try (PreparedStatement sentencia = conexion.prepareStatement(rb.getString("preAddSerieCodRut"))) {
                sentencia.setInt(1, rutina.getCodigo());
                sentencia.setString(2, rutina.getNombre());
                sentencia.setShort(3, rutina.getRepe1());
                sentencia.setShort(4, rutina.getRepe2());
                sentencia.setShort(5, rutina.getRepe3());
                sentencia.setShort(6, rutina.getRepe4());
                sentencia.setInt(7, codRutina);
                
                sentencia.executeUpdate();
                sentencia.close();
            }
        }finally{
            if(conexion != null) ConexionPool.getConexionPool().devolverConexion(conexion);
        }
    }

    @Override
    public Serie getSerie(int codigo) throws SQLException {
        Connection conexion=null;
        try{
            conexion=ConexionPool.getConexionPool().getConnection();
            try (PreparedStatement sentencia = conexion.prepareStatement(rb.getString("preGetSerieCod"))) {
                sentencia.setInt(1, codigo);
                ResultSet rs=sentencia.executeQuery();
                if(rs.next()){
                    Serie serie=new Serie(codigo);
                    serie.setNombre(rs.getString(2));
                    serie.setRepe1(rs.getShort(3));
                    serie.setRepe2(rs.getShort(4));
                    serie.setRepe3(rs.getShort(5));
                    serie.setRepe4(rs.getShort(6));
                    serie.setCodigoRutina(rs.getInt(7));
                    
                    return serie;
                }
                sentencia.close();
            }
            throw new SQLException("Serie con este "+codigo+" no existe");
        }finally{
            if(conexion != null) ConexionPool.getConexionPool().devolverConexion(conexion);
        }
    }

    @Override
    public void editSerie(Serie serie) throws SQLException {
        Connection conexion=null;
        try{
            conexion=ConexionPool.getConexionPool().getConnection();
            try (PreparedStatement sentencia = conexion.prepareStatement(rb.getString("preEditSerie"))) {
                sentencia.setString(1, serie.getNombre());
                sentencia.setShort(2, serie.getRepe1());
                sentencia.setShort(3, serie.getRepe2());
                sentencia.setShort(4, serie.getRepe3());
                sentencia.setShort(5, serie.getRepe4());
                sentencia.setInt(6, serie.getCodigo());
                
                sentencia.executeUpdate();
                sentencia.close();
            }
        }finally{
            if(conexion != null) ConexionPool.getConexionPool().devolverConexion(conexion);
        }
    }

    @Override
    public void removeSerie(int codigo) throws SQLException {
        Connection conexion=null;
        try{
            conexion=ConexionPool.getConexionPool().getConnection();
            try (PreparedStatement sentencia = conexion.prepareStatement(rb.getString("preDeleteSerie"))) {
                sentencia.setInt(1, codigo);
                
                sentencia.executeUpdate();
                sentencia.close();
            }
        }finally{
            if(conexion != null) ConexionPool.getConexionPool().devolverConexion(conexion);
        }
    }

    @Override
    public int getSerieCount() {
        Connection conexion=null;
        int cont=0;
        try{
            conexion=ConexionPool.getConexionPool().getConnection();
            try (Statement sentencia = conexion.createStatement()) {
                ResultSet rs=sentencia.executeQuery(rb.getString("selectAllSerie"));
                
                while(rs.next()){ cont++; }
                
                sentencia.close();
            }
        }finally{
            try{
                if(conexion!=null) ConexionPool.getConexionPool().devolverConexion(conexion);
            }finally{
                return cont;
            }
        }
    }

    @Override
    public int getSerieCount(int codRutina) {
        Connection conexion=null;
        int cont=0;
        try{
            conexion=ConexionPool.getConexionPool().getConnection();
            try (PreparedStatement sentencia = conexion.prepareStatement(rb.getString("preSerieCodRut"))) {
                sentencia.setInt(1, codRutina);
                ResultSet rs=sentencia.executeQuery();
                
                while(rs.next()) cont++;
                
                sentencia.close();
            }
        }finally{
            try{
                if(conexion!=null) ConexionPool.getConexionPool().devolverConexion(conexion);
            }finally{
                return cont;
            }
        }
    }

    @Override
    public ArrayList<Serie> getSerieName(String nombre) throws SQLException {
        ArrayList<Serie> lista=new ArrayList<>();
        Connection conexion=null;
        try{
            conexion=ConexionPool.getConexionPool().getConnection();
            try (PreparedStatement sentencia = conexion.prepareStatement(rb.getString("preGetSerieNom"))) {
                sentencia.setString(1, nombre);
                ResultSet rs=sentencia.executeQuery();
                
                while(rs.next()){
                    Serie serie=new Serie(rs.getInt(1));
                    serie.setNombre(rs.getString(2));
                    serie.setRepe1(rs.getShort(3));
                    serie.setRepe2(rs.getShort(4));
                    serie.setRepe3(rs.getShort(5));
                    serie.setRepe4(rs.getShort(6));
                    serie.setCodigoRutina(rs.getInt(7));
                            
                    lista.add(serie);
                }
                sentencia.close();
            }
            return lista;
        }finally{
            if(conexion != null) ConexionPool.getConexionPool().devolverConexion(conexion);
        }
    }
    
}
