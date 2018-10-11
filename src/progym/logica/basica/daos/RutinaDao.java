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
import progym.logica.basica.entidades.Rutina;
import progym.logica.basica.entidades.Serie;
import progym.recursos.conexiones.ConexionPool;


/**
 *
 * @author Klac
 */
class RutinaDao implements InterfazRutinaDao{
    
    private final ResourceBundle rb=ResourceBundle.getBundle("progym/logica/basica/util/sentencias");

    RutinaDao(){
    }

    @Override
    public ArrayList<Rutina> listarAllRutinas() throws SQLException{
        ArrayList<Rutina> lista=new ArrayList<>();
        Connection conexion=null;
        try {
            conexion=ConexionPool.getConexionPool().getConnection();
            try (Statement sentencia = conexion.createStatement(); PreparedStatement sentencia2 = conexion.prepareCall(rb.getString("preSerieCodRut"))) {
                ResultSet rs1=sentencia.executeQuery(rb.getString("selectAllRutina"));
                while(rs1.next()){
                    Rutina aux=new Rutina();
                    aux.setCodigo(rs1.getInt(1));
                    aux.setNombre(rs1.getString(2));
                    sentencia2.setInt(1, aux.getCodigo());
                    ResultSet rs2=sentencia2.executeQuery();
                    
                    while (rs2.next()) {
                        Serie serie=new Serie(rs2.getInt(1));
                        serie.setNombre(rs2.getString(2));
                        serie.setRepe1(rs2.getShort(3));
                        serie.setRepe2(rs2.getShort(4));
                        serie.setRepe3(rs2.getShort(5));
                        serie.setRepe4(rs2.getShort(6));
                        serie.setCodigoRutina(aux.getCodigo());
                        if(aux.getSeries()==null){
                            aux.setSeries(new ArrayList<Serie>());
                        }
                        aux.getSeries().add(serie);
                    }

                    lista.add(aux);
                }
                sentencia2.close();
                sentencia.close();
            }
     
            return lista;
        }finally{
            if(conexion!=null) ConexionPool.getConexionPool().devolverConexion(conexion);
        }
    }

    @Override
    public void addRutina(Rutina rutina) throws SQLException{
        Connection conexion=null;
        try {
            conexion=ConexionPool.getConexionPool().getConnection();
            try (PreparedStatement sentencia = conexion.prepareStatement(rb.getString("preAddRutina"))) {
                sentencia.setInt(1, rutina.getCodigo());
                sentencia.setString(2, rutina.getNombre());
                sentencia.executeUpdate();
            }
        }finally{
            if(conexion!=null) ConexionPool.getConexionPool().devolverConexion(conexion);
        }
    }

    @Override
    public Rutina getRutina(int codigo) throws SQLException{
        Connection conexion=null;
        try{
            conexion=ConexionPool.getConexionPool().getConnection();
            PreparedStatement sentencia=conexion.prepareStatement(rb.getString("preSerieCodRut"));
            sentencia.setInt(1, codigo);
            ResultSet rst=sentencia.executeQuery();
            
            if(rst.next()){
                Rutina aux=new Rutina(codigo);
                aux.setNombre(rst.getString(2));
                if(aux.getSeries()==null){
                    aux.setSeries(new ArrayList<Serie>());
                }
                try (PreparedStatement sentencia2 = conexion.prepareStatement(rb.getString("preSerieCodRut"))) {
                    sentencia2.setInt(1, aux.getCodigo());
                    ResultSet rs2=sentencia2.executeQuery();
                    while (rs2.next()) {
                        Serie serie=new Serie(rs2.getInt(1));
                        serie.setNombre(rs2.getString(2));
                        serie.setRepe1(rs2.getShort(3));
                        serie.setRepe2(rs2.getShort(4));
                        serie.setRepe3(rs2.getShort(5));
                        serie.setRepe4(rs2.getShort(6));
                        serie.setCodigoRutina(aux.getCodigo());
                        if(aux.getSeries()==null){
                            aux.setSeries(new ArrayList<Serie>());
                        }
                        aux.getSeries().add(serie);
                    }
                    sentencia2.close();
                }
                sentencia.close();
                return aux;
            }
            throw new SQLException("Error no se encontro Rutina");
        }finally{
            if(conexion!=null) ConexionPool.getConexionPool().devolverConexion(conexion);
        }
    }

    @Override
    public void editRutina(Rutina codigo) throws SQLException{
        Connection conexion=null;
        try{
            conexion=ConexionPool.getConexionPool().getConnection();
            try (PreparedStatement sentencia = conexion.prepareCall(rb.getString("editRutina"), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                sentencia.setString(1, codigo.getNombre());
                sentencia.setInt(2, codigo.getCodigo());
                sentencia.executeUpdate();
                sentencia.close();
            }
        }finally{
            if(conexion!=null) ConexionPool.getConexionPool().devolverConexion(conexion);
        }
    }

    @Override
    public void removeRutina(int codigo) throws SQLException{
        Connection conexion= null;
        try{
            conexion= ConexionPool.getConexionPool().getConnection();
            try (PreparedStatement sentencia = conexion.prepareStatement(rb.getString("deleteRutina"))) {
                sentencia.setInt(1, codigo);
                sentencia.executeUpdate();
                sentencia.close();
            }
        }finally{
            if(conexion!=null) ConexionPool.getConexionPool().devolverConexion(conexion);
        }
    }

    @Override
    public int getRutinaCount() {
        Connection conexion=null;
        int cont=0;
        try{
            conexion=ConexionPool.getConexionPool().getConnection();
            try (Statement sentecia = conexion.createStatement()) {
                ResultSet rst=sentecia.executeQuery(rb.getString("selectAllRutina"));
                while(rst.next()) cont++;
                sentecia.close();
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
    public ArrayList<Rutina> getRutinaName(String nombre) throws SQLException{
        ArrayList<Rutina> lista=new ArrayList<>();
        Connection conexion=null;
        try{
            conexion=ConexionPool.getConexionPool().getConnection();
            try (PreparedStatement sentencia = conexion.prepareStatement(rb.getString("preRutinaNom")); PreparedStatement sentencia2 = conexion.prepareStatement(rb.getString("preSerieCodRut"))) {
                
                sentencia.setString(1, nombre);
                
                ResultSet rst=sentencia.executeQuery();
                
                while( rst.next() ){
                    
                    Rutina aux=new Rutina(rst.getInt(1));
                    aux.setNombre(rst.getString(2));
                    
                    if(aux.getSeries()==null){
                        aux.setSeries(new ArrayList<Serie>());
                    }
                    
                    sentencia2.setInt(1, aux.getCodigo());
                    ResultSet rst2=sentencia2.executeQuery();
                    while(rst2.next()){
                        Serie serie=new Serie(rst2.getInt(1));
                        serie.setNombre(rst2.getString(2));
                        serie.setRepe1(rst2.getShort(3));
                        serie.setRepe2(rst2.getShort(4));
                        serie.setRepe3(rst2.getShort(5));
                        serie.setRepe4(rst2.getShort(6));
                        serie.setCodigoRutina(aux.getCodigo());
                        aux.getSeries().add(serie);
                    }
                    lista.add(aux);
                }
                sentencia2.close();
                sentencia.close();
            }
            return lista;
        }finally{
            if(conexion != null) ConexionPool.getConexionPool().devolverConexion(conexion);
        }
    }

    @Override
    public ArrayList<Rutina> getRutinaRango(int fistCodigo, int endCodigo) throws SQLException {
        ArrayList<Rutina> lista=new ArrayList<>();
        Connection conexion=null;
        try{
            conexion=ConexionPool.getConexionPool().getConnection();
            try (PreparedStatement sentencia = conexion.prepareStatement(rb.getString("preRutinaFistEnd")); PreparedStatement sentencia2 = conexion.prepareStatement(rb.getString("preSerieCodRut"))) {
                sentencia.setInt(1, fistCodigo);
                sentencia.setInt(2, endCodigo);
                
                ResultSet rst=sentencia.executeQuery();
                
                while(rst.next()){
                    Rutina aux=new Rutina(rst.getInt(1));
                    aux.setNombre(rst.getString(2));
                    if(aux.getSeries()==null){
                        aux.setSeries(new ArrayList<Serie>());
                    }
                    sentencia2.setInt(1, aux.getCodigo());
                    ResultSet rst2=sentencia2.executeQuery();
                    
                    while(rst2.next()){
                        Serie serie=new Serie(rst2.getInt(1));
                        serie.setNombre(rst2.getString(2));
                        serie.setRepe1(rst2.getShort(3));
                        serie.setRepe2(rst2.getShort(4));
                        serie.setRepe3(rst2.getShort(5));
                        serie.setRepe4(rst2.getShort(6));
                        serie.setCodigoRutina(aux.getCodigo());
                        
                        aux.getSeries().add(serie);
                    }
                    lista.add(aux);
                }
                sentencia2.close();
                sentencia.close();
            }
            return lista;
        }finally{
            if(conexion != null) ConexionPool.getConexionPool().devolverConexion(conexion);
        }
    }    
}
