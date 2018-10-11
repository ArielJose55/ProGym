/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.logica.basica.util;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author Ariel Jose Arnedo
 */
public class Fecha implements Serializable{
    
    private Fecha(){
        String aux="";
        
        aux+=(""+Calendar.getInstance().get(Calendar.YEAR));
        if(Calendar.getInstance().get(Calendar.MONTH)+1 < 10){
            aux+=("0"+(Calendar.getInstance().get(Calendar.MONTH)+1));
        }else{
            aux+=(""+(Calendar.getInstance().get(Calendar.MONTH)+1));
        }
        if(Calendar.getInstance().get(Calendar.DAY_OF_MONTH) < 10){
            aux+=("0"+Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        }else{
            aux+=(""+Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        }
        
        fecha=Integer.parseInt(aux);
    }
    
    public static int getCodFechaRutina(){
        new Fecha();
        return fecha;
    }
    
    public static Fecha getFecha(){
        return new Fecha();
    }
    
    public static void setValorFechaSerie(short valor){
        Fecha.valor = valor;
    }
    
    public static int getCodFechaSerie(){
        short serie;
        if(fecha == 0){
            new Fecha();
        }
        valor++;//20150109
        return  (Integer.parseInt((Integer.toString(fecha).substring(3, 4))+Integer.toString(fecha).substring(6, 8)+Integer.toString(fecha).substring(4, 6))+valor);
    }
    
    public static int getCodLastMesFechaRutina(){
        new Fecha();
        return Integer.parseInt((fecha+"").substring(0,6)+"01");
    }
    
    public static String getNameFechaRutina(int fecha){
       String año=(""+fecha).substring(0, 4);
       String meses[]={"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
       return (""+fecha).substring(6, 8)+" de "+meses[Integer.parseInt((""+fecha).substring(4, 6))-1]+" de "+año;
    }
    
    public static String getNameBreveFechaRutina(int fecha){
       String año=(""+fecha).substring(0, 4);
       String meses[]={"Ene","Feb","Mar","Abr","May","Jun","Jul","Ago","Sep","Oct","Nov","Dic"};
       return (""+fecha).substring(6, 8)+"/"+meses[Integer.parseInt((""+fecha).substring(4, 6))-1]+"/"+año;
    }
    
    
    private static short valor=-1;
    private static int fecha;
}
