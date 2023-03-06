package vista;


import java.math.BigDecimal;
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
	 Clientes cliente = new Clientes();
	
	

	public void menuReservas() {
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
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
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
                formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime fechaSalida = null;
                try {
                    fechaSalida = LocalDateTime.parse(fechaSalidaStr, formatter);
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
				BigDecimal precioTotal = scanner.nextBigDecimal();
                scanner.nextLine();

				System.out.println("Ingrese las observaciones");
				String observaciones = scanner.nextLine();
                scanner.nextLine();

				
               Reservas reserva = new Reservas();

				
				reserva.setCliente(cliente);
				reserva.setServicios(servicio);
				reserva.setFechaEntradaReserva(fechaInicio);
				reserva.setFechaSalidaReserva(fechaSalida);
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
				menuActualizar();
				break;
				
			case 4:
				
				break;
				
			case 5:
				
				break;
			}
		}	
		}
	
	public void menuActualizar() {
		Scanner scanner = new Scanner(System.in);
EntityManager entityManager = Mariadb_connect.getEntityManagerFactory().createEntityManager();        
        System.out.print("Ingrese el id de la reserva que desea actualizar: ");
        long idReserva = scanner.nextLong();
        Reservas reserva = entityManager.find(Reservas.class, idReserva);
        
        if (reserva == null) {
            System.out.println("No se encontró ninguna reserva con ese id.");
            return;
        }
        
        System.out.println("Reserva actual:");
        System.out.println(reserva.toString());
        
        System.out.print("Ingrese el campo que desea actualizar (cliente, servicios, numReserva, fechaEntradaReserva, fechaSalidaReserva, tipoHabitacionReserva, numHusepedReserva, precioTotalReserva, observacionesReserva): ");
        String campo = scanner.next();
        
        System.out.print("Ingrese el nuevo valor del campo: ");
        String valor = scanner.next();
        
        switch (campo) {
            case "cliente":
                long idCliente = Long.parseLong(valor);
                Clientes cliente = entityManager.find(Clientes.class, idCliente);
                reserva.setCliente(cliente);
                break;
            case "servicios":
                long idServicios = Long.parseLong(valor);
                Servicios servicios = entityManager.find(Servicios.class, idServicios);
                reserva.setServicios(servicios);
                break;
            case "numReserva":
                int numReserva = Integer.parseInt(valor);
                reserva.setNumReserva(numReserva);
                break;
            case "fechaEntradaReserva":
                LocalDate fechaEntradaReserva = LocalDate.parse(valor);
                reserva.setFechaEntradaReserva(fechaEntradaReserva);
                break;
            case "fechaSalidaReserva":
                LocalDateTime fechaSalidaReserva = LocalDateTime.parse(valor);
                reserva.setFechaSalidaReserva(fechaSalidaReserva);
                break;
            case "tipoHabitacionReserva":
                reserva.setTipoHabitacionReserva(valor);
                break;
            case "numHusepedReserva":
                int numHusepedReserva = Integer.parseInt(valor);
                reserva.setNumHusepedReserva(numHusepedReserva);
                break;
            case "precioTotalReserva":
                BigDecimal precioTotalReserva = new BigDecimal(valor);
                reserva.setPrecioTotalReserva(precioTotalReserva);
                break;
            case "observacionesReserva":
                reserva.setObservacionesReserva(valor);
                break;
            default:
                System.out.println("Campo inválido.");
                return;
        }
        
        entityManager.getTransaction().begin();
        entityManager.merge(reserva);
        entityManager.getTransaction().commit();
        
        System.out.println("Reserva actualizada:");
        System.out.println(reserva.toString());
        
        entityManager.close();
		
		
	}
}
