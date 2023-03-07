package vista;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//import javax.persistence.EntityManager;
//import javax.persistence.Query;

import hotel_service2.Clientes;
//import mariadb.Mariadb_connect;
//import hotel_service2.Clientes;

public class VCliente {
	
	private Clientes clientes;
	
	
	public VCliente() {
	clientes = new Clientes();	
	
	}
	
	public void actualizar() {
		Scanner scanner = new Scanner(System.in);
    	System.out.println("Digite el ID del cliente a actualizar:");
        Long idClientes = scanner.nextLong();
        System.out.println("Digite el Nombre del cliente a actualizar");
        String nomCliente = scanner.nextLine();
       scanner.nextLine();

        Clientes clientes = new Clientes();
         clientes.setIdCliente(idClientes);
         clientes.setNomCliente(nomCliente);
        
        List<Clientes> listaClientes = clientes.buscar(idClientes, nomCliente);
	    if (listaClientes != null && !listaClientes.isEmpty()) {
	    	for (Clientes c : listaClientes) {
	    		 System.out.println("Clientes encontrados:");
		        System.out.println(c.toString());   
		    }
	    	System.out.println("Digite el ID del cliente a actualizar:");
	        Long idClienteSeleccionado = scanner.nextLong();
	        scanner.nextLine();
	        
	        Clientes clienteSeleccionado = null;
	        for (Clientes cliente: listaClientes) {
	        	if (cliente.getIdCliente().equals(idClienteSeleccionado)) {
	        		clienteSeleccionado = cliente;
	        		break;
	        	}
	        	
	        }
	        
	        if (clienteSeleccionado != null) {
	        	
	            System.out.println("Cliente a actualizar:");
	            System.out.println(clienteSeleccionado);
	            int opcion;
	            do {
	            System.out.println("¿Qué desea actualizar?");
	            System.out.println("1. Nombre");
	            System.out.println("2. Dirección");
	            System.out.println("3. Teléfono");
	            System.out.println("4. Email");
	            System.out.println("5. Contacto");
	            System.out.println("6. Observaciones");
	            System.out.println("7. Salir");

	             opcion = scanner.nextInt();
	            scanner.nextLine();

	            switch (opcion) {
	                case 1:
	                    System.out.println("Nuevo nombre:");
	                    String nombre = scanner.nextLine();
	                    clienteSeleccionado.setNomCliente(nombre);
	    	            clientes.actualizar2(clienteSeleccionado.getIdCliente(), clienteSeleccionado);

	                    break;
	                case 2:
	                    System.out.println("Nueva dirección:");
	                    String direccion = scanner.nextLine();
	                    clienteSeleccionado.setDirCliente(direccion);
	                    clientes.actualizar2(clienteSeleccionado.getIdCliente(), clienteSeleccionado);
	                    break;
	                case 3:
	                	System.out.println("Nuevo Telefono");
	                	String telefono = scanner.nextLine();
	                	clienteSeleccionado.setTelCliente(telefono);
	                	clientes.actualizar2(clienteSeleccionado.getIdCliente(), clienteSeleccionado);
	                	break;
	                case 4:
	                	System.out.println("Nuevo email");
	                	String email = scanner.nextLine();
	                	clienteSeleccionado.setMailCliente(email);
	                	clientes.actualizar2(clienteSeleccionado.getIdCliente(), clienteSeleccionado);
	                	break;
	                case 5:
	                	System.out.println("Nuevo Contacto");
	                	String contacto = scanner.nextLine();
	                	clienteSeleccionado.setContCliente(contacto);
	                	clientes.actualizar2(clienteSeleccionado.getIdCliente(), clienteSeleccionado);
	                	break;
	                case 6:
	                	System.out.println("Nuevas Observaciones");
	                	String observaciones = scanner.nextLine();
	                	clienteSeleccionado.setObjCliente(observaciones);
	                	clientes.actualizar2(clienteSeleccionado.getIdCliente(), clienteSeleccionado);
	                	break;
	                	
	                case 7:
	                	System.out.println("[+] Hasta luego!");
	                System.exit(0);
	                	break;
	                    
	                    default:
	                    	System.out.println("Seleccion invalida");
	                    	break;
	           }
	        
	    } while (opcion != 6);
	            System.out.println("[+] Cliente actualizado correctamente.");
	        
	    }else {
	    	System.out.println("[!] No se encotraron al Cliente con el ID y nombre indicado.");
	    }

	    }
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
        	
        	 Clientes clientes = new Clientes();
        	    System.out.println("Digite el ID del cliente a buscar:");
        	    Long id = scanner.nextLong();
        	    scanner.nextLine();
        	    System.out.println("Digite el nombre del cliente a buscar:");
        	    String nombre = scanner.nextLine();
        	    List<Clientes> listaClientes = clientes.buscar(id, nombre);  	    
        	    System.out.println("Clientes encontrados:");
        	    for (Clientes c : listaClientes) {
        	        System.out.println(c.toString());
        	    }
            break;
            
        case 3:
             actualizar();
            
            		
            break;
        case 4:
        
            System.out.println("Digite el ID del cliente a eliminar:");
           
            Long idClienteEliminar = scanner.nextLong();
          
            List<Long> listaClientesEliminar = new ArrayList<>();
            
          
            	listaClientesEliminar.add(idClienteEliminar);
            	
          
        	Clientes cliente = new Clientes();
        cliente.eliminar(listaClientesEliminar);

            break;
        case 5:
            System.out.println("Saliendo del programa...");
            break;
        default:
            System.out.println("Opción no válida.");
		}
	}
	
		}
   
	
}
	
