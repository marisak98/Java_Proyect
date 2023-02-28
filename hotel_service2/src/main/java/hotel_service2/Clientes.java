package hotel_service2;
import java.util.Scanner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import mariadb.Mariadb_connect;
import vista.VCliente;

@Entity
@Table(name="CLIENTE")
public class Clientes {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_CLIENTE")
	private Long idCliente;
	
	@Column(name = "NOM_CLIENTE")
	private String nomCliente;
	
	@Column(name = "DIRE_CLIENTE")
	private String dirCliente;
	
	@Column(name = "TEL_CLIENTE")
	private String telCliente;
	
	@Column(name = "MAIL_CLIENTE")
	private String mailCliente;
	
	@Column(name = "CONT_CLIENTE")
	private String contCliente;
	
	@Column(name =  "OBJ_CLIENTE")
	private String objCliente;
	
	// Consctrutores
	public Clientes () {
		
	}

	
	public Clientes(Long idCliente, String nomCliente, String dirCliente, String telCliente, String mailCliente,
			String contCliente, String objCliente) {
		super();
		this.idCliente = idCliente;
		this.nomCliente = nomCliente;
		this.dirCliente = dirCliente;
		this.telCliente = telCliente;
		this.mailCliente = mailCliente;
		this.contCliente = contCliente;
		this.objCliente = objCliente;
	}


	
	public Clientes(String nomCliente, String dirCliente, String telCliente, String mailCliente, String contCliente,
			String objCliente) {
		super();
		this.nomCliente = nomCliente;
		this.dirCliente = dirCliente;
		this.telCliente = telCliente;
		this.mailCliente = mailCliente;
		this.contCliente = contCliente;
		this.objCliente = objCliente;
	}


	// Setter y Getters
	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomCliente() {
		return nomCliente;
	}

	public void setNomCliente(String nomCliente) {
		this.nomCliente = nomCliente;
	}

	public String getDirCliente() {
		return dirCliente;
	}

	public void setDirCliente(String dirCliente) {
		this.dirCliente = dirCliente;
	}

	public String getTelCliente() {
		return telCliente;
	}

	public void setTelCliente(String telCliente) {
		this.telCliente = telCliente;
	}

	public String getMailCliente() {
		return mailCliente;
	}

	public void setMailCliente(String mailCliente) {
		this.mailCliente = mailCliente;
	}

	public String getContCliente() {
		return contCliente;
	}

	public void setContCliente(String contCliente) {
		this.contCliente = contCliente;
	}

	public String getObjCliente() {
		return objCliente;
	}

	public void setObjCliente(String objCliente) {
		this.objCliente = objCliente;
	}
	
	// Metodos tipo
	
//	public String toString() {
//		return "Cliente [ID_CLIENTE = "+idCliente+", Nombre= "+nomCliente+", Direccion= "+dirCliente+", Telfono "+telCliente+" Email= "+mailCliente+", Cont= "+contCliente+", Observaciones= "+objCliente+"]";
//	}
//	
	public void crear(Clientes clientes) {
			
		
		 EntityManager entity = Mariadb_connect.getEntityManagerFactory().createEntityManager();
							
	try {
				entity.getTransaction().begin();
				entity.persist(clientes);
				entity.getTransaction().commit();
	} catch (Exception e) {
	System.out.println("Error..." + e);
	} finally {
		entity.close();
	}
				
			
		
	}
	
	public void buscar() {
		
	}
	
	public void actualizar() {
		
	
	}

	public void eliminar() {
		
		
	}
	
	}

	

