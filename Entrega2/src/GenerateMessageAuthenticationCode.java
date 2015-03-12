
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

public class GenerateMessageAuthenticationCode {

	public static byte[] calculateMac(String mensaje,SecretKey key) {
		byte[] digest = null;
		try {

			// get a key generator for the HMAC-MD5 keyed-hashing algorithm
			//KeyGenerator keyGen = KeyGenerator.getInstance("HmacMD5");

			// generate a key from the generator estoy hay que genrarlo fuera del metodo y pasarselo por parametros
			//SecretKey key = keyGen.generateKey();

			// create a MAC and initialize with the above key
			Mac mac = Mac.getInstance(key.getAlgorithm());
			mac.init(key);

			String message = mensaje;

			// get the string as UTF-8 bytes
			byte[] b = message.getBytes("UTF-8");

			// create a digest from the byte array
			digest = mac.doFinal(b);


		}

		catch (NoSuchAlgorithmException e) {
			System.out.println("No Such Algorithm:" + e.getMessage());
		} catch (UnsupportedEncodingException e) {
			System.out.println("Unsupported Encoding:" + e.getMessage());
		} catch (InvalidKeyException e) {
			System.out.println("Invalid Key:" + e.getMessage());
		}
		return digest;

	}
	public static void main (String[]args) throws NoSuchAlgorithmException{
		KeyGenerator keyGen = KeyGenerator.getInstance("HmacMD5");
		SecretKey key = keyGen.generateKey();
		System.out.print(calculateMac("Hola", key));
	}

}