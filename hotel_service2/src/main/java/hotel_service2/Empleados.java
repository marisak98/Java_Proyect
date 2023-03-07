package hotel_service2;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.Hibernate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import mariadb.Mariadb_connect;

@Entity
@Table (name = "EMPLEADO")
public class Empleados {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_EMPLEADO")
	private Long idEmpleado;
	
	@Column(name = "NOM_EMPLEADO")
	private String nomEmpleado;
	
	
	@Column (name = "DIR_EMPLEADO")
	private String dirEmp;
	
	@Column (name = "TEL_EMPLEADO")
	private String telfEmp;
	
	@Column(name = "EMAIL_EMPLEADO")
	private String emailEmp;
	
	@Column(name = "USER_EMPLEADO")
	private String user;
	
	@Column(name = "PASS_EMPLEADO")
	private String pass;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "ID_PAGOS", referencedColumnName = "ID_PAGOS")
    private Pagos pago;

	
	/// Contructores

	public Empleados(Long idEmpleado, String nomEmpleado, String dirEmp, String telfEmp, String emailEmp, String user, String pass,
			Pagos pago) {
		super();
		this.nomEmpleado = nomEmpleado;
		this.idEmpleado = idEmpleado;
		this.dirEmp = dirEmp;
		this.telfEmp = telfEmp;
		this.emailEmp = emailEmp;
		this.user = user;
		this.pass = pass;
		this.pago = pago;
	}


	public Empleados(String nomEmpleado, String dirEmp, String telfEmp, String emailEmp, String user, String pass, Pagos pago) {
		this.nomEmpleado = nomEmpleado;
		this.dirEmp = dirEmp;
		this.telfEmp = telfEmp;
		this.emailEmp = emailEmp;
		this.user = user;
		this.pass = pass;
		this.pago = pago;
	}


	public Empleados() {

	}

	///Getters y SEtter


	public Long getIdEmpleado() {
		return idEmpleado;
	}


	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}


	public String getDirEmp() {
		return dirEmp;
	}


	public void setDirEmp(String dirEmp) {
		this.dirEmp = dirEmp;
	}


	public String getTelfEmp() {
		return telfEmp;
	}


	public void setTelfEmp(String telfEmp) {
		this.telfEmp = telfEmp;
	}


	public String getEmailEmp() {
		return emailEmp;
	}


	public void setEmailEmp(String emailEmp) {
		this.emailEmp = emailEmp;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public Pagos getPago() {
		return pago;
	}


	public void setPago(Pagos pago) {
		this.pago = pago;
	}
	
	
public String getNomEmpleado() {
		return nomEmpleado;
	}


	public void setNomEmpleado(String nomEmpleado) {
		this.nomEmpleado = nomEmpleado;
	}


	// Metodos Tipo
	public void crear(Empleados empleados) {
		
		EntityManager entity = Mariadb_connect.getEntityManagerFactory().createEntityManager();
		try {
			entity.getTransaction().begin();
			Pagos pago = entity.find(Pagos.class, empleados.getPago().getIdPagos());
			if (pago == null) {
			System.out.println("No se encontro el Pago con ese ID:" +  empleados.getPago().getIdPagos());
			return;
			}
			
			empleados.setPago(pago);
			
			entity.persist(empleados);
			entity.getTransaction().commit();
			
		} catch (Exception e) {
			System.out.println("Error:" + e);
		} finally {
			entity.close();
		}
		
	}
	
	public List<Empleados> buscar(Long idEmp){
		EntityManager entity = Mariadb_connect.getEntityManagerFactory().createEntityManager();
		List<Empleados> listaEmp = new ArrayList<>();
		try {
			 String jpql = "SELECT e FROM Empleados e JOIN e.pago  p  WHERE 1=1";
		        if (idEmp != null) {
		            jpql += " AND e.idEmpleado = :id";
		        }
		        Query query = entity.createQuery(jpql, Empleados.class);
		        if (idEmp != null) {
		            query.setParameter("id", idEmp);
		            
		        }
		        
		        listaEmp = query.getResultList();
		        if(listaEmp.isEmpty()) {
		        	System.out.println("[-] No se encontro el pago en ese Id.");
		        }else {
		        	for(Empleados emp : listaEmp) {
		        		System.out.println("Pago: " + emp.getPago().getIdPagos());
		        		System.out.println("ID_EMPLEADO" + emp.getIdEmpleado());
		               
		        		Hibernate.initialize(emp.getPago());
		        		
		        	}
		        }
			
		} catch (Exception e) {
			System.out.println("[!] Error al buscar el EMpleado: " + e);
		} finally {
			entity.close();
		}
		
		
		
		return listaEmp; 
		
		
	}
	
	public void actualizar(Long idEmpleado, Empleados empleado) {
		EntityManager  entity= Mariadb_connect.getEntityManagerFactory().createEntityManager();
		entity.getTransaction().begin();
		try {
			Empleados emple = entity.find(Empleados.class, idEmpleado);
			if (emple != null) {
			
				
				emple.setNomEmpleado(empleado.getNomEmpleado());
				emple.setDirEmp(empleado.getNomEmpleado());
				emple.setTelfEmp(empleado.getTelfEmp());
				emple.setEmailEmp(empleado.getEmailEmp());
				emple.setUser(empleado.getUser());
				emple.setPass(empleado.getPass());
			
				entity.merge(emple);
				entity.getTransaction().commit();
				System.out.println("[+] Empleado actualizado con exito!");

			
			}else {
				System.out.println("[-] ID del Empleado no encotrado.");

			}
			
		} catch (Exception e) {
			System.out.println("Error: " + e);
			
		}finally {
			entity.close();
		}
		
	}
	
	public void eliminar(List <Long> idEmpleado) {
		
		EntityManager entity = Mariadb_connect.getEntityManagerFactory().createEntityManager();
		try {
			entity.getTransaction().begin();
			Integer registroEliminado = 0;
			for (Long emplea : idEmpleado){
			Empleados empl = entity.find(Empleados.class, emplea);
			
			if (empl != null) {
				entity.remove(empl);
				registroEliminado++;
			}
			}
			entity.getTransaction().commit();
			System.out.println("Se eliminaron " + registroEliminado + " Registros");
			
		} catch (Exception e) {
			System.out.println("Error" + e);
			entity.getTransaction().rollback();
		} finally {
			entity.close();
		}
	}
	
}
