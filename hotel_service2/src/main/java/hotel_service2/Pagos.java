package hotel_service2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*
;

import org.hibernate.Hibernate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import mariadb.Mariadb_connect;

@Entity
@Table(name = "PAGOS")
public class Pagos {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_PAGOS")
	private Long idPagos;
	
	@Column(name="NUMRES_PAGOS")
 	private Integer numPagos;
	
	@Column(name = "FECHA_PAGOS")
	private Date datePagos;
	
	@Column(name = "MONT_PAGOS")
	private Integer montPago;
	
	@Column(name = "FORM_PAGOS")
	private String formPagos;
	
	@Column(name = "OBS_PAGOS")
	private String observaciones;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "ID", referencedColumnName = "ID")
    private Habitaciones habitacion;

	@OneToMany(mappedBy = "pago", cascade = CascadeType.ALL)
	private List<Empleados> empleados = new ArrayList<>();
	//Constructores

	public Pagos(Long idPagos, Integer numPagos, Date datePagos, Integer montPago, String formPagos,
			String observaciones, Habitaciones habitacion) {
		
		this.idPagos = idPagos;
		this.numPagos = numPagos;
		this.datePagos = datePagos;
		this.montPago = montPago;
		this.formPagos = formPagos;
		this.observaciones = observaciones;
		this.habitacion = habitacion;
	}

	public Pagos(Integer numPagos, Date datePagos, Integer montPago, String formPagos, String observaciones,
			Habitaciones habitacion) {
		
		this.numPagos = numPagos;
		this.datePagos = datePagos;
		this.montPago = montPago;
		this.formPagos = formPagos;
		this.observaciones = observaciones;
		this.habitacion = habitacion;
	}

	public Pagos() {
		
	}
	
	//Setter y Getters


	public Long getIdPagos() {
		return idPagos;
	}

	public void setIdPagos(Long idPagos) {
		this.idPagos = idPagos;
	}

	public Integer getNumPagos() {
		return numPagos;
	}

	public void setNumPagos(Integer numPagos) {
		this.numPagos = numPagos;
	}

	public Date getDatePagos() {
		return datePagos;
	}

	public void setDatePagos(Date datePagos) {
		this.datePagos = datePagos;
	}

	public Integer getMontPago() {
		return montPago;
	}

	public void setMontPago(Integer montPago) {
		this.montPago = montPago;
	}

	public String getFormPagos() {
		return formPagos;
	}

	public void setFormPagos(String formPagos) {
		this.formPagos = formPagos;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Habitaciones getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(Habitaciones habitacion) {
		this.habitacion = habitacion;
	}
	
	
//Metodos Tipo
	
	public void crear(Pagos pagos) {
		EntityManager entity = Mariadb_connect.getEntityManagerFactory().createEntityManager();
		try {
			entity.getTransaction().begin();
			Habitaciones pago = entity.find(Habitaciones.class, pagos.getHabitacion().getIdHabitacion());
			if (pago == null) {
			System.out.println("No se encontro el Pago con ese ID:" + pagos.getHabitacion().getIdHabitacion());
			return;
			}
			
			pagos.setHabitacion(habitacion);
			
			entity.persist(pagos);
			entity.getTransaction().commit();
			
		} catch (Exception e) {
			System.out.println("Error:" + e);
		} finally {
			entity.close();
		}
		
		
	}
	
	
	public List<Pagos> buscar(Long idPagos){
		EntityManager entity = Mariadb_connect.getEntityManagerFactory().createEntityManager();
		List<Pagos> listaPagos = new ArrayList<>();
		try {
			 String jpql = "SELECT p FROM Pagos p JOIN p.habitacion  h  WHERE 1=1";
		        if (idPagos != null) {
		            jpql += " AND p.idPagos = :id";
		        }
		        Query query = entity.createQuery(jpql, Pagos.class);
		        if (idPagos != null) {
		            query.setParameter("id", idPagos);
		            
		        }
		        
		        listaPagos = query.getResultList();
		        if(listaPagos.isEmpty()) {
		        	System.out.println("[-] No se encontro el pago en ese Id.");
		        }else {
		        	for(Pagos pay : listaPagos) {
		        		System.out.println("Habitacion: " + pay.getHabitacion().getIdHabitacion());
		        		System.out.println("Pago" + pay.getIdPagos());
		               
		        		Hibernate.initialize(pay.getHabitacion());
		        		
		        	}
		        }
			
		} catch (Exception e) {
			System.out.println("[!] Error al buscar el pago: " + e);
		} finally {
			entity.close();
		}
		
		
		
		return listaPagos; 
		
		
	}
	
	public void actualizar(Long idPagos, Pagos pago) {
		EntityManager  entity= Mariadb_connect.getEntityManagerFactory().createEntityManager();
		entity.getTransaction().begin();
		try {
			Pagos pays = entity.find(Pagos.class, idPagos);
			if (pays != null) {
				
				pays.setNumPagos(pago.getNumPagos());
				pays.setDatePagos(pago.getDatePagos());
				pays.setMontPago(pago.getMontPago());
				pays.setFormPagos(pago.getFormPagos());
				pays.setObservaciones(pago.getObservaciones());
				
				
			
				entity.merge(pays);
				entity.getTransaction().commit();
				System.out.println("[+] Pago actualizado con exito!");

			
			}else {
				System.out.println("[-] ID del Pago no encotrado.");

			}
			
		} catch (Exception e) {
			System.out.println("Error: " + e);
			
		}finally {
			entity.close();
		}
		
		
	}
	
	public void eliminar(List<Long> idPagos) {
		EntityManager entity = Mariadb_connect.getEntityManagerFactory().createEntityManager();
		try {
			entity.getTransaction().begin();
			Integer registroEliminado = 0;
			for (Long pago : idPagos){
			Pagos payss = entity.find(Pagos.class, pago);
			
			if (payss != null) {
				entity.remove(payss);
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
