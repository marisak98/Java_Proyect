import socket
import sys
import jpype
from jpype import java
from jpype import javax

#from clientes import Clientes

jpype.startJVM(classpath=['/home/marisak/eclipse-workspace/hotel_service2/target/classes/hotel_service2'])
#sys.path.append('/home/marisak/eclipse-workspace/hotel_service2/src/main/java/')


from hotel_service2 import Clientes

# configurar la conexión con el servidor Java
HOST = 'localhost'  # dirección IP del servidor
PORT = 4444        # puerto en el que está escuchando el servidor
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((HOST, PORT))

def mostrar_menu():
    print("1. Crear Cliente")
    print("2. Salir")
    opcion = input("Seleccione una opcion: ")
    return opcion

# mostrar el menú y enviar la opción seleccionada al servidor
while True:
    opcion = mostrar_menu()

    if opcion == '1':
        # enviar la opción seleccionada al servidor
        s.sendall(opcion.encode())
        
        # recibir la confirmación del servidor
        data = s.recv(1024).decode()
        print(data)
        
        print("Ingrese los datos del cliente:")
        nomCliente = input("Nombre del cliente: ")
        dirCliente = input("Dirección del cliente: ")
        telCliente = input("Teléfono del cliente: ")
        mailCliente = input("Email del cliente: ")
        contCliente = input("Contacto del cliente: ")
        objCliente = input("Observaciones sobre el cliente: ")
        
        cliente = Clientes.crear(nomCliente, dirCliente, telCliente, mailCliente, contCliente, objCliente)
        
        print("Cliente creado con éxito: ")
        print(f"Nombre: {cliente.getNomCliente()}")
        print(f"Dirección: {cliente.getDirCliente()}")
        print(f"Teléfono: {cliente.getTelCliente()}")
        print(f"Email: {cliente.getMailCliente()}")
        print(f"Contacto: {cliente.getContCliente()}")
        print(f"Observaciones: {cliente.getObjCliente()}")

    elif opcion == '2':
        break
    else:
        print("[!]Opción inválida.")
        
# cerrar la conexión con el servidor
s.close()

