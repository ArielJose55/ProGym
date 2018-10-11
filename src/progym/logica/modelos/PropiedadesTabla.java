/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.logica.modelos;

import java.util.ArrayList;
import progym.logica.basica.entidades.Serie;

/**
 *
 * @author Klac
 */
public class PropiedadesTabla implements TablaImple{
    
    public PropiedadesTabla(){
        this.tipoTabla=TABLA_EMPLY;
    }
    
    public PropiedadesTabla(byte tipoTabla){
        this.tipoTabla=tipoTabla;
    }

    public byte getTipoTabla() {
        return tipoTabla;
    }

    public void setTipoTabla(byte tipoTabla) {
        this.tipoTabla = tipoTabla;
    }
    
    public String[] getNombresColumna(){
        switch(tipoTabla){
            case TABLA_EMPLY:{
                return new String[]{"","","","","",""};
            }
            case TABLA_MUESTRA:{
                return NAME_COLUMNA_MUESTRA;
            }
            default:{
                return NAME_COLUMNA_REPORTE;
            }
        }
    }
    
    public Class[] getNombresClass(){
        switch(tipoTabla){
            case TABLA_EMPLY:{
                return new Class[]{String.class,String.class,String.class,String.class,String.class,String.class};
            }
            case TABLA_MUESTRA:{
                return NAME_CLASS_MUESTRA;
            }
            default:{
                return NAME_CLASS_REPORTE;
            }
        }
        
    }
    
    public Serie getIndexValue(ArrayList datos, int codigo, short valor,int columna){
        switch(tipoTabla){
            case TABLA_EMPLY:{
                throw new UnsupportedOperationException("Operacion no valida para este tipo de tabla");
            }
            case TABLA_MUESTRA:{
                
                ArrayList<Serie> lista=datos;
                for (int i = 0; i < lista.size(); i++) {
                    if(lista.get(i).getCodigo()==codigo){
                        Serie serie=lista.get(i);
                        switch(columna){
                            case 2: serie.setRepe1(valor);break;
                            case 3: serie.setRepe2(valor);break;
                            case 4: serie.setRepe3(valor);break;
                            case 5: serie.setRepe4(valor);break;
                            default:{
                                throw new UnsupportedOperationException("Operacion no validala Columna no es correcta");
                            }
                        }
                        return serie;
                    }
                }
            }
        }
        throw new UnsupportedOperationException("Operacion no valida para este tipo de tabla");
    }
    
    public Object getValueAttribute(Object dato,int columna){
        if(tipoTabla != TABLA_EMPLY){
            if(tipoTabla == TABLA_MUESTRA){
                Serie serie = (Serie)dato;
                switch(columna){
                    case 0:{ return serie.getCodigo(); }
                    case 1:{ return serie.getNombre(); }
                    case 2:{ return serie.getRepe1(); }
                    case 3:{ return serie.getRepe2(); }
                    case 4:{ return serie.getRepe3(); }
                    case 5:{ return serie.getRepe4(); }
                }
            }
        }
        return null;
    }
    
    private byte tipoTabla;
}
