/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.logica.modelos;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Klac
 */
public class ModeloCampoTexto extends PlainDocument{

    private final ActionSimpleListener ACTION_SIMPLE = new ActionSimpleListener();
    
    public ModeloCampoTexto(ArrayList<String> datos, JComponent componente){
        super();
        value = new StringBuilder();
        this.datos = datos;
        this.componente = componente;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if(str == null) return;
        value.append(str);
        showJPopupMenu();
        super.insertString(offs, str, a); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void removeUpdate(DefaultDocumentEvent chng) {
        try {
            value.delete(value.lastIndexOf(chng.getDocument().getText(chng.getOffset(), chng.getLength())), value.length());
            showJPopupMenu();
        }catch (Exception ex){
            return;
        }
        super.removeUpdate(chng); //To change body of generated methods, choose Tools | Templates.
    }
    
    private synchronized void showJPopupMenu(){
        try {
            createPopupMenu(value).show(componente, 0, 20);
        }finally{
            return;
        }
    }
    
    private JPopupMenu createPopupMenu(StringBuilder str){
        if(datos !=null && !datos.isEmpty() && str.length() != 0){
             return iniPopupMenu(new JPopupMenu(), str.toString());
        }
        return null;
    }
    
    private ArrayList<String> filtro(String str){
        final ArrayList<String> lista = new ArrayList<>();
        int tamaño =str.length();
        
        for(String aux : datos){
            if(str.equalsIgnoreCase(aux.substring(0, tamaño))){
                lista.add(aux);

            }
        }
        
        return lista;
    }
    
    private JPopupMenu iniPopupMenu(JPopupMenu menu,String clave){
        int i = 0;
        for(;i< filtro(clave).size(); i++){
            JMenuItem item = new JMenuItem(filtro(clave).get(i));
            item.addActionListener(ACTION_SIMPLE);
            menu.add(item);
        }
        menu.setPreferredSize(new Dimension(componente.getWidth(), (20*i)));
        menu.setBorderPainted(false);
        menu.setFocusable(false);
        return  menu;
    }
    
    private class ActionSimpleListener implements ActionListener{
        public void actionPerformed(java.awt.event.ActionEvent evt){
            ((JTextField)componente).setText(((JMenuItem)evt.getSource()).getText());
        }
    }
    
    private StringBuilder value;
    private  JComponent componente;
    private ArrayList<String> datos;
}
