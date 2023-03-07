package hotel_service2;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.Hibernate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import mariadb.Mariadb_connect;

@Entity
@Table (name = "HABITACIONES")
public class Habitaciones {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long idHabitacion;
	
	@Column(name = "NUMERO_HABITACION")
	private Integer numHabitacion;
	
	@Column(name = "NUMCAM_HABITACIONES")
	private Integer numCamas;
	
	@Column(name = "OBS_HABITACIONES")
	private String observaciones;
	
	@OneToMany(mappedBy = "habitacion", cascade = CascadeType.ALL)
	private List<Pagos> pagos = new ArrayList<>();
	
	 	@ManyToOne(fetch = FetchType.LAZY)
	    @Fetch(FetchMode.JOIN)
	    @JoinColumn(name = "ID_RESERVA", referencedColumnName = "ID_RESERVA")
	    private Reservas reservas;

	 //Constructores

	public Habitaciones(Long idHabitacion, Integer numHabitacion, Integer numCamas, String observaciones,
			Reservas reservas) {
		
		this.idHabitacion = idHabitacion;
		this.numHabitacion = numHabitacion;
		this.numCamas = numCamas;
		this.observaciones = observaciones;
		this.reservas = reservas;
	}

	public Habitaciones(Integer numHabitacion, Integer numCamas, String observaciones, Reservas reservas) {
		this.numHabitacion = numHabitacion;
		this.numCamas = numCamas;
		this.observaciones = observaciones;
		this.reservas = reservas;
	}

	public Habitaciones() {
		
	}

	//Setter y Getters

	public Long getIdHabitacion() {
		return idHabitacion;
	}

	public void setIdHabitacion(Long idHabitacion) {
		this.idHabitacion = idHabitacion;
	}

	public Integer getNumHabitacion() {
		return numHabitacion;
	}

	public void setNumHabitacion(Integer numHabitacion) {
		this.numHabitacion = numHabitacion;
	}

	public Integer getNumCamas() {
		return numCamas;
	}

	public void setNumCamas(Integer numCamas) {
		this.numCamas = numCamas;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Reservas getReservas() {
		return reservas;
	}

	public void setReservas(Reservas reservas) {
		this.reservas = reservas;
	}

	//Metodos Tipo
	
//	@Override
//	public String toString() {
//		return "Habitaciones [idHabitacion=" + idHabitacion + ", numHabitacion=" + numHabitacion + ", numCamas="
//				+ numCamas + ", observaciones=" + observaciones + ", reservas=" + reservas + "]";
//	}
	
	public void crear(Habitaciones habitaciones) {
		EntityManager entity = Mariadb_connect.getEntityManagerFactory().createEntityManager();
		try {
			entity.getTransaction().begin();
			Reservas reserva = entity.find(Reservas.class, habitaciones.getReservas().getIdReserva());
			if (reserva == null) {
			System.out.println("No se encontro Reserva con ese ID:" + habitaciones.getReservas().getIdReserva());
			return;
			}
			
			habitaciones.setReservas(reserva);
			
			entity.persist(habitaciones);
			entity.getTransaction().commit();
			
		} catch (Exception e) {
			System.out.println("Error:" + e);
		} finally {
			entity.close();
		}

		
	}

	public List<Habitaciones> buscar(Long idHabitacion){
		EntityManager entity = Mariadb_connect.getEntityManagerFactory().createEntityManager();
		List<Habitaciones> listaHabitacion = new ArrayList<>();
		try {
			 String jpql = "SELECT h FROM Habitaciones h JOIN h.reservas  r  WHERE 1=1";
		        if (idHabitacion != null) {
		            jpql += " AND h.idHabitacion = :id";
		        }
		        Query query = entity.createQuery(jpql, Habitaciones.class);
		        if (idHabitacion != null) {
		            query.setParameter("id", idHabitacion);
		            
		        }
		        
		        listaHabitacion = query.getResultList();
		        if(listaHabitacion.isEmpty()) {
		        	System.out.println("[-] No se encontro el Habitacion en ese Id.");
		        }else {
		        	for(Habitaciones habitacion : listaHabitacion) {
		        		System.out.println("Reserva: " + habitacion.getReservas().getIdReserva());
		        		System.out.println("Habitacion" + habitacion.getIdHabitacion());
		               
		        		Hibernate.initialize(habitacion.getReservas());
		        		
		        	}
		        }
			
		} catch (Exception e) {
			System.out.println("[!] Error al buscar la habitacion: " + e);
		} finally {
			entity.close();
		}
		
		
		
		return listaHabitacion; 
	}

	public void actualizar(Long idHabitacion, Habitaciones habitacion) {
		EntityManager  entity= Mariadb_connect.getEntityManagerFactory().createEntityManager();
		entity.getTransaction().begin();
		try {
			Habitaciones habitac = entity.find(Habitaciones.class, idHabitacion);
			if (habitac != null) {
				habitac.setNumHabitacion(habitacion.getNumHabitacion());;
				habitac.setNumCamas(habitacion.getNumCamas());
				habitac.setObservaciones(habitacion.getObservaciones());
			
				entity.merge(habitac);
				entity.getTransaction().commit();
				System.out.println("[+] Habitacion actualizado con exito!");

			
			}else {
				System.out.println("[-] ID de la habitacion no encotrado.");

			}
			
		} catch (Exception e) {
			System.out.println("Error: " + e);
			
		}finally {
			entity.close();
		}
		
	}
	 
	public void eliminar(List<Long> idHabitacion) {
		EntityManager entity = Mariadb_connect.getEntityManagerFactory().createEntityManager();
		try {
			entity.getTransaction().begin();
			Integer registroEliminado = 0;
			for (Long idHabitac : idHabitacion){
			Habitaciones room = entity.find(Habitaciones.class, idHabitac);
			
			if (room != null) {
				entity.remove(room);
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
