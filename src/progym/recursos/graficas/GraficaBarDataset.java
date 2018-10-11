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
import progym.logica.basica.entidades.Rutina;
import progym.logica.basica.entidades.Serie;


/**
 *
 * @author Ariel Jose Arnedo
 */
public class GraficaBarDataset {

        
    public GraficaBarDataset(String titulo){
        colection = new DefaultCategoryDataset();
        this.titulo = titulo;
        this.datos = new ArrayList();
    }
    
    /**
     * 
     * @param datos 
     */
    public GraficaBarDataset(ArrayList datos,String titulo) {
        colection = new DefaultCategoryDataset();
        this.titulo=titulo;
        this.datos = datos;
        inicializaDatosAtChart();
    }
    
    public void setDatos(ArrayList datos){
        this.datos=datos;
        inicializaDatosAtChart();
    }
    
    
    public ChartPanel getGrafica( String dominio){
        return  new ChartPanel(grafica);
    }
    
    public void setDatos(Object objeto){
        datos.add(objeto);
        inicializaDatosAtChart();
    }
    
    private void inicializaDatosAtChart(){
        if(datos !=null && !datos.isEmpty() && datos.get(0) instanceof Rutina){
            for( int i = 0 ; i < datos.size() ; i++){
                Rutina rutina = (Rutina)datos.get(i);
                
                for(Serie aux : rutina.getSeries()){
                    colection.addValue(aux.getRepe1(), "1° Repeticion", aux.getCodigo()+" "+aux.getNombre());
                    colection.addValue(aux.getRepe2(), "2° Repeticion", aux.getCodigo()+" "+aux.getNombre());
                    colection.addValue(aux.getRepe3(), "3° Repeticion", aux.getCodigo()+" "+aux.getNombre());
                    colection.addValue(aux.getRepe4(), "4° Repeticion", aux.getCodigo()+" "+aux.getNombre());
                }
            }
            grafica = ChartFactory.createBarChart3D(titulo, "Fecha de Serie", "N° de Repeticiones", colection, PlotOrientation.VERTICAL, true, false, false);
        }else if(datos !=null && !datos.isEmpty() && datos.get(0) instanceof Serie){
            for( int i = 0 ; i < datos.size() ; i++){
                Serie serie = (Serie)datos.get(i);
                int valor = (serie.getRepe2() + serie.getRepe2() + serie.getRepe3() + serie.getRepe4());
                colection.addValue(valor, serie.getNombre(), serie.getCodigo()+"");             
            }
        }
        
    }
    
    
    
    private String titulo;
    private JFreeChart grafica;
    private ArrayList datos;
    private DefaultCategoryDataset colection;
}
