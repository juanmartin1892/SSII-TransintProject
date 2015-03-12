package entrega2;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class InstanceKey {
	
	public static String toHexadecimal(byte[] digest){
        String hash = "";
        for(byte aux : digest) {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1) hash += "0";
            hash += Integer.toHexString(b);
        }
        return hash;
    }
	public static void main(String[]args) throws NoSuchAlgorithmException{
	
		KeyGenerator keyGen = KeyGenerator.getInstance("HmacMD5");
		SecretKey key = keyGen.generateKey();
		
		 FileWriter fichero = null;
	     PrintWriter pw = null;
	        try
	        {
	            fichero = new FileWriter("/home/snap/workspace/SSII/Entrega2/key.txt",true);
	            pw = new PrintWriter(fichero);
	            pw.println(toHexadecimal(key.getEncoded()));
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	           try {
	           // Nuevamente aprovechamos el finally para 
	           // asegurarnos que se cierra el fichero.
	           if (null != fichero)
	              fichero.close();
	           } catch (Exception e2) {
	              e2.printStackTrace();
	           }
	        }
	
	
	}
}
