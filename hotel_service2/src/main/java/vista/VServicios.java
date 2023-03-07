package vista;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.List;
import hotel_service2.Servicios;

public class VServicios {
	private Servicios servicios;

	public void menuServicios() {
		Scanner scanner = new Scanner(System.in);
		Integer opcion = 0;
		
		do {
			System.out.println("1. Crear Servicio");
			System.out.println("2. Buscar Serivio");
			System.out.println("3. Actualizar Servicio");
			System.out.println("4. Eliminar Servicio");
			System.out.println("5. Salir");
			System.out.println("Elija una opción:");
			opcion = scanner.nextInt();
			switch(opcion) {
			case 1:
				System.out.println("Ingrese el nombre del servicio");
				servicios = new Servicios();
				servicios.setIdServicios(null);
				scanner.nextLine();
				servicios.setNombre(scanner.nextLine());
				
				System.out.println("Ingrese el precio del servicio");
				servicios.setPrecio(scanner.nextBigDecimal());
				System.out.println(servicios);
				
				System.out.println("Ingrese las observaciones");
				servicios.setObservaciones(scanner.next());
				System.out.println(servicios);
				
				servicios.crear(servicios);
				
				System.out.println("[+] Servicio creado con exito");
				System.out.println("");
				
				break;
			
			case 2:
				Servicios servicios = new Servicios();
				System.out.println("Ingrese el id del servicio a buscar");
				Long id = scanner.nextLong();
				List<Servicios> listaServicios = servicios.buscar(id);
				System.out.println("[+] Servicio encontrado.");
				for (Servicios s : listaServicios) {
					System.out.println(s.toString());
				}
				
				break;
				
			case 3: 
				menuActualizar();
				break;
				
			case 4:
				System.out.println("Ingrese el ID del servicio a eliminar");
				Long idServicios = scanner.nextLong();
				List<Long> listaServicioEliminar = new ArrayList<>();
				listaServicioEliminar.add(idServicios);
				
				Servicios servicio2 = new Servicios();
				servicio2.eliminar(listaServicioEliminar);
				break;
			}
			
		} while (opcion != 5 );
	}
	
	
	public void menuActualizar() {
	Scanner scanner = new Scanner(System.in);
	System.out.println("[+] Ingrese el Id del servicio a Actualizar");
	Long idServicios = scanner.nextLong();
    scanner.nextLine();

	
	Servicios servicio = new Servicios();
	
	servicio.setIdServicios(idServicios);
	
	List<Servicios> listaServicios = servicio.buscar(idServicios);
	listaServicios.size();
	if (listaServicios != null && !listaServicios.isEmpty()) {
	for (Servicios serv : listaServicios) {
		System.out.println("Cliente encontrado...");
		System.out.println(serv.toString());
	}
		}
	
	System.out.println("Ingrese de nuevo el ID para validar");
	Long IdServicioSelecionada = scanner.nextLong();
	scanner.nextLine();
	
	Servicios servUpdate = null;
	for (Servicios servic : listaServicios) {
	if (servic.getIdServicios().equals(IdServicioSelecionada)) {
		servUpdate = servic;
		break;
	}
	}
	
	if (servUpdate != null) {
		System.out.println("Actualizar Datos");
		System.out.println(servUpdate);
		Integer opcion = 0;
		do {
			System.out.println("Que campo desea Actualizar");
			System.out.println("1. Nombre del Servicio");
			System.out.println("2. Precio del Servicio");
			System.out.println("3. Observaciones del Servicio");
			System.out.println("4. Salir");
			
			opcion = scanner.nextInt();
			switch(opcion) {
			case 1:
				System.out.println("Nuevo nombre:");
				scanner.nextLine();
				String nombre = scanner.nextLine();
				servUpdate.setNombre(nombre);
				servicio.actulizar(servUpdate.getIdServicios(), servUpdate);
				break;
			case 2:
				System.out.println("Nuevo precio");
				scanner.nextBigDecimal();
				BigDecimal precio = scanner.nextBigDecimal();
				servUpdate.setPrecio(precio);
				servicio.actulizar(servUpdate.getIdServicios(), servUpdate);
				break;
			case 3:
				System.out.println("Nuevas observaciones");
				scanner.nextLine();
				String observaciones = scanner.nextLine();
				servUpdate.setObservaciones(observaciones);
				servicio.actulizar(servUpdate.getIdServicios(), servUpdate);
				break;
			case 4:
				System.out.println("[-] Exit...");
				break;
				default:
					System.out.println("[!] Seleccion invalida");
					break;
			}
			
		} while (opcion != 4);
		
	} else {
		System.out.println("No se encontro el servicio en ese ID");
	}
	
	}
}
