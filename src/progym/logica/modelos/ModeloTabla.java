/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.logica.modelos;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import progym.logica.basica.entidades.Serie;

/**
 *
 * @author Klac
 */
public class ModeloTabla extends AbstractTableModel{

    private static short uldigito=0;
    private static byte indexLista=0;
   
    public ModeloTabla() {
        propiedades=new PropiedadesTabla();
        datos=new ArrayList<>();
    }

    public ModeloTabla(PropiedadesTabla propiedades) {
        this.propiedades = propiedades;
        datos=new ArrayList<>();
    }
    
    public ModeloTabla(ArrayList datos, PropiedadesTabla propiedades) {
        this.datos = datos;
        indexLista=(byte)datos.size();
        this.propiedades = propiedades;
    }

    public ArrayList getDatos() {
        return datos;
    }

    public void addManualDato(Serie dato){
        if(yaEstaAñadido(dato)) throw  new UnsupportedOperationException("Esta serie ya fue añadida");
        int primero=datos.size();
        datos.add(dato);
        indexLista++;
        fireTableRowsInserted(primero, primero);
    }
    
    public void addDato(Serie serie){
        int primero=datos.size();
        datos.add(serie);
        indexLista++;
        fireTableRowsInserted(primero, primero);
    }
    
    public void setDatos(ArrayList datos) {
        this.datos = datos;
    }
    
    public static byte getSizeModeloDato(){
        return ((byte)(indexLista-1));
    }
    
    public void addNewDatos(ArrayList newDatos){
        if(newDatos != null){
            int primero = datos.size();
            int ultimo = primero + newDatos.size() - 1;
            this.datos.addAll(newDatos);
            indexLista=(byte)(ultimo+1);
            fireTableRowsInserted(primero, ultimo);
        }
    }
    
    public void actulizarRepeticion(int codSerie, short valor, int fila, int columna){
        Serie serie=propiedades.getIndexValue(datos, codSerie, valor, columna);
        setValueAt(serie, fila, columna);
    }
    
    public PropiedadesTabla getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(PropiedadesTabla propiedades) {
        this.propiedades = propiedades;
        fireTableStructureChanged();
    }
    
    private boolean yaEstaAñadido(Object object){
        if(datos.isEmpty() || object == null) return false;
        
        if(datos.get(0) instanceof Serie){
            Serie aux = (Serie)object;
            for(Object aux2 : datos ){
                Serie otro = (Serie)aux2;
                if(otro.getNombre().equals(aux.getNombre())){
                    return true;
                }
            }
            return false;
        }
        throw new UnsupportedOperationException("Operacion no valida");
    }

    
    
    public int getCodSerieOfName(String nameSerie){
        if(propiedades.getTipoTabla()!=TablaImple.TABLA_EMPLY){
            if( datos == null) return 0;
            if(propiedades.getTipoTabla()==TablaImple.TABLA_MUESTRA){
                for (Serie serie : datos) {
                    if(serie.getNombre().compareTo(nameSerie) == 0){
                        return serie.getCodigo();
                    }
                }
                return 0;
            }
        }
        throw new UnsupportedOperationException("Error Tabla Vacía");
    }
    
    public void setSeleccionarModoMunual(boolean editable){
        this.modoManual = editable;
    }

    public void setRowEditada(int row){
        this.rowEditada = row;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex == 0 || columnIndex == 1) return false;
        return rowEditada == rowIndex;
    }
    
    @Override
    public String getColumnName(int columna){
        return propiedades.getNombresColumna()[columna];
    }
    
    @Override
    public Class getColumnClass(int columna){
        return propiedades.getNombresClass()[columna];
    }
    
    @Override
    public int getRowCount() {
        return (datos.isEmpty())? 0:datos.size();
    }

    @Override
    public int getColumnCount() {
        return propiedades.getNombresColumna().length;
    }
    
    
    @Override
    public void setValueAt(Object row, int rowIndex, int columnValue){
       if(modoManual){
           try{
                 switch(columnValue){
                     case 2:{  datos.get(rowIndex).setRepe1(Short.valueOf(String.valueOf(row))); break; }
                     case 3:{  datos.get(rowIndex).setRepe2(Short.valueOf(String.valueOf(row))); break; }
                     case 4:{  datos.get(rowIndex).setRepe3(Short.valueOf(String.valueOf(row))); break; }
                     case 5:{  datos.get(rowIndex).setRepe4(Short.valueOf(String.valueOf(row))); break; }
                }
           } finally {
               return;
           }
       }else{
           Serie serie = (Serie)row;
           switch(columnValue){
               case 2:  datos.get(rowIndex).setRepe1(serie.getRepe1()); break;
               case 3:  datos.get(rowIndex).setRepe2(serie.getRepe2()); break;
               case 4:  datos.get(rowIndex).setRepe3(serie.getRepe3()); break;
               case 5:  datos.get(rowIndex).setRepe4(serie.getRepe4()); break;
           }
       }
       fireTableCellUpdated(rowIndex, columnValue);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return propiedades.getValueAttribute(datos.get(rowIndex), columnIndex);
    }
    
    private boolean modoManual  = false;
    private int rowEditada = -1;
    private ArrayList<Serie> datos;
    private PropiedadesTabla propiedades;
}
