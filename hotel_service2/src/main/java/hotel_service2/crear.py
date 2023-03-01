# -*- coding: utf-8 -*-
import os
import sys

pyjnius_path = '/home/marisak/.local/lib/python3.9/site-packages'
if pyjnius_path not in sys.path:
    os.environ['PYTHONPATH'] = pyjnius_path + os.pathsep + os.environ.get('PYTHONPATH', '')
    sys.path.insert(0, pyjnius_path)

from jnius import autoclass
import socket

# configurar la conexión por socket
TCP_IP = 'localhost'
TCP_PORT = 4444
BUFFER_SIZE = 1024

# ruta del archivo Servidor.jar
jar_file = '/home/marisak/Desktop/Servidor.jar'

# agregar la ruta del archivo jar al CLASSPATH
autoclass('java.lang.System').setProperty('java.class.path', jar_file)

# importar la clase Clientes del paquete hotel_service2.clientes
Clientes = autoclass('hotel_service2.Clientes')

# crear un objeto de la clase Clientes
clientes = Clientes()

# llamar al método crear del objeto Clientes
def crear_cliente(nomCliente, dirCliente, telCliente, mailCliente, contCliente, objCliente):
    return clientes.crear(nomCliente, dirCliente, telCliente, mailCliente, contCliente, objCliente)

# mostrar el menú y llamar a la función correspondiente
def mostrar_menu(conn):
    conn.send("1. Crear Cliente\n2. Salir\nSeleccione una opcion: ".encode())
    opcion = conn.recv(BUFFER_SIZE).decode().strip()
    return opcion

def crear_cliente_python(conn):
    conn.send("Ingrese los datos del cliente:\n".encode())
    conn.send("Nombre del cliente: ".encode())
    nomCliente = conn.recv(BUFFER_SIZE).decode().strip()
    conn.send("Dirección del cliente: ".encode())
    dirCliente = conn.recv(BUFFER_SIZE).decode().strip()
    conn.send("Teléfono del cliente: ".encode())
    telCliente = conn.recv(BUFFER_SIZE).decode().strip()
    conn.send("Email del cliente: ".encode())
    mailCliente = conn.recv(BUFFER_SIZE).decode().strip()
    conn.send("Contacto del cliente: ".encode())
    contCliente = conn.recv(BUFFER_SIZE).decode().strip()
    conn.send("Observaciones sobre el cliente: ".encode())
    objCliente = conn.recv(BUFFER_SIZE).decode().strip()

    # llamar a la función crear_cliente y enviar el resultado
    cliente = crear_cliente(nomCliente, dirCliente, telCliente, mailCliente, contCliente, objCliente)
    conn.send(("Cliente creado con éxito: " + cliente.toString() + "\n").encode())

# establecer la conexión por socket y esperar solicitudes
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((TCP_IP, TCP_PORT))
s.listen(1)

while True:
    conn, addr = s.accept()
    print('Conexión establecida con', addr)

    while True:
        opcion = mostrar_menu(conn)

        if opcion == '1':
            crear_cliente_python(conn)

        elif opcion == '2':
            conn.send("Adiós\n".encode())
            conn.close()
            break

        else:
            conn.send("[!]Opción inválida.\n".encode())

