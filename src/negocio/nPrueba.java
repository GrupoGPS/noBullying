package negocio;

import datos.Alerta;
import datos.Prueba;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 * @author Billy
 * @version 1.0
 * @created 21-jun-2013 16:40:23
 */
public class nPrueba {

	public Prueba pruebas;
        

	public nPrueba(){

	}


    ///  listar
   public DefaultTableModel Listar(int idAlerta) {
       
       Alerta alert = new Alerta(idAlerta);
       
       
       pruebas = new Prueba();
       List list = null;
        try {
           
           list = pruebas.Listar(alert);
        } catch (Exception ex) {
            Logger.getLogger(nPrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
         String todos = "";

        for (Iterator it = list.iterator(); it.hasNext();) {
            Prueba object = (Prueba) it.next();
            todos = todos + object.toString() + "/";
        }
         String all[]=todos.split("/");

         Object[][] objetos = new Object[all.length][2];

        for (int i = 0; i < all.length; i++) {
            String tupla = all[i];
            System.out.println(tupla);
            String[] datos = tupla.split(",");
            for (int j = 0; j < datos.length; j++) {
                String dato = datos[j];
                objetos[i][j] = dato;
            }
        }
          String[] columnNames = {"ID","IMAGEN"};
        DefaultTableModel dtm= new DefaultTableModel(objetos, columnNames);

        return dtm;
    }
   
   
  public byte[] devolversimbolo(int idima){
   Prueba a = new Prueba();
                               
     try {
            a = (Prueba) Prueba.buscar("Prueba","pruebaId",idima, true);
            //JOptionPane.showMessageDialog(null,idsim);
            System.out.print("se encontro requisito");
        } catch (Exception ex) {
           System.err.print(ex.getMessage());
        }
     
     return a.getImagen();
   }

    

}