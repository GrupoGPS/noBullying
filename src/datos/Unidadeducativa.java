package datos;
// Generated 06-dic-2013 15:07:58 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Unidadeducativa generated by hbm2java
 */
public class Unidadeducativa  implements java.io.Serializable {


     private Integer unidadEducativaId;
     private Director director;
     private String nombre;
     private String distrito;
     private String direccion;
     private Set cursos = new HashSet(0);

    public Unidadeducativa() {
    }
    
    public Unidadeducativa(int codUE) {
        this.unidadEducativaId = codUE;
    }

	
    public Unidadeducativa(Director director, String nombre, String distrito, String direccion) {
        this.director = director;
        this.nombre = nombre;
        this.distrito = distrito;
        this.direccion = direccion;
    }
    public Unidadeducativa(Director director, String nombre, String distrito, String direccion, Set cursos) {
       this.director = director;
       this.nombre = nombre;
       this.distrito = distrito;
       this.direccion = direccion;
       this.cursos = cursos;
    }
   
    public Integer getUnidadEducativaId() {
        return this.unidadEducativaId;
    }
    
    public void setUnidadEducativaId(Integer unidadEducativaId) {
        this.unidadEducativaId = unidadEducativaId;
    }
    public Director getDirector() {
        return this.director;
    }
    
    public void setDirector(Director director) {
        this.director = director;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDistrito() {
        return this.distrito;
    }
    
    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public Set getCursos() {
        return this.cursos;
    }
    
    public void setCursos(Set cursos) {
        this.cursos = cursos;
    }

    ////////////////
    public List Listar() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(this.getClass());
        List list = criteria.list();
        return list;
    }

    @Override
    public String toString() {
        return unidadEducativaId+","+nombre+","+distrito;
    }
    
    
    public static Object buscar(String clase, String atributo, Object valor, boolean usarComillas) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (usarComillas) {
            valor = "'" + valor + "'";
        }
        Query query = session.createQuery("FROM " + clase + " AS C WHERE C." + atributo + " = " + valor);
        Object object = query.uniqueResult();
        session.close();
        return object;
    }

}

