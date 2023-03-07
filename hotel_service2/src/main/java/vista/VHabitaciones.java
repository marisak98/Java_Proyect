package vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import hotel_service2.Clientes;
import hotel_service2.Habitaciones;
import hotel_service2.Reservas;
import hotel_service2.Servicios;
import mariadb.Mariadb_connect;

public class VHabitaciones {

		private Habitaciones habitaciones;
		private Reservas reserva = new Reservas();
		
	public void menuHabitaciones() {
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
			
			opcion = scanner.nextInt();
			switch (opcion) {
			case 1:
				System.out.println("Ingrese el ID del Reserva");
				Long idReserva = scanner.nextLong();
				try {
				List<Reservas> listaReservas = reserva.buscar(idReserva);
				if(listaReservas == null || listaReservas.isEmpty()) {
					System.out.println("[!] El Reserva no exite, ingrese un ID valido...");
				}else {
					reserva = listaReservas.get(0);
				}
				}catch(Exception e) {
					System.out.println("Error...");
				}
				
				System.out.println("Ingrese el Numero de Habitacion");
				Integer numHuesped = scanner.nextInt();
                scanner.nextLine();

				
				System.out.println("Ingrese el numero de Camas de la habitacion");
				Integer numcamas = scanner.nextInt();
				scanner.nextLine();

				
				System.out.println("Ingrese las observaciones");
				String observaciones = scanner.nextLine();
				

				Habitaciones obj = new Habitaciones();
				obj.setReservas(reserva);
				obj.setNumHabitacion(numHuesped);
				obj.setNumCamas(numcamas);
				obj.setObservaciones(observaciones);
				obj.crear(obj);

				
				System.out.println("[+] Servicio creado con exito");
				System.out.println("");
				break;
			case 2:
				Habitaciones habitacion = new Habitaciones();
				System.out.println("Ingrese el ID de la habitacion a buscar");
				Long idHabitacion = scanner.nextLong();
				List<Habitaciones> listaHabitacion = habitacion.buscar(idHabitacion);
				System.out.println("Habitacion encontrada:");
				for (Habitaciones h : listaHabitacion) {
					System.out.println(h.toString());
				}
				
				break;
				
			case 3: 
				menuActualizar();
				
				break;
				
			case 4:
				System.out.println("Ingrese el Id de la habitacion que desea eliminar");
				Long idHabitacions = scanner.nextLong();
				List<Long> listaEliminar = new ArrayList<>();
				listaEliminar.add(idHabitacions);
				
				Habitaciones obj2 = new Habitaciones();
				obj2.eliminar(listaEliminar);
				
				break;
			default:
				break;
			}
			
		} while (opcion != 5);
		
		
	}
	public void menuActualizar() {
		Scanner scanner = new Scanner(System.in);
		EntityManager entityManager = Mariadb_connect.getEntityManagerFactory().createEntityManager();        
		      
		System.out.print("Ingrese el id de la Habitacion que desea actualizar: ");
		        Long idHabitacions = scanner.nextLong();
		        scanner.nextLine();
		      
		        Habitaciones rooms = new Habitaciones();
		        rooms.setIdHabitacion(idHabitacions);
		       try {
		    	   List<Habitaciones> listaHabitacion = rooms.buscar(idHabitacions);
		           if (listaHabitacion != null && !listaHabitacion.isEmpty()) {
		           	for (Habitaciones hab : listaHabitacion) {
		           		System.out.println("[+] Se encontró una reserva con ese id.");
		                   System.out.println(hab.toString());
		           	}
		               
		           }
		          
			} catch (Exception e) {
				// TODO: handle exception
			}
		      
		        
		     
		        Integer opcion = 0;
		       
		        do { 
		        	System.out.println("Que campo deseas actualizar");
		        	System.out.println("1. Numero de Habitacion");
		        	System.out.println("2. Numero de Camas");
		        	System.out.println("3. Tipo Observaciones");
		        	System.out.println("4. Salir");
		        	opcion = scanner.nextInt();
		        	scanner.nextLine();
		        switch (opcion) {
		            case 1:
		              System.out.println("Nueva Habitacion");
		              Integer numHabitacion = scanner.nextInt();
		              scanner.nextLine();
				        rooms.setNumHabitacion(numHabitacion);

		              rooms.actualizar(rooms.getIdHabitacion(), rooms);
		                break;
		            case 2:
		            	 System.out.print("Ingrese la nueva nuemero de Camas ");
		                Integer numCamas = scanner.nextInt();
		                scanner.nextLine();
		                rooms.setNumCamas(numCamas);
			              rooms.actualizar(rooms.getIdHabitacion(), rooms);

		                break;
		           
		            case 3:
		            	System.out.print("Ingrese nuevas observaciones ");
		                String observaciones = scanner.nextLine();
		                scanner.nextLine();
		                rooms.setObservaciones(observaciones);
			              rooms.actualizar(rooms.getIdHabitacion(), rooms);

		                break;
		            
		            case 4:
		                System.out.println("Saliendo del menú de actualización...");
		                System.exit(0);
		                break;
		            default:
		                System.out.println("Campo inválido.");
		                return;
		        }
		        
		        } while (opcion != 4);
			}
	
	}
	

