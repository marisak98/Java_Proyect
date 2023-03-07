package mariadb;
import java.io.IOException;
import java.util.Scanner;

import javax.persistence.EntityManager;

import hotel_service2.Clientes;
import hotel_service2.Usuarios;
import vista.VCliente;
import vista.VEmpleado;
import vista.VHabitaciones;
import vista.VPagos;
import vista.VReservas;
import vista.VServicios;
import vista.VUsuario;
public class MainApp {

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int opcion  = 0;

       
        while (opcion!=4) {
			System.out.println("1. CRUD - MENU");
			System.out.println("2. Usuario");
			System.out.println("3. Reportes(No Disponible)");
			System.out.println("4. Salir");
			
		

			opcion = scanner.nextInt();
			switch (opcion) {
			case 1:
				int opcion2 = 0;
				System.out.println("1. Clientes");
				System.out.println("2. Reservas");
				System.out.println("3. Servicios");
				System.out.println("4. Habitaciones");
				System.out.println("5  Pagos");
				System.out.println("6. Empleados");
				opcion2 =scanner.nextInt();
				switch (opcion2) {
				case 1:
					  VCliente obj1 = new VCliente();        
		                obj1.vista();
					break;
				
				case 2: 
					
					 VReservas obj2 = new VReservas();
		        try {
					obj2.menuReservas();
				} catch (Exception e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
					break;
					
				case 3:
					 VServicios obj3 = new VServicios();
				        obj3.menuServicios();
					
					break;
					
				case 4:
					VHabitaciones obj4 = new VHabitaciones();
	        		obj4.menuHabitaciones();
					break;

				case 5:
					 
			        VPagos obj5 = new VPagos();
			        obj5.menuPagos();

					
					break;
					
				case 6:
					 VEmpleado obj6 = new VEmpleado();
				        
				        obj6.menuEmpleados();
					break;
				case 7:
					System.out.println("Voliendo Menu Principal");
					
					break;
					
				default:
					break;
				}
				
				break;
			case 2:
				 VUsuario obj = new VUsuario();
			        obj.menuUsuario();

				break;
			case 3:
				System.out.println("Muy Pronto.....");
			break;
			case 4:
			System.out.println("[-] Saliendo....");
				break;
		

			default:
				System.out.println("Opción no válida\n");
			break;
		}
		}
}

}
