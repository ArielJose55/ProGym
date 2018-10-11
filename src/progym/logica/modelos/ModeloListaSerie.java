/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.logica.modelos;

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import progym.logica.basica.util.RecuperatorOfXMLSerie;

/**
 *
 * @author Klac
 */
public class ModeloListaSerie extends AbstractListModel<String>{

    private static byte indexLista=0;
    
    public ModeloListaSerie()  {
        try {
            datos= new RecuperatorOfXMLSerie().getAllSeriesDeHoy();
            indexLista=(byte)datos.size();
        } catch (Exception ex) {
            datos=new ArrayList<>();
        }
    }
    
    public void addSerieALista(String serie){
        int i=datos.size();
        datos.add(serie);
        indexLista++;
        fireIntervalAdded(this, i, i);
    }
    
    public static byte getSizeModeloDato(){
        return ((byte)(indexLista-1));
    }
    
    public void addSeriesALista(ArrayList<String> listaSeries){
        int i=datos.size();
        int u=i+listaSeries.size()-1;
        datos.addAll(listaSeries);
        indexLista=(byte)(u+1);
        fireIntervalAdded(this, i, u);
    }
    
    public void removeSerieDeLista(String serie){
        int i=datos.size();
        if(datos.remove(serie)){
        indexLista--;
        fireIntervalRemoved(this, i, i);}
    }
    
    public void removeSeriesDeLista(ArrayList<String> listaSeries){
        int i=datos.size();
        int u=i+listaSeries.size()-1;
        if(datos.removeAll(listaSeries)){
        indexLista=(byte)(indexLista-listaSeries.size());
        fireIntervalRemoved(this, i, u);}
    }
    
    @Override
    public int getSize() {
        return (datos.isEmpty())? 0:datos.size();
    }

    
    @Override
    public String getElementAt(int index) {
        return datos.get(index);
    }
    private ArrayList<String> datos;
}
