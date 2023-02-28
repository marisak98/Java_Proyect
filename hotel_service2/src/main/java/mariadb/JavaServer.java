package mariadb;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import hotel_service2.Clientes;

public class JavaServer extends Clientes {

@SuppressWarnings("resource")
public void service() throws IOException {
	int port = 4444;
ServerSocket severSocket = null;

	severSocket = new ServerSocket(port);



System.out.println("Servidor corriendo en el puert" + port);

while (true) {
	
		Socket clientSocket = severSocket.accept();
		System.out.println("Conexion Establecida con el Cliente.");
		
		InputStream inputStream = clientSocket.getInputStream();
		byte[] buffer =  new byte[1024];
		int byteRead = inputStream.read(buffer);
		String opcion = new String(buffer, 0, byteRead);
		
		if (opcion.trim().equals("1")) {

//			cliente.crear(nomCliente, dirCliente, telCliente, mailCliente, contCliente, objCliente);
			 @SuppressWarnings("unused")
			//Clientes cliente = Clientes.crear(getNomCliente(), getDirCliente(), getTelCliente(), getMailCliente(), getContCliente(), getObjCliente());
			 
			 OutputStream outputStream = clientSocket.getOutputStream();
			String respuesta = "Cliente creado correctamente";
			outputStream.write(respuesta.getBytes());
					}
		clientSocket.close();
	
}

	
	}
  }	

