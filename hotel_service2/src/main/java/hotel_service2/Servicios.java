package hotel_service2;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


import org.hibernate.Hibernate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.math.*;
import java.time.*;

import mariadb.Mariadb_connect;

@Entity
@Table(name = "SERVICIOS")
public class Servicios {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_SERVICIOS")
	private Long idServicios;
	
	@Column(name = "NOMB_SERVICIOS")
    private String nombre;

    @Column(name = "PRECIO_SERVICIOS")
    private BigDecimal precio;

    @Column(name = "OBS_SERVICIOS")
    private String observaciones;

    @OneToMany(mappedBy ="servicios", cascade = CascadeType.ALL)
    //@Fetch(FetchMode.JOIN)
    private List<Reservas> reservas = new ArrayList<>();
    

 // Constructores 
    
    public Servicios(Long idServicios, String nombre, BigDecimal precio, String observaciones, List<Reservas> reservas) {
	this.idServicios = idServicios;
	this.nombre = nombre;
	this.precio = precio;
	this.observaciones = observaciones;
	this.reservas = reservas;
    }
    
    
    
    public Servicios(String nombre, BigDecimal precio, String observaciones, List<Reservas> reservas) {
	super();
	this.nombre = nombre;
	this.precio = precio;
	this.observaciones = observaciones;
	this.reservas = reservas;
    }



	public Servicios() {
	
    }

	// Setters y Getters


	public Long getIdServicios() {
		return idServicios;
	}



	public void setIdServicios(Long idServicios) {
		this.idServicios = idServicios;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public BigDecimal getPrecio() {
		return precio;
	}



	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}



	public String getObservaciones() {
		return observaciones;
	}



	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}



	public List<Reservas> getReservas() {
		return reservas;
	}



	public void setReservas(List<Reservas> reservas) {
		this.reservas = reservas;
	}


//****Metodos Tipo***
	@Override
	public String toString() {
		return "Servicios [idServicios=" + idServicios + ", nombre=" + nombre + ", precio=" + precio
				+ ", observaciones=" + observaciones + ", reserva=" + reservas + "]";
	}
    
	
	public void crear(Servicios servicios) {
		EntityManager entity = Mariadb_connect.getEntityManagerFactory().createEntityManager();
		try {
			entity.getTransaction().begin();
			entity.persist(servicios);
			entity.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Erro al crear el servicio:" + e);
		}finally {
			entity.close();
		}
		
	}
	
	public List<Servicios> buscar(Long idServicio) {
		EntityManager entity = Mariadb_connect.getEntityManagerFactory().createEntityManager();
		List<Servicios> listaServicios = new ArrayList<>();
		try {
			 String jpql = "SELECT s FROM Servicios s WHERE 1=1";
		        if (idServicio != null) {
		            jpql += " AND s.idServicios = :id";
		        }
		        Query query = entity.createQuery(jpql, Servicios.class);
		        if (idServicio != null) {
		            query.setParameter("id", idServicio);
		            
		        }
		        
		        listaServicios = query.getResultList();
		        if(listaServicios.isEmpty()) {
		        	System.out.println("[-] No se encontro el serivicio en ese Id.");
		        }else {
		        	for(Servicios servicio : listaServicios ) {
		        		Hibernate.initialize(servicio.getReservas());
		        	}
		        }
			
		} catch (Exception e) {
			System.out.println("[!] Error al buscar el servicio: " + e);
		} finally {
			entity.close();
		}
		return listaServicios;
	}
	
	public void actulizar(Long IdServicios, Servicios servicios){
		EntityManager entity = Mariadb_connect.getEntityManagerFactory().createEntityManager();
		entity.getTransaction().begin();
		try {
			Servicios servicio = entity.find(Servicios.class, IdServicios);
			if (servicio != null) {
				
				servicio.setNombre(servicios.getNombre());
				servicio.setPrecio(servicios.getPrecio());
				servicio.setObservaciones(servicios.getObservaciones());
				
				entity.merge(servicio);
				entity.getTransaction().commit();
				System.out.println("[+] Servicio actualizado con exito!");
			} else {
				System.out.println("[-] ID del servicio no encotrado.");
			}
		} catch (Exception e) {
			System.out.println("[!] Error:" + e);
		}finally {
			entity.close();
		}
		
	}
    
	public void eliminar(List<Long> idServicios) {
		EntityManager entity = Mariadb_connect.getEntityManagerFactory().createEntityManager();
		try {
			entity.getTransaction().begin();
			Integer registroEliminado = 0;
			for (Long idServicio : idServicios){
			Servicios servicios = entity.find(Servicios.class, idServicio);
			
			if (servicios != null) {
				entity.remove(servicios);
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
