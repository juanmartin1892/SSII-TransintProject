package entrega2;

import java.io.*;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.swing.*;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.net.*;

import entrega2.GenerateMessageAuthenticationCode;

public class IntegrityVerifierClient {
	// Constructor que abre una conexi�n Socket para enviar mensaje/MAC al
	// servidor
	public IntegrityVerifierClient() throws InvalidKeyException,
			NoSuchAlgorithmException, IllegalStateException {
		
		try {
			SocketFactory socketFactory = (SocketFactory) SocketFactory
					.getDefault();
			Socket socket = (Socket) socketFactory.createSocket("localhost",
					7070);
			
			// Crea un PrintWriter para enviar mensaje/MAC al servidor
			PrintWriter output = new PrintWriter(new OutputStreamWriter(
					socket.getOutputStream()));
			String mensaje = JOptionPane.showInputDialog(null,
					"Introduzca su mensaje:");
			
			// Envío del mensaje al servidor
			output.println(mensaje);
			
			// Habría que calcular el correspondiente MAC con la clave
			// compartida por servidor/cliente
			output.println(macdelMensaje);
			
			// Importante para que el mensaje se envíe
			output.flush();
			
			// Crea un objeto BufferedReader para leer la respuesta del servidor
			BufferedReader input = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			
			// Lee la respuesta del servidor
			String respuesta = input.readLine();
			
			// Muestra la respuesta al cliente
			JOptionPane.showMessageDialog(null, respuesta);
			
			// Se cierra la conexion
			output.close();
			input.close();
			socket.close();
		} // end try
		catch (IOException ioException) {
			ioException.printStackTrace();
		}
		// Salida de la aplicacion
		finally {
			System.exit(0);
		}
	}

	// ejecución del cliente de verificación de la integridad
	public static void main(String args[]) throws InvalidKeyException, NoSuchAlgorithmException, IllegalStateException {
		new IntegrityVerifierClient();
	}
}