/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.recursos.reportes;

import java.net.URL;
import java.util.ArrayList;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import progym.logica.basica.entidades.Rutina;
import progym.logica.basica.entidades.Serie;

/**
 *La Clase <code>ReporteDataSource</code> implementa de <code>JRDataSource</code>
 * 
 * 
 * @author Ariel Jose Arnedo
 */
public class ReporteDataSource implements JRDataSource{

    /**
     * Constructor
     * 
     * @param datos un ArrayList de datos, el cual sera el modelo para el reporte
     */
    public ReporteDataSource(ArrayList datos) {
        this.datos = datos;
        contador=-1;
    }
    /**
     * 
     * @return
     * @throws JRException 
     */
    @Override
    public boolean next() throws JRException {
        return ++contador < datos.size();
    } 

    /**
     * 
     * @return 
     */
    public URL getReportURL(){
        return getClass().getResource("/progym/recursos/reportes/RereporteRutinaActual.jasper");
    }
    
    /**
     * 
     * @param jrf
     * @return
     * @throws JRException 
     */
    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
 
        switch(jrf.getName()){
            case "codigo": return datos.get(contador).getCodigo();
            case "nombre": return datos.get(contador).getNombre();
            case "repe1":  return datos.get(contador).getRepe1();
            case "repe2":  return datos.get(contador).getRepe2();
            case "repe3":  return datos.get(contador).getRepe4();
            case "repe4":  return datos.get(contador).getRepe4();
            default: return null;
        }
    }
    
    
    
    private ArrayList<Serie> datos;
    private int contador;
}
