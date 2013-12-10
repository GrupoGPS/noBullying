package negocio;

import datos.Alerta;
import datos.Director;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


/**
 * @author Billy
 * @version 1.0
 * @created 21-jun-2013 16:40:23
 */
public class nAlerta {

	public Alerta alerta;
        

	public nAlerta(){

	}


    ///  listar
   public DefaultTableModel Listar() {
       alerta = new Alerta();
       List list = null;
        try {
           list = alerta.Listar();
        } catch (Exception ex) {
            Logger.getLogger(nAlerta.class.getName()).log(Level.SEVERE, null, ex);
        }
         String todos = "";

        for (Iterator it = list.iterator(); it.hasNext();) {
            Alerta object = (Alerta) it.next();
            todos = todos + object.toString() + "/";
        }
         String all[]=todos.split("/");

         Object[][] objetos = new Object[all.length][4];

        for (int i = 0; i < all.length; i++) {
            String tupla = all[i];
            System.out.println(tupla);
            String[] datos = tupla.split(",");
            for (int j = 0; j < datos.length; j++) {
                String dato = datos[j];
                objetos[i][j] = dato;
            }
        }
          String[] columnNames = {"ID","DESCRIPCION","VICTIMA","AGRESOR"};
        DefaultTableModel dtm= new DefaultTableModel(objetos, columnNames);

        return dtm;
    }

    

}