package vista;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import hotel_service2.Clientes;
import hotel_service2.Empleados;
import hotel_service2.Habitaciones;
import hotel_service2.Pagos;
import mariadb.Mariadb_connect;

public class VEmpleado {
	private Empleados empleado = new Empleados();
	private Pagos pago = new Pagos();
	
	public void menuEmpleados() {
		Scanner scanner = new Scanner(System.in);
		Integer opcion = 0;
		do {
			System.out.println("Menu Empleados");
			System.out.println("Elija una opción:");
			System.out.println("1. Crear");
			System.out.println("2. Buscar ");
			System.out.println("3. Actualizar ");
			System.out.println("4. Eliminar ");
			System.out.println("5. Salir");
			
			opcion= scanner.nextInt();
			switch (opcion) {
			case 1:
				System.out.println("Ingrese el ID del Pago");
				Long idPago = scanner.nextLong();
				try {
				List<Pagos> listaPagos = pago.buscar(idPago);
				if(listaPagos == null || listaPagos.isEmpty()) {
					System.out.println("[!] El Pago no exite, ingrese un ID valido...");
				}else {
					pago = listaPagos.get(0);
				}
				}catch(Exception e) {
					System.out.println("Error...");
				}
				
				System.out.println("Ingrese el nombre del Empleado");
				String nombre = scanner.nextLine();
				scanner.nextLine();
				
				System.out.println("Ingrese la Direccion del empleado");
				String direccion = scanner.nextLine();
				
				System.out.println("Ingrese el Telefono del Empleado");
				String telefono = scanner.nextLine();
				
				System.out.println("Ingrese el Email");
				String emailEmp = scanner.nextLine();
				
				System.out.println("Ingrese el usuario");
				String user = scanner.nextLine();
				
				System.out.println("Ingrese una contraseña");
				String pass = scanner.nextLine();
				
				Empleados obj = new Empleados();
				
				obj.setPago(pago);
				obj.setNomEmpleado(nombre);
				obj.setDirEmp(direccion);
				obj.setTelfEmp(telefono);
				obj.setEmailEmp(emailEmp);
				obj.setUser(user);
				obj.setPass(pass);
				
				obj.crear(obj);
				System.out.println("Empleado guardado con exito");
				
				
				break;
				
			case 2: 

				System.out.println("Ingrese el ID del Pago a buscar");
				Long idEmpleado = scanner.nextLong();
				List<Empleados> listaEmp = empleado.buscar(idEmpleado);
				System.out.println("Pago encontrada:");
				for (Empleados e : listaEmp) {
					System.out.println(e.toString());
				}
				break;
				
			case 3:
				actualiza2();
				break;
				
			case 4:
				System.out.println("Ingrese el Id del Empleado que desea eliminar");
				Long idEmpleadoss = scanner.nextLong();
				List<Long> listaEmpleado = new ArrayList<>();
				listaEmpleado.add(idEmpleadoss);
				
				Pagos obj2 = new Pagos();
				obj2.eliminar(listaEmpleado);
				
				
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
		      
		System.out.print("Ingrese el id del Empleado que desea actualizar: ");
		        Long idEmpleado = scanner.nextLong();
		        scanner.nextLine();
		      
		        Empleados empl = new Empleados();
		        empl.setIdEmpleado(idEmpleado);
		       try {
		    	   List<Empleados> listaEmpl = empl.buscar(idEmpleado);
		           if (listaEmpl != null && !listaEmpl.isEmpty()) {
		           	for (Empleados employer : listaEmpl) {
		           		System.out.println("[+] Se encontró una pago con ese id.");
		                   System.out.println(employer.toString());
		           	}
		               
		           }
		          
			} catch (Exception e) {
				// TODO: handle exception
			}
		      
		        
		     
		        Integer opcion = 0;
		       
		        do { 
		        	System.out.println("Que campo deseas actualizar");
		        	System.out.println("1. Nombre de Empleado");
		        	System.out.println("2. Direccion del Empleado");
		        	System.out.println("3. Telefono del Empleado");
		        	System.out.println("4. Email del Empleado");
		        	System.out.println("5. Usuario");
		        	System.out.println("6. Contraseña");
		        	System.out.println("7. Salir");
		        	opcion = scanner.nextInt();
		        	scanner.nextLine();
		        switch (opcion) {
		            case 1:
		              System.out.println("Nuevo Numero de Pagos");
		              String nombre = scanner.nextLine();
		              empl.setNomEmpleado(nombre);  
		            
		            	empl.actualizar(empl.getIdEmpleado(), empl);
		                break;
		            case 2:
		            	System.out.println("Nueva Direccion");
		            	String direccion  = scanner.nextLine();
		            	empl.setDirEmp(direccion);

		                break;
		           
		            case 3:
		            	System.out.print("Ingrese nuevo Telefono");
		                String telefono = scanner.nextLine();
		                empl.setTelfEmp(telefono);

		                
		            	empl.actualizar(empl.getIdEmpleado(), empl);
		                break;
		            
		                
		            case 4:
		            	System.out.println("Ingrese nuevo Correo Electronico");
		            	String correo = scanner.nextLine();
		            	empl.setEmailEmp(correo);

		            	empl.actualizar(empl.getIdEmpleado(), empl);
		            	break;
		            case 5:
		            	System.out.println("Ingrese nuevo Usuario");
		            	String usuario = scanner.nextLine();
		            	empl.setUser(usuario);

		            	
		            	empl.actualizar(empl.getIdEmpleado(), empl);
		            	break;
		            	
		            case 6:
		            	System.out.println("Ingrese nueva Contraseña");
		            	String contraseña = scanner.nextLine();
		            	empl.setPass(contraseña);
		            	empl.actualizar(empl.getIdEmpleado(), empl);
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
