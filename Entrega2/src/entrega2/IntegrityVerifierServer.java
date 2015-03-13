package entrega2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.net.ServerSocketFactory;

public class IntegrityVerifierServer {
	private ServerSocket serverSocket;

	// Constructor del Servidor
	// Constructor del Servidor
	public IntegrityVerifierServer() throws Exception {
		// ServerSocketFactory para construir los ServerSockets
		ServerSocketFactory socketFactory = (ServerSocketFactory) ServerSocketFactory
				.getDefault();
		// Creación de un objeto ServerSocket escuchando peticiones en el puerto
		// 7070
		serverSocket = (ServerSocket) socketFactory.createServerSocket(7070);
	}

	// Ejecución del servidor para escuchar peticiones de los clientes
	private void runServer() throws InvalidKeyException, NoSuchAlgorithmException, IllegalStateException {
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
				// A continuación habría que calcular el mac del MensajeEnviado
				// que podría ser
				String macdelMensajeEnviado = input.readLine();
				// mac del MensajeCalculado
				
				
				
				String macdelMensajeCalculado = entrega2.GenerateMessageAuthenticationCode.calculateMac(mensaje);
				
				
				if (macdelMensajeEnviado.equals(macdelMensajeCalculado)) {
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

	public static void main(String args[]) throws Exception {
		IntegrityVerifierServer server = new IntegrityVerifierServer();
		server.runServer();
	}
}