package vista;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import hotel_service2.Clientes;
import hotel_service2.Reservas;
import hotel_service2.Servicios;
import mariadb.Mariadb_connect;

public class VReservas {
	
	private Servicios servicio = new Servicios();
	private Clientes cliente = new Clientes();
	
	

	public void menuReservas() throws Exception {
		Scanner scanner = new Scanner(System.in);
		Integer opcion = 0;
		while (opcion != 5) {
			
			System.out.println("Seleccione que desea hacer");
			System.out.println("1. Crear reserva");
			System.out.println("2. Buscar reserva ");
			System.out.println("3. Actualizar reserva");
			System.out.println("4. Eliminar reserva");
			System.out.println("5. Volver atras");
			
			opcion = scanner.nextInt();
			switch(opcion){
			case 1:

				System.out.println("Ingrese el ID del Cliente");
				Long idCliente = scanner.nextLong();
				scanner.nextLine();
				System.out.println("Ingrese el nombre del Cliente");
				String nombre = scanner.nextLine();	
				List<Clientes> listaClientes =  cliente.buscar(idCliente, nombre);
				if(listaClientes == null || listaClientes.isEmpty()) {
					System.out.println("[!] El cliente no existe, ingreser un ID y nombre validos...");
				}else{
					cliente = listaClientes.get(0);
				}
				
				System.out.println("Ingrese el ID del Servicio");
				Long idServicio = scanner.nextLong();
				List<Servicios> listaServicios = servicio.buscar(idServicio);
				if(listaServicios == null || listaServicios.isEmpty()) {
					System.out.println("[!] El servicio no exite, ingrese un ID valido...");
				}else {
					servicio = listaServicios.get(0);
				}
				
				System.out.println("Ingrese la fecha de inicio de la reserva (formato dd/MM/yyyy)");
				String fechaStr = scanner.nextLine();
				scanner.nextLine();
				DateTimeFormatter formatter = (DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				LocalDate fechaInicio = null;
				try {
				     fechaInicio = LocalDate.parse(fechaStr, formatter);
				    System.out.println("Fecha de inicio de la reserva: " + fechaInicio);
				} catch (DateTimeParseException e) {
				    System.out.println("Error: la fecha ingresada no es válida. Asegúrese de usar el formato dd/MM/yyyy");
				}
				
				
				
				
				System.out.println("Ingrese la fecha de finalizacion de la reserva (formato dd/MM/yyyy hh:mm)");
                String fechaSalidaStr = scanner.nextLine();
                scanner.nextLine();
                formatter = (DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                LocalDate fechaSalida = null;
                try {
                    fechaSalida = LocalDate.parse(fechaSalidaStr, formatter);
                } catch (DateTimeParseException e) {
                    System.out.println("Error: la fecha ingresada no es válida. Asegúrese de usar el formato dd/MM/yyyy hh:mm");
                   
               }

				
				
				System.out.println("Ingrese el tipo de habitacion");
				String tipoHabitacion = scanner.nextLine();
                scanner.nextLine();

				System.out.println("Ingrese el numero de huspedes");
				Integer numHuesped = scanner.nextInt();
                scanner.nextLine();

				
				System.out.println("Ingrese el precio total de la reserva");
				Double precioTotal = scanner.nextDouble();
                scanner.nextLine();

				System.out.println("Ingrese las observaciones");
				String observaciones = scanner.nextLine();
                scanner.nextLine();

				
               Reservas reserva = new Reservas();

				
				reserva.setCliente(cliente);
				reserva.setServicios(servicio);
				reserva.setFechaEntradaReserva(fechaInicio);
				//reserva.setFechaSalidaReserva(fechaSalida);
				reserva.setTipoHabitacionReserva(tipoHabitacion);
				reserva.setNumHusepedReserva(numHuesped);
				reserva.setPrecioTotalReserva(precioTotal);
				reserva.setObservacionesReserva(observaciones);
				
				reserva.crear(reserva);
				break;
				
			case 2:
				Reservas reservas = new Reservas(); 
				System.out.println("Ingrese el ID de la Reservas");
				Long id = scanner.nextLong();
				List<Reservas> listaReservas = reservas.buscar(id);
				System.out.println("[+] Rerva Encontrada...");
				for (Reservas r : listaReservas) {
					System.out.println(r.toString());
				}
				
				
				break;
				
			case 3:	
				try {
					menuActualizar();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case 4:
				System.out.println("Ingrese el ID del servicio a eliminar");
				Long idReservas = scanner.nextLong();
				List<Long> listaServicioEliminar = new ArrayList<>();
				listaServicioEliminar.add(idReservas);
				
				Reservas reservas2 = new Reservas();
				reservas2.eliminar(listaServicioEliminar);
				
				break;
				
			case 5:
                System.exit(0);

				break;
			}
		}	
		}
	
	public void menuActualizar() throws Exception {
		Scanner scanner = new Scanner(System.in);
EntityManager entityManager = Mariadb_connect.getEntityManagerFactory().createEntityManager();        
      
System.out.print("Ingrese el id de la reserva que desea actualizar: ");
        Long idReserva = scanner.nextLong();
        scanner.nextLine();
      
        Reservas resrvas = new Reservas();
        resrvas.setIdReserva(idReserva);
       try {
    	   List<Reservas> listaReservas = resrvas.buscar(idReserva);
           if (listaReservas != null && !listaReservas.isEmpty()) {
           	for (Reservas res : listaReservas) {
           		System.out.println("[+] Se encontró una reserva con ese id.");
                   System.out.println(res.toString());
           	}
               
           }
          
	} catch (Exception e) {
		// TODO: handle exception
	}
      
        
     
        Integer opcion = 0;
       
        do { 
        	System.out.println("Que campo deseas actualizar");
        	System.out.println("1. Fecha de inicio");
        	System.out.println("2. Fecha de salida");
        	System.out.println("3. Tipo de habitacion");
        	System.out.println("4. Numero de Huespedes");
        	System.out.println("5. Precio");
        	System.out.println("6. Observaciones");
        	System.out.println("7. Salir");
        	opcion = scanner.nextInt();
        	scanner.nextLine();
        switch (opcion) {
            case 1:
              System.out.println("Nueva Fecha");
              String fechaInicioStr = scanner.nextLine();
              LocalDate fechaInicio = LocalDate.parse(fechaInicioStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
              resrvas.setFechaEntradaReserva(fechaInicio);
              resrvas.actualizar2(resrvas.getIdReserva(), resrvas);
                break;
            case 2:
            	 System.out.print("Ingrese la nueva fecha de salida (en formato dd/mm/yyyy): ");
                 String fechaSalidaStr = scanner.nextLine();
                 LocalDate fechaSalida = LocalDate.parse(fechaSalidaStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                 resrvas.setFechaSalidaReserva(fechaSalida);
                 resrvas.actualizar2(resrvas.getIdReserva(), resrvas);

                
                break;
            case 3:
            	 System.out.print("Ingrese el nuevo tipo de habitación: ");
                 String tipoHabitacion = scanner.nextLine();
                 resrvas.setTipoHabitacionReserva(tipoHabitacion);
                 resrvas.actualizar2(resrvas.getIdReserva(), resrvas);

                break;
            case 4:
            	System.out.print("Ingrese el nuevo número de huéspedes: ");
                Integer numHuespedes = scanner.nextInt();
                scanner.nextLine();
                resrvas.setNumHusepedReserva(numHuespedes);
                resrvas.actualizar2(resrvas.getIdReserva(), resrvas);

                break;
            case 5:
            	 System.out.print("Ingrese el nuevo precio: ");
                 Double precio = scanner.nextDouble();
                 scanner.nextLine();
                 resrvas.setPrecioTotalReserva(precio);
                 resrvas.actualizar2(resrvas.getIdReserva(), resrvas);

                break;
            case 6:
            	 System.out.print("Ingrese las nuevas observaciones: ");
                 String observaciones = scanner.nextLine();
                 resrvas.setObservacionesReserva(observaciones);
                 resrvas.actualizar2(resrvas.getIdReserva(), resrvas);

                break;
            case 7:
                System.out.println("Saliendo del menú de actualización...");
                System.exit(0);
                break;
            default:
                System.out.println("Campo inválido.");
                return;
        }
        
        
        } while (opcion != 7);
		
	}
}
