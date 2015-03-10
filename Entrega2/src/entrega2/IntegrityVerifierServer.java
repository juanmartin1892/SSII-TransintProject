package entrega2;

import java.net.ServerSocket;

import java.io.*;
import javax.swing.*;
import javax.net.*;

public class IntegrityVerifierServer {
	private ServerSocket serverSocket;

	// Constructor del Servidor
	public IntegrityVerifierServer() throws Exception {
		// ServerSocketFactory para construir los ServerSockets
		ServerSocketFactory socketFactory = (ServerSocketFactory) ServerSocketFactory
				.getDefault();
		// Creaci�n de un objeto ServerSocket escuchando peticiones en el puerto
		// 7070
		serverSocket = (ServerSocket) socketFactory.createServerSocket(7070);
	}

	// Ejecuci�n del servidor para escuchar peticiones de los clientes
	private void runServer() {
		while (true) {
			// Espera las peticiones del cliente para comprobar mensaje/MAC
			try {
				System.err.println("Esperando conexiones de clientes...");
				Socket socket = (Socket) serverSocket.accept();
				// Abre un BufferedReader para leer los datos del cliente
				BufferedReader input = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				// Abre un PrintWriter para enviar datos al cliente
				PrintWriter output = new PrintWriter(new OutputStreamWriter(
						socket.getOutputStream()));
				// Se lee del cliente el mensaje y el macdelMensajeEnviado
				String mensaje = input.readLine();
				// A continuaci�n habr�a que calcular el mac del MensajeEnviado
				// que podr�a ser
				String macdelMensajeEnviado = input.readLine();
				// mac del MensajeCalculado

				if (macMensajeEnviado.equals(macdelMensajeCalculado)) {
					output.println("Mensaje enviado integro ");
				} else {
					output.println("Mensaje enviado no integro.");
				}
				output.close();
				input.close();
				socket.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}
}
