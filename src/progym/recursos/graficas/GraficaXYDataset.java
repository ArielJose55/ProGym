/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.recursos.graficas;

import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.general.DefaultKeyedValues2DDataset;
import progym.logica.basica.entidades.Rutina;
import progym.logica.basica.entidades.Serie;
import progym.logica.basica.util.Fecha;

/**
 *
 * @author Ariel Jose Arnedo
 */
public class GraficaXYDataset {
    
    public GraficaXYDataset(){
        xyDatos = new DefaultKeyedValues2DDataset();
        datos = new ArrayList();
        titulo = "Grafica Contunua de Puntos";
    }
    
    public GraficaXYDataset(String titulo){
        xyDatos = new DefaultKeyedValues2DDataset();
        datos = new ArrayList();
        this.titulo = titulo;
    }
    
    public GraficaXYDataset(ArrayList datos){
        xyDatos = new DefaultKeyedValues2DDataset();
        this.datos = datos;
        titulo = "Grafica Contunua de Puntos";
    }
    
    private void setTitulo(String titulo){
        this.titulo = titulo;
    }
    
    public ArrayList getDatos(){
         return datos;
    }
    
    public void addAllDato(ArrayList datos){
        this.datos.addAll(datos);
    }
    
    public ChartPanel createChartPanel(){
        return new ChartPanel(getChart());
    }
    
    private JFreeChart getChart(){
        if(datos == null || datos.isEmpty()) throw new UnsupportedOperationException("Coleccion de datos para la graficas es null o esta vacio");
        
        if(datos.get(0) instanceof Serie){
            
            for(int i = 0 ; i < datos.size() ; i++){
                Serie aux = (Serie)datos.get(i);
                xyDatos.addValue((((aux.getRepe1() + aux.getRepe2() + aux.getRepe3() + aux.getRepe4()) * 100)/(48)), "Rendimiento", aux.getCodigo()+"");
            }
            
            grafica = ChartFactory.createLineChart3D(titulo, "Codigo de Serie", "Porcentaje de Rendimiento", xyDatos, PlotOrientation.VERTICAL, true, false, false);
        
            return grafica;
            
        }else if(datos.get(0) instanceof Rutina){
            
            for(int i = 0 ; i < datos.size() ; i++){
                Rutina aux = (Rutina)datos.get(i);
                int value = 0;
                for(Serie serie : aux.getSeries()){
                    value += (serie.getRepe1()+serie.getRepe2()+serie.getRepe3()+serie.getRepe4());
                }
                xyDatos.addValue(value, "Repeticiones", Fecha.getNameBreveFechaRutina(aux.getCodigo()));
            }
            
            grafica = ChartFactory.createLineChart3D(titulo, "Fecha de Realizacion de la Rutina", "Cantidad de Repeticiones", xyDatos, PlotOrientation.VERTICAL, true, false, false);
            return grafica;
            
        }else{
            throw new UnsupportedOperationException("Datos no compatible con el modelo de grafica");
        }
    }
    
    private String titulo;
    private ArrayList datos;
    private DefaultKeyedValues2DDataset xyDatos;
    private JFreeChart grafica;
}
