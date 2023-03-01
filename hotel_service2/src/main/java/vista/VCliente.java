package vista;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import hotel_service2.Clientes;
import mariadb.Mariadb_connect;
import hotel_service2.Clientes;

public class VCliente {
	
	private Clientes clientes;
	
	public VCliente() {
	clientes = new Clientes();	
	}
	
		
	
	
	public void vista() {
		int opcion = 0;
		Scanner scanner = new Scanner(System.in);
	while (opcion != 5) {
		System.out.println("1. Crear Cliente");
		System.out.println("2. Buscar Cliente");
		System.out.println("3. Actualizar Cliente");
		System.out.println("4. Eliminar Cliente");
		System.out.println("5. Salir");
		System.out.println("Elija una opción:");
		
		opcion = scanner.nextInt();
		switch (opcion) {
		case 1:
			System.out.println("Digite el nombre del cliente:");
			clientes = new Clientes();
			clientes.setIdCliente(null);
			scanner.nextLine();
			clientes.setNomCliente(scanner.nextLine());

			System.out.println("Digite la Direccion del producto:");
			clientes.setDirCliente(scanner.nextLine());
			System.out.println(clientes);
			
			System.out.println("Digite el Telefono del cliente ");
			clientes.setTelCliente(scanner.nextLine());
			System.out.println(clientes);
			
			System.out.println("Digete el Email del Cliente");
			clientes.setMailCliente(scanner.nextLine());
			System.out.println(clientes);
			
			
			clientes.crear(clientes );
			
			System.out.println("Cliente registrado..");
			System.out.println();
			break;
			
			  
        case 2:
        	
            System.out.println("Digite el ID del cliente a buscar:");
            int idCliente = scanner.nextInt();
            clientes.buscar(); // utiliza el método buscarCliente de la Clase Clientes
            break;
//        case 3:
//            System.out.println("Digite el ID del cliente a actualizar:");
//            int idClienteActualizar = scanner.nextInt();
//            scanner.nextLine();
//            
//            System.out.println("Digite el nuevo nombre del cliente:");
//            String nuevoNombreCliente = scanner.nextLine();
//            clientes.actualizarCliente(idClienteActualizar, nuevoNombreCliente); // utiliza el método actualizarCliente de la Clase Clientes
//            break;
//        case 4:
//            System.out.println("Digite el ID del cliente a eliminar:");
//            int idClienteEliminar = scanner.nextInt();
//            clientes.eliminarCliente(idClienteEliminar); // utiliza el método eliminarCliente de la Clase Clientes
//            break;
//        case 5:
//            System.out.println("Saliendo del programa...");
//            break;
        default:
            System.out.println("Opción no válida.");
		}
	}
	scanner.close();
		}
}
