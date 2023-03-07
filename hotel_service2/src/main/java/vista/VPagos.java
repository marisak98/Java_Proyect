package vista;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import org.hibernate.internal.build.AllowSysOut;

import hotel_service2.Habitaciones;
import hotel_service2.Pagos;
import hotel_service2.Reservas;
import mariadb.Mariadb_connect;

public class VPagos {

	private Pagos pago = new Pagos();
	private Habitaciones habitacion = new Habitaciones(); 
	
	public void menuPagos() {
		
		Scanner scanner = new Scanner(System.in);
		Integer opcion = 0;
		do {
			System.out.println("Menu Habitaciones");
			System.out.println("Elija una opción:");
			System.out.println("1. Crear");
			System.out.println("2. Buscar ");
			System.out.println("3. Actualizar ");
			System.out.println("4. Eliminar ");
			System.out.println("5. Salir");
			
			opcion= scanner.nextInt();
			switch (opcion) {
			case 1:
				System.out.println("Ingrese el ID de la Habitacion");
				Long idHabitacion = scanner.nextLong();
				try {
				List<Habitaciones> listaHabitacion = habitacion.buscar(idHabitacion);
				if(listaHabitacion == null || listaHabitacion.isEmpty()) {
					System.out.println("[!]La Habitacion no exite, ingrese un ID valido...");
				}else {
					habitacion = listaHabitacion.get(0);
				}
				}catch(Exception e) {
					System.out.println("Error...");
				}
				
				System.out.println("Ingrese el numero de Pago");
				Integer numPago = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Ingrese la Fecha de Pago");
				// To take the input
				//Scanner scanner = new Scanner(System.in);
				System.out.println("Enter the Date ");

				String date = scanner.next();

				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
				Date date2=null;
				try {
				    //Parsing the String
				    date2 = dateFormat.parse(date);
				} catch (Exception e) {
				    // TODO Auto-generated catch block
				    e.printStackTrace();
				}
				System.out.println(date2);

				System.out.println("Ingrese el monto de Pago");
				Integer monPago = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Forma de Pago");
				String FromPago = scanner.nextLine();
				
				System.out.println("Ingrese si hay observaciones");
				String observaciones = scanner.nextLine();
				
				Pagos obj = new Pagos();
				obj.setHabitacion(habitacion);

				obj.setNumPagos(numPago);
				obj.setDatePagos(date2);
				obj.setMontPago(monPago);
				obj.setFormPagos(FromPago);
				obj.setObservaciones(observaciones);
				
				obj.crear(obj);
				System.out.println("Pago guardado con exito");
				break;
				
			case 2: 
				Pagos pagos = new Pagos();
				System.out.println("Ingrese el ID del Pago a buscar");
				Long idPago = scanner.nextLong();
				List<Pagos> listaPagos = pagos.buscar(idPago);
				System.out.println("Pago encontrada:");
				for (Pagos p : listaPagos) {
					System.out.println(p.toString());
				}
				break;
				
			case 3:
				actualiza2();
				break;
				
			case 4:
				System.out.println("Ingrese el Id de la habitacion que desea eliminar");
				Long idPagos = scanner.nextLong();
				List<Long> listaPagoss = new ArrayList<>();
				listaPagoss.add(idPagos);
				
				Pagos obj2 = new Pagos();
				obj2.eliminar(listaPagoss);
				
				
				break;
			case 5:
				System.out.println("Saliendo del Menu!");
				break;
			default:
				break;
			}
			
		} while (opcion != 5);
	}
	
	public void actualiza2() {
		
		Scanner scanner = new Scanner(System.in);
		EntityManager entityManager = Mariadb_connect.getEntityManagerFactory().createEntityManager();        
		      
		System.out.print("Ingrese el id del Pago que desea actualizar: ");
		        Long idPago = scanner.nextLong();
		        scanner.nextLine();
		      
		        Pagos pay = new Pagos();
		        pay.setIdPagos(idPago);
		       try {
		    	   List<Pagos> listaPago = pay.buscar(idPago);
		           if (listaPago != null && !listaPago.isEmpty()) {
		           	for (Pagos pag : listaPago) {
		           		System.out.println("[+] Se encontró una pago con ese id.");
		                   System.out.println(pag.toString());
		           	}
		               
		           }
		          
			} catch (Exception e) {
				// TODO: handle exception
			}
		      
		        
		     
		        Integer opcion = 0;
		       
		        do { 
		        	System.out.println("Que campo deseas actualizar");
		        	System.out.println("1. Numero de Pago");
		        	System.out.println("2. Fecha del pago");
		        	System.out.println("3. Monto del Pago");
		        	System.out.println("4. Forma de Pago");
		        	System.out.println("5. Observaciones");
		        	System.out.println("6. Salir");
		        	opcion = scanner.nextInt();
		        	scanner.nextLine();
		        switch (opcion) {
		            case 1:
		              System.out.println("Nuevo Numero de Pagos");
		              Integer numPago = scanner.nextInt();
		              scanner.nextLine();
		              pay.setNumPagos(numPago);


		            	pay.actualizar(pay.getIdPagos(), pay);
		                break;
		            case 2:
		            	// To take the input
		            	
		            	System.out.println("Enter the Date ");

		            	String date = scanner.next();

		            	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		            	Date date2=null;
		            	try {
		            	    //Parsing the String
		            	    date2 = dateFormat.parse(date);
		            	} catch (Exception e) {
		            	    // TODO Auto-generated catch block
		            	    e.printStackTrace();
		            	}
		            	System.out.println(date2);

		            	pay.actualizar(pay.getIdPagos(), pay);

		                break;
		           
		            case 3:
		            	System.out.print("Ingrese nuevo Monto de Pago ");
		                Integer monPago = scanner.nextInt();
		                scanner.nextLine();
		                pay.setMontPago(monPago);

		                
		            	pay.actualizar(pay.getIdPagos(), pay);
		                break;
		            
		                
		            case 4:
		            	System.out.println("Ingrese nueva Forma De Pago");
		            	String formaPago = scanner.nextLine();
		            	pay.setFormPagos(formaPago);
		            	pay.actualizar(pay.getIdPagos(), pay);
		            	
		            	break;
		            case 5:
		            	System.out.println("Ingrese nuevas observaciones");
		            	String observaciones = scanner.nextLine();
		            	pay.setObservaciones(observaciones);
		            	pay.actualizar(pay.getIdPagos(), pay);
		            	break;
		            case 6:
		                System.out.println("Saliendo del menú de actualización...");
		                System.exit(0);
		                break;
		            default:
		                System.out.println("Campo inválido.");
		                return;
		        }
		        
		        } while (opcion != 6);
			
	}
	
}
