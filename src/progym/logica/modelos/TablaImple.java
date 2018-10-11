/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.logica.modelos;

/**
 *
 * @author Klac
 */
public interface TablaImple {
    
    public byte TABLA_EMPLY=-1;
    
    public byte TABLA_MUESTRA=1;
    public String NAME_COLUMNA_MUESTRA[]={"Codigo","Nombre","Repeticion 1","Repeticion 2","Repeticion 3","Repeticion 4"};
    public Class NAME_CLASS_MUESTRA[]={Integer.class,String.class,Short.class,Short.class,Short.class,Short.class};
    
    public byte TABLA_REPORTE=2;
    public String NAME_COLUMNA_REPORTE[]={};
    public Class NAME_CLASS_REPORTE[]={};
    
    public byte TABLA_CONSULTA = 3;
}
