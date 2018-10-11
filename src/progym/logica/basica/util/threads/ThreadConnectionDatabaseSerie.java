/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.logica.basica.util.threads;

import java.sql.SQLException;
import progym.logica.basica.daos.SerieController;
import progym.logica.basica.entidades.Serie;

/**
 *
 * @author Ariel Jose Arnedo
 */
public class ThreadConnectionDatabaseSerie extends Thread{
    
    public ThreadConnectionDatabaseSerie(){
        comienza();
    }

    private Serie getSerieTemplete() {
        return serie;
    }

    private void setSerieTemplate(Serie serie) {
        this.serie = serie;
    }
    
    public synchronized void addSerieAtDatabase(Serie serie, int codSerie){
        setSerieTemplate(serie);
        this.codSerie = codSerie;
        this.notify();
    }
    
    private synchronized void parar() throws InterruptedException{
        this.wait();
    } 
    
    private void comienza(){
        this.start();
    }
    
    @Override
    public void run(){
        while(true){
            
            try {
                parar();
                new SerieController().añadirSerie(getSerieTemplete(), codSerie);
            }catch(InterruptedException | SQLException ex){
                continue;
            }
            
        }
    }
    
    private Serie serie;
    private int codSerie;
}
