package negocio;

import datos.Curso;
import datos.Unidadeducativa;
import java.util.List;
import javax.swing.JComboBox;


/**
 * @author Billy
 * @version 1.0
 * @created 21-jun-2013 16:40:23
 */
public class nCurso {

	public Curso cursos;
        public Unidadeducativa colegio;
        

	public nCurso(){

	}


    public  void listar(JComboBox c, int codue){
    
         
    colegio = new Unidadeducativa(codue);
    cursos = new Curso(colegio);
    
    
    
    List list = cursos.Listar();
    int a = list.size();
    int i = 0;
    
    while (a>0){
        Curso trans = (Curso)list.get(i);
        
        if(trans.getUnidadeducativa().getUnidadEducativaId()==codue)
       {
            c.addItem(trans.getCursoId()+";"+trans.getGrado()+";"+trans.getNivel());
        }
        
        a--;
        i++;
        }
    }  

    

}