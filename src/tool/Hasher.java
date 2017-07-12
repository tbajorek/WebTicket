package tool;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {
	public static String getHash(String input) {
		try {
	        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
	        byte[] inputBytes = input.getBytes();
	        byte[] inputHash = sha256.digest(inputBytes);             
	        StringBuilder sb = new StringBuilder();
	        for(int i=0; i< inputHash.length ;i++) {
	            sb.append(Integer.toString((inputHash[i] & 0xff) + 0x100, 16).substring(1));         
	        }
	        return sb.toString();
	    } catch (NoSuchAlgorithmException e) { e.printStackTrace(); }       
	    return null;
	}
}