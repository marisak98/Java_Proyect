package hotel_service2;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.math.*;
import java.time.*;

import mariadb.Mariadb_connect;

@Entity
@Table(name = "RESERVAS")
public class Reservas  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_RESERVA")
	private Long idReserva;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "ID_SERVICIOS", referencedColumnName = "ID_SERVICIOS")
    private Servicios servicios;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE")
	private Clientes cliente;
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "NUMRESER_")
	private Integer numReserva; ////Nombre de reserva
	
	@Column(name = "FECENTR_RESRVA")
	private LocalDate fechaEntradaReserva;
	
	@Column(name = "FECSALI_RESERVA")
	private LocalDateTime fechaSalidaReserva;
	
	@Column(name = "TIPHA_RESERVA")
	private String tipoHabitacionReserva;
	
	@Column(name = "NUMHUS_RESERVA")
	private Integer numHusepedReserva;
	
	@Column(name = "PRETO_RESERVA")
	private BigDecimal precioTotalReserva;
	
	@Column(name = "OBS_RESERVA")
	private String observacionesReserva;

	//Constructores
	
	
	
	public Reservas(Long idReserva, Clientes cliente, Servicios servicios, Integer numReserva,
			LocalDate fechaEntradaReserva, LocalDateTime fechaSalidaReserva, String tipoHabitacionReserva,
			Integer numHusepedReserva, BigDecimal precioTotalReserva, String observacionesReserva) {
		
		this.idReserva = idReserva;
		this.cliente = cliente;
        this.servicios = servicios;
		this.numReserva = numReserva;
		this.fechaEntradaReserva = fechaEntradaReserva;
		this.fechaSalidaReserva = fechaSalidaReserva;
		this.tipoHabitacionReserva = tipoHabitacionReserva;
		this.numHusepedReserva = numHusepedReserva;
		this.precioTotalReserva = precioTotalReserva;
		this.observacionesReserva = observacionesReserva;
	}

	

	public Reservas( Servicios servicios, Clientes cliente, Integer numReserva,
			LocalDate fachaEntradaReserva, LocalDateTime fechaSalidaReserva, String tipoHabitacionReserva,
			Integer numHusepedReserva, BigDecimal precioTotalReserva, String observacionesReserva) {
		
		this.servicios = servicios;
		this.cliente = cliente;
		this.numReserva = numReserva;
		this.fechaEntradaReserva = fachaEntradaReserva;
		this.fechaSalidaReserva = fechaSalidaReserva;
		this.tipoHabitacionReserva = tipoHabitacionReserva;
		this.numHusepedReserva = numHusepedReserva;
		this.precioTotalReserva = precioTotalReserva;
		this.observacionesReserva = observacionesReserva;
	}

	public Reservas() {
		
	}

	//Setters y Getters

	public long getIdReserva() {
		return idReserva;
	}



	public void setIdReserva(long idReserva) {
		this.idReserva = idReserva;
	}



	public Clientes getCliente() {
		return cliente;
	}



	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}




	public Servicios getServicios() {
		return servicios;
	}



	public void setServicios(Servicios servicios) {
		this.servicios = servicios;
	}



	public Integer getNumReserva() {
		return numReserva;
	}



	public void setNumReserva(Integer numReserva) {
		this.numReserva = numReserva;
	}



	public LocalDate getFechaEntradaReserva() {
		return fechaEntradaReserva;
	}



	public void setFechaEntradaReserva(LocalDate fechaEntradaReserva) {
		this.fechaEntradaReserva = fechaEntradaReserva;
	}



	public LocalDateTime getFechaSalidaReserva() {
		return fechaSalidaReserva;
	}



	public void setFechaSalidaReserva(LocalDateTime fechaSalidaReserva) {
		this.fechaSalidaReserva = fechaSalidaReserva;
	}



	public String getTipoHabitacionReserva() {
		return tipoHabitacionReserva;
	}



	public void setTipoHabitacionReserva(String tipoHabitacionReserva) {
		this.tipoHabitacionReserva = tipoHabitacionReserva;
	}



	public Integer getNumHusepedReserva() {
		return numHusepedReserva;
	}



	public void setNumHusepedReserva(Integer numHusepedReserva) {
		this.numHusepedReserva = numHusepedReserva;
	}



	public BigDecimal getPrecioTotalReserva() {
		return precioTotalReserva;
	}



	public void setPrecioTotalReserva(BigDecimal precioTotalReserva) {
		this.precioTotalReserva = precioTotalReserva;
	}



	public String getObservacionesReserva() {
		return observacionesReserva;
	}



	public void setObservacionesReserva(String observacionesReserva) {
		this.observacionesReserva = observacionesReserva;
	}

	//Metodos Tipo 
	
	@Override
	public String toString() {
		return "Reservas [idReserva=" + idReserva + ", servicios=" + servicios + ", cliente=" + cliente
				+ ", numReserva=" + numReserva + ", fechaEntradaReserva=" + fechaEntradaReserva
				+ ", fechaSalidaReserva=" + fechaSalidaReserva + ", tipoHabitacionReserva=" + tipoHabitacionReserva
				+ ", numHusepedReserva=" + numHusepedReserva + ", precioTotalReserva=" + precioTotalReserva
				+ ", observacionesReserva=" + observacionesReserva + "]";
	}
	

	public void crear(Reservas reservas) {
		EntityManager entity = Mariadb_connect.getEntityManagerFactory().createEntityManager();
		
		try {
			entity.getTransaction().begin();
			Clientes cliente = entity.find(Clientes.class, reservas.getCliente().getIdCliente());
            if (cliente == null) {
                System.out.println("No se encontró el cliente con ID " + reservas.getCliente().getIdCliente());
                return;
            }
            
            Servicios servicio = entity.find(Servicios.class, reservas.getServicios().getIdServicios());
            if (servicio == null) {
                System.out.println("No se encontró el servicio con ID " + reservas.getServicios().getIdServicios());
                return;
            }
            
           reservas.setCliente(cliente);
           reservas.setServicios(servicio);
            
            entity.persist(reservas);
            entity.getTransaction().commit();
            
            System.out.println("Se creó la reserva con éxito.");
        } catch (Exception e) {
            System.out.println("Ocurrió un error al crear la reserva: " + e.getMessage());
        } finally {
        	entity.close();
        }
		
    }
    
	
	public List<Reservas> buscar(Long idReserva) {
		EntityManager entity = Mariadb_connect.getEntityManagerFactory().createEntityManager();
		List<Reservas> listaReservas = new ArrayList<>();
		try {
			String jqsl = "SELECT r FROM Reservas r JOIN r.servicios s JOIN r.cliente c WHERE 1=1";
			if(idReserva != null){
				jqsl += "AND r.idReserva = :id";
			}
			Query query = entity.createQuery(jqsl, Reservas.class);
			if(idReserva != null) {
				query.setParameter("id", idReserva);
			}
			
			listaReservas = query.getResultList();
			if(listaReservas.isEmpty()) {
				System.out.println("[!] No se econtraron reservas");
			}else {
				for (Reservas reserva : listaReservas) {

					System.out.println("Reserva: " + reserva.getIdReserva());
	                System.out.println("Cliente: " + reserva.getCliente().getNomCliente()); // Acceso a la información del cliente
	                System.out.println("Servicio: " + reserva.getServicios().getNombre());
			        Hibernate.initialize(reserva.getServicios());

				
				}
			}
		
			
		} catch (Exception e) {
	        System.out.println("[!] Error: " + e.getMessage());
		}finally {
			entity.close();
		}
		
		return listaReservas;
	}




	public void actualizar2(Long idReservas, Reservas reservas) {
		EntityManager  entity= Mariadb_connect.getEntityManagerFactory().createEntityManager();
		entity.getTransaction().begin();
		try {
			Reservas reserva = entity.find(Reservas.class, idReservas);
			if (reserva != null) {
				reserva.setFechaEntradaReserva(reservas.getFechaEntradaReserva());;
				reserva.setFechaSalidaReserva(reservas.getFechaSalidaReserva());
				reserva.setTipoHabitacionReserva(reservas.getTipoHabitacionReserva());
				reserva.setNumHusepedReserva(reservas.getNumHusepedReserva());
				reserva.setPrecioTotalReserva(reservas.getPrecioTotalReserva());
				reserva.setObservacionesReserva(reservas.getObservacionesReserva());
			
				entity.merge(reserva);
				entity.getTransaction().commit();
				System.out.println("[+] Reserva actualizado con exito!");

			
			}else {
				System.out.println("[-] ID de la reserva no encotrado.");

			}
			
		} catch (Exception e) {
			System.out.println("Error: " + e);
			
		}finally {
			entity.close();
		}
				
	}



	
	public void eliminar() {
		
	}
	

	
}
