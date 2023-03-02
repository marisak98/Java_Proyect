package hotel_service2;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import java.math.*;
import java.time.*;

import mariadb.Mariadb_connect;

@Entity
@Table(name = "RESERVAS")
public abstract class Reservas implements Crud {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_RESERVA")
	private long idReserva;
	
	@OneToMany 
	@JoinColumn(name="ID_CLIENTE")
	private Clientes cliente;
	
	@ManyToOne
	@JoinColumn(name="ID_SERVICIOS")
	private Servicios servicio;
	
	@Column(name = "NUMRESER_")
	private Integer numReserva;
	
	@Column(name = "FECENTR_RESRVA")
	private LocalDateTime fachaEntradaReserva;
	
	@Column(name = "FECSALI_RESERVA")
	private LocalDateTime fechaSalidaReserva;
	
	@Column(name = "TIPHA_RESERVA")
	private String tipoHabitacionRerva;
	
	@Column(name = "NUMHUS_RESERVA")
	private Integer numHusepedReserva;
	
	@Column(name = "PRETO_RESERVA")
	private BigDecimal precioTotalReserva;
	
	@Column(name = "OBS_RESERVA")
	private String observacionesReserva;

	//Constructores
	
	
	
	public Reservas(long idReserva, Clientes cliente, Servicios servicio, Integer numReserva,
			LocalDateTime fachaEntradaReserva, LocalDateTime fechaSalidaReserva, String tipoHabitacionRerva,
			Integer numHusepedReserva, BigDecimal precioTotalReserva, String observacionesReserva) {
		super();
		this.idReserva = idReserva;
		this.cliente = cliente;
		this.servicio = servicio;
		this.numReserva = numReserva;
		this.fachaEntradaReserva = fachaEntradaReserva;
		this.fechaSalidaReserva = fechaSalidaReserva;
		this.tipoHabitacionRerva = tipoHabitacionRerva;
		this.numHusepedReserva = numHusepedReserva;
		this.precioTotalReserva = precioTotalReserva;
		this.observacionesReserva = observacionesReserva;
	}

	

	public Reservas(Clientes cliente, Servicios servicio, Integer numReserva, LocalDateTime fachaEntradaReserva,
			LocalDateTime fechaSalidaReserva, String tipoHabitacionRerva, Integer numHusepedReserva,
			BigDecimal precioTotalReserva, String observacionesReserva) {
		super();
		this.cliente = cliente;
		this.servicio = servicio;
		this.numReserva = numReserva;
		this.fachaEntradaReserva = fachaEntradaReserva;
		this.fechaSalidaReserva = fechaSalidaReserva;
		this.tipoHabitacionRerva = tipoHabitacionRerva;
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



	public Servicios getServicio() {
		return servicio;
	}



	public void setServicio(Servicios servicio) {
		this.servicio = servicio;
	}



	public Integer getNumReserva() {
		return numReserva;
	}



	public void setNumReserva(Integer numReserva) {
		this.numReserva = numReserva;
	}



	public LocalDateTime getFachaEntradaReserva() {
		return fachaEntradaReserva;
	}



	public void setFachaEntradaReserva(LocalDateTime fachaEntradaReserva) {
		this.fachaEntradaReserva = fachaEntradaReserva;
	}



	public LocalDateTime getFechaSalidaReserva() {
		return fechaSalidaReserva;
	}



	public void setFechaSalidaReserva(LocalDateTime fechaSalidaReserva) {
		this.fechaSalidaReserva = fechaSalidaReserva;
	}



	public String getTipoHabitacionRerva() {
		return tipoHabitacionRerva;
	}



	public void setTipoHabitacionRerva(String tipoHabitacionRerva) {
		this.tipoHabitacionRerva = tipoHabitacionRerva;
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
	public void crear(Reservas reserva) {
		EntityManager entity = Mariadb_connect.getEntityManagerFactory().createEntityManager();
		
		try {
			entity.getTransaction().begin();
			Clientes clientes = entity.find(Clientes.class, cliente.getIdCliente());
			reserva.setCliente(clientes);
			entity.persist(reserva);
			entity.getTransaction().commit();
			System.out.println("[+] Resrva creada con exito!");
		} catch (Exception e) {
			System.out.println("[!] Error al crear la reserva: " + e.getMessage());
			entity.getTransaction().rollback();
			
		} finally {
			entity.close();
		}
		
	}



	@Override
	public void buscar() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void actualizar2() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void eliminar() {
		// TODO Auto-generated method stub
		
	}
	

	
	
	
	
}
