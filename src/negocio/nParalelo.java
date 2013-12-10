package negocio;

import datos.Curso;
import datos.Paralelo;
import java.util.List;
import javax.swing.JComboBox;


/**
 * @author Billy
 * @version 1.0
 * @created 21-jun-2013 16:40:23
 */
public class nParalelo {

	public Curso cursos;
        public Paralelo paralelos;
        

	public nParalelo(){

	}


    public  void listar(JComboBox c, int codCurso){
    
         
    cursos = new Curso(codCurso);
    paralelos = new Paralelo(cursos);
    
    
    
    List list = paralelos.Listar();
    int a = list.size();
    int i = 0;
    
    while (a>0){
        Paralelo trans = (Paralelo)list.get(i);
        
        if(trans.getCurso().getCursoId()==codCurso)
       {
            c.addItem(trans.getParaleloId()+";"+trans.getNombre());
        }
        
        a--;
        i++;
        }
    }  

    

}