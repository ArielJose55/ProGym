/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.logica.basica.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import progym.logica.basica.entidades.Rutina;
import progym.logica.basica.entidades.Serie;

/**
 *
 * @author Klac
 */
public class FactoryTable {
    
    public static JTable createTable(ArrayList datos){
        JTable table = new JTable(createTableModel(datos));
        table.setColumnModel(createModelColumn(datos.get(0)));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        table.setRowHeight(26);
        return table;
    } 
    
    public static JTable createTable(){
        JTable table = new JTable();
        return table;
    }
    
    private static TableModel createTableModel(final ArrayList datos){
        if(datos == null || datos.isEmpty()) throw new UnsupportedOperationException("datos para el modelo de la tabla vacio");
        AbstractTableModel modelo = new AbstractTableModel(){

            @Override
            public int getRowCount() {
                return datos.size();
            }
            
            @Override
            public Class getColumnClass(int columna){
                return getClassColumn(datos.get(0))[columna];
            }
            
            @Override
            public int getColumnCount() {
                return getNamesColumns(datos.get(0)).length;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return getAtributo(datos.get(rowIndex), columnIndex);
            }
            
        };
        return modelo;
    }
    
    private static Class[] getClassColumn(Object object){
        if(object instanceof Serie){
            return new Class[]{
                Integer.class,String.class,Short.class,Short.class,Short.class,Short.class
            };
        }else if(object instanceof Rutina){
            return new Class[]{
                
            };
        }else{
            throw new UnsupportedOperationException("datos no compatibles");
        }
    }
    
    private static String[] getNamesColumns(Object object){
        if(object instanceof Serie){
            return new String[]{
                "Codigo","Nombre","Repeticion 1"," Repeticion 2","Repeticion 3","Repeticion 4"
            };
        }else if(object instanceof Rutina){
            return new String[]{
                
            };
        }else{
            throw new UnsupportedOperationException("datos no compatibles");
        }
    }
    private static Object getAtributo(Object objeto, int columna){
        if(objeto instanceof Serie){
            Serie aux= (Serie)objeto;
            switch(columna){
                case 0:  return aux.getCodigo();
                case 1:  return aux.getNombre();
                case 2:  return aux.getRepe1();
                case 3:  return aux.getRepe2();
                case 4:  return aux.getRepe3();
                case 5:  return aux.getRepe4();
                default: return null;
            }
        }else if(objeto instanceof Rutina){
            Rutina aux= (Rutina)objeto;
            switch(columna){
                case 0:  return aux.getCodigo();
                case 1:  return aux.getNombre();
                case 2:  return aux.getSeries().size();
                case 3:  {
                    int cont = 0;
                    int i =0;
                    for(;i < aux.getSeries().size() ; i++){
                        cont += (aux.getSeries().get(i).getRepe1()+aux.getSeries().get(i).getRepe2()+aux.getSeries().get(i).getRepe3()+aux.getSeries().get(i).getRepe4());
                    }
                    
                    return ((cont*100)/(48*i))+"%";
                }
                case 4:  {
                    int cont = 0;
                    int i =0;
                    for(;i < aux.getSeries().size() ; i++){
                        cont += (aux.getSeries().get(i).getRepe1()+aux.getSeries().get(i).getRepe2()+aux.getSeries().get(i).getRepe3()+aux.getSeries().get(i).getRepe4());
                    }
                    return (float)(cont / i);
                }
                
                default: return null;
            }
        }else{
            throw new UnsupportedOperationException("datos no compatibles");
        }
    }
    private static class FilaRenderer extends DefaultTableCellRenderer{

        private final Color[] colores;
        private int columnaAplicada;
        public FilaRenderer(int columnaAplicada){
            this.columnaAplicada = columnaAplicada;
            this.colores = new Color[]{new Color(204, 204, 255), new Color(224,227,245)};
            
        }
        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
           super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
           
           if(columnaAplicada == -1){
               setBackground(new Color(160, 160, 160));
               setFont(new java.awt.Font("Microsoft YaHei UI",Font.BOLD, 14));
               setHorizontalAlignment(SwingConstants.CENTER);
               setForeground(new java.awt.Color(95, 37, 93));
               
               return this;
           }
           
           setBackground(colores[row%colores.length]);
           setFont(new Font("Microsoft YaHei UI",Font.BOLD,13));
           
           if(column == columnaAplicada){
               switch(columnaAplicada){
                   case 0:{
                       setHorizontalAlignment(SwingConstants.CENTER);
                       break;
                   }
                   case 1:{
                       break;
                   }
                   case 2:{
                       setHorizontalAlignment(SwingConstants.CENTER);
                       break;
                   }
                   case 3:{
                       setHorizontalAlignment(SwingConstants.CENTER);
                       break;     
                   }
                   case 4:{
                       setHorizontalAlignment(SwingConstants.CENTER);
                       break;
                   }
                   case 5:{
                       setHorizontalAlignment(SwingConstants.CENTER);
                       break;
                   }
               }
           }
           
           
           return this;
        }

        public int getColumnaAplicada() {
            return columnaAplicada;
        }

        public void setColumnaAplicada(int columnaAplicada) {
            this.columnaAplicada = columnaAplicada;
        }
        
    }
    
    private static TableColumnModel createModelColumn(Object objeto){
        DefaultTableColumnModel tablaColumn = new  DefaultTableColumnModel();
        
        if(objeto instanceof Serie){
            int [] ancho = {20,100,23,23,23,23};
            for(int i = 0 ; i < getNamesColumns(objeto).length ; i++){
                TableColumn columna = new TableColumn(i,ancho[i]);
                columna.setHeaderValue(getNamesColumns(objeto)[i]);
                columna.setCellRenderer(new FilaRenderer(i));
                columna.setHeaderRenderer(new FilaRenderer(-1));
                tablaColumn.addColumn(columna);
            }
        }else if(objeto instanceof Rutina){
            for(String str: getNamesColumns(objeto)){
                for(int i = 0 ; i < getNamesColumns(objeto).length ; i++){
                
            }
            }
        }else{
            throw new UnsupportedOperationException("Datos incompatibles");
        }
        
        
        return tablaColumn;
    }
}
