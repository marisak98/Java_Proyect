package vista;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import hotel_service2.Usuarios;
import mariadb.Mariadb_connect;

public class VUsuario {
public void menuUsuario() {
	int opcion = 0;
	Scanner scanner = new Scanner(System.in);
	Usuarios usuario;

	EntityManager entity = Mariadb_connect.getEntityManagerFactory().createEntityManager();
	while (opcion!=5) {
		System.out.println("1. Crear Usuario");
		System.out.println("2. Buscar Usuario");
		System.out.println("3. Actualizar Usuario");
		System.out.println("4. Eliminar Usuario");
		System.out.println("5. Salir");
		System.out.println("Elija una opción:");

		opcion = scanner.nextInt();
		switch (opcion) {
		case 1:
			System.out.println("Digite el nombre del usuario:");
			usuario = new Usuarios();
			usuario.setIdUsuario(null);
			scanner.nextLine();
			usuario.setNickname(scanner.nextLine());

			System.out.println("Digite una contraseña");
			usuario.setPassword(scanner.nextLine());
			
			
			
			System.out.println(usuario);
			
			
			entity.getTransaction().begin();
			entity.persist(usuario);
			entity.getTransaction().commit();
			System.out.println("Usuario registrado..");
			System.out.println();
			break;

		case 2:
			System.out.println("Digite el id del usuario a buscar:");
			usuario = new Usuarios();
			usuario = entity.find(Usuarios.class, scanner.nextLong());
			if (usuario != null) {
				System.out.println(usuario);
				System.out.println();
			} else {
				System.out.println();
				System.out.println("Usuario no encontrado... Lista de productos completa");
				List<Usuarios> listaUsuarios= new ArrayList<>();
				Query query=entity.createQuery("SELECT p FROM Producto p");
				listaUsuarios=query.getResultList();
				for (Usuarios p : listaUsuarios) {
					System.out.println(p);
				}
				
				System.out.println();
			}

			break;
		case 3:
			System.out.println("Digite el id del usuario a actualizar:");
			usuario = new Usuarios();

			usuario = entity.find(Usuarios.class, scanner.nextLong());
			if (usuario != null) {
				System.out.println(usuario);
				System.out.println("Digite el nombre del usuario:");
				scanner.nextLine();
				usuario.setNickname(scanner.nextLine());
				System.out.println("Digite el precio del producto:");
				usuario.setPassword(scanner.nextLine());
				entity.getTransaction().begin();
				entity.merge(usuario);
				entity.getTransaction().commit();
				System.out.println("Producto actualizado..");
				System.out.println();
			} else {
				System.out.println("Producto no encontrado....");
				System.out.println();
			}
			break;
		case 4:
			System.out.println("Digite el id del producto a eliminar:");
			usuario= new Usuarios();

			usuario = entity.find(Usuarios.class, scanner.nextLong());
			if (usuario != null) {
				System.out.println(usuario);
				entity.getTransaction().begin();
				entity.remove(usuario);
				entity.getTransaction().commit();
				System.out.println("Producto eliminado...");
			} else {
				System.out.println("Producto no encontrado...");
			}
			break;
		case 5:entity.close();Mariadb_connect.shutdown();
		break;

		default:
			System.out.println("Opción no válida\n");
			break;
		}
	
	
	}
}
}
