package entrega2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class GenerateMessageAuthenticationCode {

	public static String toHexadecimal(byte[] digest) {
		String hash = "";
		for (byte aux : digest) {
			int b = aux & 0xff;
			if (Integer.toHexString(b).length() == 1)
				hash += "0";
			hash += Integer.toHexString(b);
		}
		return hash;
	}

	public static String calculateMac(String cs)

	throws NoSuchAlgorithmException, InvalidKeyException,
			IllegalStateException, UnsupportedEncodingException {

		SecretKey key = null;
		
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		String StringKey = null;

		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File("/home/snap/workspace/SSII/Entrega2/key.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			

			// Lectura del fichero
			String linea;
			
			while ((linea = br.readLine()) != null)
				StringKey = linea;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta
			// una excepcion.
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		key=new SecretKeySpec(StringKey.getBytes(), "HmacMD5");

		Mac mac1 = Mac.getInstance("HmacMD5");

		mac1.init(key);

		mac1.update(cs.getBytes());

		byte[] b = mac1.doFinal();

		return toHexadecimal(b);

	}

	public static void main(String[] args) throws NoSuchAlgorithmException,
			InvalidKeyException, UnsupportedEncodingException {

		KeyGenerator keyGen = KeyGenerator.getInstance("HmacMD5");
		SecretKey key = keyGen.generateKey();

		System.out.print(calculateMac("Hola") + "\n");
		System.out.print(calculateMac("Hola"));
	}

}