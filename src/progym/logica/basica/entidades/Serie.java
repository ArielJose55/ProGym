/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.logica.basica.entidades;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Klac
 */
public class Serie implements Serializable{

    public Serie() {
    }

    public Serie(Integer codigo) {
        this.codigo = codigo;
    }

    public Serie(Integer codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
    
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Short getRepe1() {
        return repe1;
    }

    public void setRepe1(Short repe1) {
        this.repe1 = repe1;
    }

    public Short getRepe2() {
        return repe2;
    }

    public void setRepe2(Short repe2) {
        this.repe2 = repe2;
    }

    public Short getRepe3() {
        return repe3;
    }

    public void setRepe3(Short repe3) {
        this.repe3 = repe3;
    }

    public Short getRepe4() {
        return repe4;
    }

    public void setRepe4(Short repe4) {
        this.repe4 = repe4;
    }

    public int getCodigoRutina() {
        return codigoRutina;
    }

    public void setCodigoRutina(int codigoRutina) {
        this.codigoRutina = codigoRutina;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Serie)) {return false;}
        
        Serie otro = (Serie) obj;
        if(this.codigo!=null && otro.codigo!=null && this.codigo.equals(otro.codigo)){return true;}
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public String toString() {
        return "Serie: "+nombre+" "+repe1+" "+repe2+" "+repe3+" "+repe4;
    }
    
    
    
    private Integer codigo;
    private String nombre;
    private Short repe1;
    private Short repe2;
    private Short repe3;
    private Short repe4;
    private int codigoRutina;
}
