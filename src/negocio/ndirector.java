package negocio;

import datos.Director;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;


/**
 * @author Billy
 * @version 1.0
 * @created 21-jun-2013 16:40:23
 */
public class ndirector {

	public Director director;
        

	public ndirector(){

	}

        
          /// --------- INSERTAR
    public void insertar(String nombre, String correo, int telefono){
           director = new Director(nombre,correo,telefono);
    try {
            director.Insertar();
            } catch (Exception ex) {
                System.err.print(ex.getMessage());
            }
    }

    /// -------- MODIFICAR 
//    public void Modificar(int cod, String nombre, float superficie, Boolean estado) {
//            objetosconservacion = new Dobjetosconservacion(nombre,superficie,estado);
//            objetosconservacion.setCodigoobjeto(cod);
//    try {
//           objetosconservacion.Modificar();
//        } catch (Exception ex) {
//            System.err.print(ex.getMessage());
//        }
//    }
//    
//    /// ------- ELIMINAR
//    public void eliminar(int cod, String nombre, float superficie, Boolean estado) {
//            objetosconservacion = new Dobjetosconservacion(nombre,superficie,false);
//            objetosconservacion.setCodigoobjeto(cod);
//    try {
//           objetosconservacion.Modificar();
//        } catch (Exception ex) {
//            System.err.print(ex.getMessage());
//        }
//    }
//
//    ///  listar
//   public DefaultTableModel Listar() {
//       objetosconservacion = new Dobjetosconservacion();
//       List list = null;
//        try {
//           list = objetosconservacion.Listar();
//        } catch (Exception ex) {
//            Logger.getLogger(ndirector.class.getName()).log(Level.SEVERE, null, ex);
//        }
//         String todos = "";
//
//        for (Iterator it = list.iterator(); it.hasNext();) {
//            Dobjetosconservacion object = (Dobjetosconservacion) it.next();
//            todos = todos + object.toString() + "/";
//        }
//         String all[]=todos.split("/");
//
//         Object[][] objetos = new Object[all.length][4];
//
//        for (int i = 0; i < all.length; i++) {
//            String tupla = all[i];
//            System.out.println(tupla);
//            String[] datos = tupla.split(",");
//            for (int j = 0; j < datos.length; j++) {
//                String dato = datos[j];
//                objetos[i][j] = dato;
//            }
//        }
//          String[] columnNames = {"ID","OBJETO","SUPERFICIE","ESTADO"};
//        DefaultTableModel dtm= new DefaultTableModel(objetos, columnNames);
//
//        return dtm;
//    }
//   
//   
//    public  void listar(JComboBox c){
//    objetosconservacion = new Dobjetosconservacion();
//    
//    List list = objetosconservacion.Listar();
//    int a = list.size();
//    int i = 0;
//    while (a>0){
//        Dobjetosconservacion trans = (Dobjetosconservacion)list.get(i);
//        
//        if(trans.getEstado()==true)
//        {
//            c.addItem(trans.getCodigoobjeto()+";"+trans.getNombre());
//        }
//        
//        a--;
//        i++;
//        }
//    }  

    

}