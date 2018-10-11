/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.logica.basica.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import progym.logica.basica.util.Fecha;

/**
 *
 * @author Klac
 */
public class Rutina implements Serializable{

    public Rutina() {
    }

    public Rutina(int codigo) {
        this.codigo = codigo;
    }

    public Rutina(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Serie> getSeries() {
        return series;
    }

    public void setSeries(ArrayList<Serie> series) {
        this.series = series;
    }
    
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Rutina)){return false;}
        
        Rutina otra=(Rutina)obj;
        if(this.codigo!=null && otra.codigo!=null && this.codigo.equals(otra.codigo)){
            return true;
        }
        return false;
    }    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.codigo);
        return hash;
    }
    
    @Override
    public String toString(){
        return String.format("Rutina de %s del %s", nombre, Fecha.getNameFechaRutina(codigo));
    }
    
    private Integer codigo;
    private String nombre;
    private ArrayList<Serie> series;
}
