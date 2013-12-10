package datos;
// Generated 06-dic-2013 15:07:58 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Estudiante generated by hbm2java
 */
public class Estudiante  implements java.io.Serializable {


     private Integer estudianteId;
     private Paralelo paralelo;
     private String nombreCompleto;
     private String correo;
     private int telefono;
     private String fechaNacimiento;
     private Set alertasForAgresorId = new HashSet(0);
     private Set alertasForVictimaId = new HashSet(0);

    public Estudiante() {
    }

	
    public Estudiante(Paralelo paralelo, String nombreCompleto, String correo, int telefono, String fechaNacimiento) {
        this.paralelo = paralelo;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
    }
    public Estudiante(Paralelo paralelo, String nombreCompleto, String correo, int telefono, String fechaNacimiento, Set alertasForAgresorId, Set alertasForVictimaId) {
       this.paralelo = paralelo;
       this.nombreCompleto = nombreCompleto;
       this.correo = correo;
       this.telefono = telefono;
       this.fechaNacimiento = fechaNacimiento;
       this.alertasForAgresorId = alertasForAgresorId;
       this.alertasForVictimaId = alertasForVictimaId;
    }
   
    public Integer getEstudianteId() {
        return this.estudianteId;
    }
    
    public void setEstudianteId(Integer estudianteId) {
        this.estudianteId = estudianteId;
    }
    public Paralelo getParalelo() {
        return this.paralelo;
    }
    
    public void setParalelo(Paralelo paralelo) {
        this.paralelo = paralelo;
    }
    public String getNombreCompleto() {
        return this.nombreCompleto;
    }
    
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    public String getCorreo() {
        return this.correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public int getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    public String getFechaNacimiento() {
        return this.fechaNacimiento;
    }
    
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public Set getAlertasForAgresorId() {
        return this.alertasForAgresorId;
    }
    
    public void setAlertasForAgresorId(Set alertasForAgresorId) {
        this.alertasForAgresorId = alertasForAgresorId;
    }
    public Set getAlertasForVictimaId() {
        return this.alertasForVictimaId;
    }
    
    public void setAlertasForVictimaId(Set alertasForVictimaId) {
        this.alertasForVictimaId = alertasForVictimaId;
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


