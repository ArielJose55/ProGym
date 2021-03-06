/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progym.vista.dialogos;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import progym.logica.basica.daos.RutinaController;
import progym.logica.basica.daos.SerieController;
import progym.logica.basica.util.IdentifyRutina;
import progym.logica.basica.util.RecuperatorOfXMLSerie;
import progym.logica.modelos.ModeloCampoTexto;
import progym.recursos.graficas.GraficaXYDataset;


/**
 *
 * @author Klac
 */
public class DialogoChartRendimiento extends javax.swing.JDialog {

    /**
     * Creates new form DialogoChartRendimiento
     */
    public DialogoChartRendimiento(java.awt.Frame parent) {
        super(parent,true);
        this.setTitle("Progresibidad");
        this.setIconImage(parent.getIconImage());
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jocPaneGroup1 = new com.xzq.osc.JocPaneGroup();
        jocBusyIcon1 = new com.xzq.osc.JocBusyIcon();
        panelPestaña = new javax.swing.JTabbedPane();
        panelProRutina = new javax.swing.JPanel();
        panelDesktopRutina = new org.jdesktop.swingx.JXTitledPanel();
        panelProSerie = new javax.swing.JPanel();
        panelDesktopSerie = new org.jdesktop.swingx.JXTitledPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        jocPaneGroup1.setAllCollapseAllowed(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panelProRutina.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints confi2 = new java.awt.GridBagConstraints();
        confi2.anchor = java.awt.GridBagConstraints.NORTH;
        confi2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        confi2.insets = new java.awt.Insets(5, 0, 5, 0);
        confi2.gridx = 0;
        confi2.gridy = 0;
        confi2.weightx = 1.0;

        panelProRutina.add(createContainerRutina(), confi2);

        javax.swing.GroupLayout panelDesktopRutinaLayout = new javax.swing.GroupLayout(panelDesktopRutina.getContentContainer());
        panelDesktopRutina.getContentContainer().setLayout(panelDesktopRutinaLayout);
        panelDesktopRutinaLayout.setHorizontalGroup(
            panelDesktopRutinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 943, Short.MAX_VALUE)
        );
        panelDesktopRutinaLayout.setVerticalGroup(
            panelDesktopRutinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 431, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        panelProRutina.add(panelDesktopRutina, gridBagConstraints);

        panelPestaña.addTab("Progresibidad en Rutina", panelProRutina);

        panelProSerie.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints confi = new java.awt.GridBagConstraints();
        confi.anchor = java.awt.GridBagConstraints.NORTH;
        confi.fill = java.awt.GridBagConstraints.HORIZONTAL;
        confi.insets = new java.awt.Insets(5, 0, 5, 0);
        confi.gridx = 0;
        confi.gridy = 0;
        confi.weightx = 1.0;

        panelProSerie.add(createContainerSerie(), confi);

        javax.swing.GroupLayout panelDesktopSerieLayout = new javax.swing.GroupLayout(panelDesktopSerie.getContentContainer());
        panelDesktopSerie.getContentContainer().setLayout(panelDesktopSerieLayout);
        panelDesktopSerieLayout.setHorizontalGroup(
            panelDesktopSerieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 943, Short.MAX_VALUE)
        );
        panelDesktopSerieLayout.setVerticalGroup(
            panelDesktopSerieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 431, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        panelProSerie.add(panelDesktopSerie, gridBagConstraints);

        panelPestaña.addTab("Progresibidad en Serie", panelProSerie);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jButton1.setText("jButton1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.2;
        jPanel1.add(jButton1, gridBagConstraints);

        panelPestaña.addTab("tab3", jPanel1);

        getContentPane().add(panelPestaña, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
       
    }//GEN-LAST:event_formWindowOpened

    private void campoSeleccionSeriePerformed(ActionEvent evt){
       final JComponent compo = (JComponent)evt.getSource();
       compo.setEnabled(false);
       final Runnable runnable = new Runnable(){
           @Override
           public void run(){
               final GraficaXYDataset grafica;
               try{
                   grafica = new GraficaXYDataset(campo.getText());
                   grafica.addAllDato(new SerieController().getSerieNombre(campo.getText()));
               }catch(NullPointerException ex){
                   getToolkit().beep();
                   return;
               }catch(SQLException ex){
                   JOptionPane.showMessageDialog(panelDesktopSerie, ex.getMessage());
                   return;
               }
               
               panelDesktopSerie.setContentContainer(grafica.createChartPanel());
               pack();
               compo.setEnabled(true);
           }
       };
       
       Thread hilito = new Thread(runnable);
       hilito.start();
    }
    
    private void radioButtonSelectionPerformed(final ActionEvent evt){
        final Runnable runnable = new Runnable(){
          @Override
          public void run(){
              GraficaXYDataset grafica;
              try{
                  grafica = new GraficaXYDataset(((AbstractButton)evt.getSource()).getText());
                  grafica.addAllDato(new RutinaController().getRutinaNombre(((AbstractButton)evt.getSource()).getText()));
              }catch(SQLException ex){
                  JOptionPane.showMessageDialog(panelProRutina, ex.getMessage());
                  return;
              }
              panelDesktopRutina.add(grafica.createChartPanel(), BorderLayout.CENTER);
              pack();
          }  
        };
        Thread hilito  = new Thread(runnable);
        hilito.start();
    }
    
    
    private JTextField campo;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private com.xzq.osc.JocBusyIcon jocBusyIcon1;
    private com.xzq.osc.JocPaneGroup jocPaneGroup1;
    private org.jdesktop.swingx.JXTitledPanel panelDesktopRutina;
    private org.jdesktop.swingx.JXTitledPanel panelDesktopSerie;
    private javax.swing.JTabbedPane panelPestaña;
    private javax.swing.JPanel panelProRutina;
    private javax.swing.JPanel panelProSerie;
    // End of variables declaration//GEN-END:variables


    private Container createContainerSerie(){
        JPanel panel = new JPanel(new AbsoluteLayout());
        try{
            campo = new JTextField(20);
            final ModeloCampoTexto CAMPO_MODEL = new ModeloCampoTexto(new RecuperatorOfXMLSerie().getAllRutinas(), campo);
            campo.setDocument(CAMPO_MODEL);
            campo.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt){
                    campoSeleccionSeriePerformed(evt);
                }
            });
        }catch(Exception ex){
            campo = new JTextField();
        }
        
        JLabel eti = new JLabel("Ingrese una Serie ", SwingConstants.RIGHT);
        eti.setForeground(new Color(120, 100, 120));
        eti.setFont(new Font("Arial", Font.BOLD, 12));
        
        panel.add(eti, new AbsoluteConstraints(150, 20, 200, 26));
        panel.add(campo, new AbsoluteConstraints(360, 20, 200, 26));
        
        JButton boton = new JButton("Generar Grafica");
        boton.setFont(new Font("Arial", Font.BOLD, 12));
        boton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt){
                    campoSeleccionSeriePerformed(evt);
                }
            });
        panel.add(boton, new AbsoluteConstraints(570, 20, 120, 26));
        
        ImageIcon image = new ImageIcon(getClass().getResource("/progym/recursos/imagenes/icon_TituloProSerie.png"));
        panel.add(new JLabel(image), new AbsoluteConstraints(0,0,-1,-1));
        return panel;
    }
    
    private Container createContainerRutina(){
        JPanel panel = new JPanel(new AbsoluteLayout());
        ButtonGroup grupo = new ButtonGroup();
        try{
            int cont = 0;
            int fila = 4;
            int columna = 200;
            for(String rutina : IdentifyRutina.getIdentitifyRutina().getNameRutinas()){
                JRadioButton boton = new JRadioButton(rutina);
                boton.setBackground(new Color(200, 200, 200));
                boton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent evt){
                        radioButtonSelectionPerformed(evt);
                    }
                });
                grupo.add(boton);
                panel.add(boton,new AbsoluteConstraints(columna, fila,-1,-1));
                columna += 120;
                cont++;
                if(cont % 5 == 0){
                    fila += 28;
                    columna = 200;
                }
            }
        }catch(Exception ex){
            JLabel eti = new JLabel("ERROR AL OBTENER LAS RUTINAS");
            eti.setForeground(Color.red);
            panel.add(eti,new AbsoluteConstraints(250, 20, -1, -1));
        }
        
        ImageIcon image = new ImageIcon(getClass().getResource("/progym/recursos/imagenes/icon_TituloProSerie.png"));
        panel.add(new JLabel(image), new AbsoluteConstraints(0,0,-1,-1));
        return panel;
    }
}

