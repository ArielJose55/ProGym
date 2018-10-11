/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.logica.basica.util.threads;


import org.jdesktop.swingx.JXEditorPane;

/**
 *
 * @author Klac
 */
public class ThreadPanelEscritor extends Thread{

    
    public ThreadPanelEscritor(JXEditorPane panelEditor) {
        this.panelEditor = panelEditor;
        this.panelEditor.setText("");
        empiezaThread();
    }
    
    public void espera(){
        waitThread();
    }
    
    public void despierta(String texto){
        this.texto = texto;
        notifyThread();
    }
    
    @Override
    public void run() {
        while (true) {
            try{
                if(texto.compareTo("") != 0){
                    panelEditor.setText(texto+"\n"+panelEditor.getText());
                }
                espera();
            }finally{
                continue;
            }
        }
    }
    
    
    private String texto;
    private JXEditorPane panelEditor;
    
    
    //----------------------------------------------------
    
    private void duerme(long segundos) throws InterruptedException{
        Thread.sleep(segundos);
    } 
    
    private void empiezaThread(){
        this.start();
    }
    
    private synchronized void notifyThread(){
        this.notify();
    }
    
    private synchronized void waitThread(){
        try {
            this.wait();
        } catch (InterruptedException ex) {}
    }

}