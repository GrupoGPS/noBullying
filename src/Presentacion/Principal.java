/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import java.awt.Desktop;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Billy
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    private int codigoue;
    
    
    public Frminiciarsesion iniciar;
    public FrmVerAlertas alertas;
    
    private JInternalFrame internalAux;
    
    
    
    public Principal() {
        initComponents();
        iniciar();  
    }
    
    
    public void iniciar()
    {
        this.setSize(700,530);
        this.setLocation(250,150);
         desabilitarmenus();
         pasteMenuItem.setVisible(false);
    }
    
    public JDesktopPane getContenedor()
    {
    return this.desktopPane;
    }
    
    public void habilitarmenus(){
        mHerramientas.setEnabled(true);
        mIniciarSesion.setEnabled(true);
        mCerrarSesion.setEnabled(true);
    }
    
    public void desabilitarmenus(){
        mHerramientas.setEnabled(false);
        mCerrarSesion.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        mIniciarSesion = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mCerrarSesion = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        mHerramientas = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("NO + BULLYING");
        setFocusable(false);
        setResizable(false);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/inicio.png"))); // NOI18N
        jLabel1.setBounds(0, 0, 710, 450);
        desktopPane.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        fileMenu.setMnemonic('f');
        fileMenu.setText("Inicio");

        mIniciarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/iconos/on.png"))); // NOI18N
        mIniciarSesion.setMnemonic('o');
        mIniciarSesion.setText("Iniciar Sesion");
        mIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mIniciarSesionActionPerformed(evt);
            }
        });
        fileMenu.add(mIniciarSesion);

        saveMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/iconos/ubica.png"))); // NOI18N
        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Ir A Pagina Web");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenuItem);
        fileMenu.add(jSeparator1);

        mCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/iconos/logout.png"))); // NOI18N
        mCerrarSesion.setText("Cerrar Sesion");
        mCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mCerrarSesionActionPerformed(evt);
            }
        });
        fileMenu.add(mCerrarSesion);

        exitMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/iconos/off.png"))); // NOI18N
        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Salir");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        mHerramientas.setMnemonic('e');
        mHerramientas.setText("Herramientas");

        cutMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/iconos/buscar.PNG"))); // NOI18N
        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Ver Alertas de Bullying");
        cutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cutMenuItemActionPerformed(evt);
            }
        });
        mHerramientas.add(cutMenuItem);

        pasteMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/iconos/contrato.png"))); // NOI18N
        pasteMenuItem.setMnemonic('p');
        pasteMenuItem.setText("Reportes");
        pasteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasteMenuItemActionPerformed(evt);
            }
        });
        mHerramientas.add(pasteMenuItem);

        menuBar.add(mHerramientas);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Ayuda");

        contentMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/iconos/prof.png"))); // NOI18N
        contentMenuItem.setMnemonic('c');
        contentMenuItem.setText("Ayuda de Contenidos");
        contentMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contentMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(contentMenuItem);

        aboutMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/iconos/info.png"))); // NOI18N
        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("Acerca de...");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        // TODO add your handling code here:
          abrirenlace("http://localhost/nomasbullying/");
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void mIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mIniciarSesionActionPerformed
        // TODO add your handling code here:
        iniciar = new Frminiciarsesion();
        desktopPane.add(iniciar);
        iniciar.setPrincipal(this);
        iniciar.show(true);
    }//GEN-LAST:event_mIniciarSesionActionPerformed

    private void cutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cutMenuItemActionPerformed
        // TODO add your handling code here:
        if (estaAbierto("alertasdebullying") != -1) {
            ponerAlFrente();
        } else {
            alertas = new FrmVerAlertas();
            alertas.setCodue(this.getCodigoue());
            alertas.setName("alertasdebullying");
            alertas.setTitle("Alertas de Bullying");
            alertas.setFormpricipal(this);
            alertas.setIconifiable(Boolean.TRUE);
            desktopPane.add(alertas);           
            alertas.show();
        }
        
    }//GEN-LAST:event_cutMenuItemActionPerformed

    private void contentMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contentMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contentMenuItemActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        // TODO add your handling code here:
         if (estaAbierto("acercade") != -1) {
            ponerAlFrente();
        } else {
            FrmAcercade acercade = new FrmAcercade();
            acercade.setName("acercade");
            acercade.setTitle("Acerca de...");
            acercade.setIconifiable(Boolean.TRUE);
            acercade.setClosable(true);
            desktopPane.add(acercade);
            acercade.show();
        }
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void pasteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasteMenuItemActionPerformed
        // TODO add your handling code here:
        Reportes reporte = new Reportes();
        reporte.show();
    }//GEN-LAST:event_pasteMenuItemActionPerformed

    private void mCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mCerrarSesionActionPerformed
        // TODO add your handling code here:
        
    int confirmado = JOptionPane.showConfirmDialog(this,"¿Esta seguro(a) que desea cerrar su sesion?","CONFIRMACION",0); 
    if (JOptionPane.OK_OPTION == confirmado)
        {   
        this.codigoue = 0;
        desabilitarmenus();
        }
        
    }//GEN-LAST:event_mCerrarSesionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Principal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem contentMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem mCerrarSesion;
    private javax.swing.JMenu mHerramientas;
    private javax.swing.JMenuItem mIniciarSesion;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the codigoue
     */
    public int getCodigoue() {
        return codigoue;
    }

    /**
     * @param codigoue the codigoue to set
     */
    public void setCodigoue(int codigoue) {
        this.codigoue = codigoue;
    }
    

    /////////////////////
    public void abrirenlace(String ruta){
        Desktop desktop = Desktop.getDesktop();
            URI uri;
        try {
            uri = new URI(ruta);
            try {
                desktop.browse(uri);
            } catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    
    public int estaAbierto(String nombreformulario){
        JInternalFrame v[]= desktopPane.getAllFrames();
        int i=0;
        int pos=-1;
        boolean sw=true;
        if(v.length>0){
            while((i<v.length)&&(sw)){
                String nomV=v[i].getName();
                if(nomV.equals(nombreformulario)){
                    sw=false;
                    pos=i;
                    internalAux=v[i];
                }
                i++;
            }
        }
        return pos;
    }
    
    
    public void ponerAlFrente(){
        try {
            internalAux.setIcon(Boolean.FALSE);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        internalAux.toFront();
    }
    
    
    
    
    
    
    
}