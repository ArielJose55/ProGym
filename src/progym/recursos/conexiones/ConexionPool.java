/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.recursos.conexiones;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 *
 * @author Klac
 */
public class ConexionPool {
    
    private static ConexionPool poolConexion=null;
    private final ResourceBundle rs=ResourceBundle.getBundle("progym/recursos/conexiones/propiedades");
    
    private ConexionPool() throws SQLException {
       
        maxConex=Byte.parseByte(rs.getString("max_con"));
        minConex=Byte.parseByte(rs.getString("min_con"));
        cantConex=Byte.parseByte(rs.getString("cant_ins"));
        try {
            Class.forName(rs.getString("driver"));
        } catch (ClassNotFoundException ex) {
        }
        libres=new ArrayList<>();
        usadas=new ArrayList<>();
        instanciarConexiones(cantConex);
    }
    
    public static ConexionPool getConexionPool() throws SQLException{
        if(poolConexion==null){
            poolConexion=new ConexionPool();
        }
        return poolConexion;
    }
    
    public Connection getConnection() throws SQLException{
        if(libres.isEmpty()){
            if(!instanciarMas()){
                throw new RuntimeException();
            }
        }
        Connection aux=libres.remove(0);
        usadas.add(aux);
       
        return aux;
    }
    
    private void instanciarConexiones(byte cantidad) throws SQLException{
  
        Connection con;
        for (byte i = 0; i <cantidad; i++) {
            con= DriverManager.getConnection(rs.getString("urldatabase"), rs.getString("user"), "Marelis");
            libres.add(con);
        }
    }
    
    private boolean instanciarMas() throws SQLException{
        byte cantActual=(byte) (libres.size() + usadas.size());
        
        byte x=(byte) Math.min(maxConex-cantActual, cantConex);
        
        if(x>0){
            instanciarConexiones(x);
        }
        return x>0;
    } 
    
    public void devolverConexion(Connection conexion){
        boolean de=usadas.remove(conexion);
        if(de){
            libres.add(conexion);
        }
    }
    
    {
        Runtime.getRuntime().addShutdownHook(new ThreadClose());
    }
    
    private class ThreadClose extends Thread{

        @Override
        public void run() {
            try {
                for (Connection connection : libres) {
                    connection.close();
                }
                for (Connection connection : usadas) {
                    connection.close();
                }
            } catch (Exception e) {}
        }
        
    }
    
    private byte maxConex;
    private byte minConex;
    private byte cantConex;
    private ArrayList<Connection> libres;
    private ArrayList<Connection> usadas;
}
