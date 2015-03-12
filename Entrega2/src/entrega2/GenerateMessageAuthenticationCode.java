package entrega2;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;



public class GenerateMessageAuthenticationCode {
	
	public static String toHexadecimal(byte[] digest){
        String hash = "";
        for(byte aux : digest) {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1) hash += "0";
            hash += Integer.toHexString(b);
        }
        return hash;
    }

	public static String calculateMac(String cs, SecretKey key)
	
		
	
			throws NoSuchAlgorithmException, InvalidKeyException, IllegalStateException, UnsupportedEncodingException {
		
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

		System.out.print(calculateMac("Hola", key) + "\n");
		System.out.print(calculateMac("Hola", key));
	}

	

}