/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.logica.modelos;

import javax.swing.DefaultListSelectionModel;

/**
 *
 * @author Klac
 */
public class ModeloListaSeleccion extends DefaultListSelectionModel{

    private byte indexLista=0;
    
    private static final byte SELECCION_SIMPLE=0;
    private static final byte SELECCION_MULTIPLE=1;
    
    private byte seleccion;
    
    private byte tipo;
    
    public ModeloListaSeleccion(byte tipo){
        seleccion=SELECCION_SIMPLE;
        this.tipo=tipo;
    }
    
    public void avanzarSerie(){
        indexLista++;
        if(tipo== 1){
            if(indexLista >= ModeloListaSerie.getSizeModeloDato()){indexLista = ModeloListaSerie.getSizeModeloDato();}
            if(seleccion == SELECCION_SIMPLE){
                 super.setSelectionInterval(indexLista, indexLista);
            }
        }else{
            if(indexLista >= ModeloTabla.getSizeModeloDato()){indexLista = ModeloTabla.getSizeModeloDato();}
            if(seleccion == SELECCION_SIMPLE){
                super.setSelectionInterval(indexLista, indexLista);
            }
        }
    }
    
    public int getSelectionIndex(){
        return indexLista;
    }
    
    public void setSelectionIndex(byte indexLista){
        this.indexLista = indexLista;
    }
    
    public void retrocederSerie(){
        indexLista--;
        if(indexLista < 0){indexLista=0;}
        if(seleccion == SELECCION_SIMPLE ){
            setSelectionInterval(indexLista, indexLista);
        }
    }
    
    public void setSelectedItem(int index){
        if(seleccion == SELECCION_SIMPLE){
            indexLista+=index;
           super.setSelectionInterval(index, index);
        }
    }

    public void isTipoSeleccionMultiple(boolean seleccion){
        this.seleccion=(seleccion)? SELECCION_MULTIPLE:SELECCION_SIMPLE;
    }
    @Override
    public void setSelectionInterval(int index0, int index1) {
        if(seleccion == SELECCION_MULTIPLE){
            super.setSelectionInterval(index0, index1);
        }else{
            super.setSelectionInterval(indexLista, indexLista);
        }
    }
    
}
