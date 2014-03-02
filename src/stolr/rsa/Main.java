package stolr.rsa;

import java.io.IOException;
import java.math.BigInteger;

public class Main {

	public static void main(String[] args) throws IOException {

		BigInteger a = new BigInteger("4");
		BigInteger b = new BigInteger("13");
		BigInteger e = new BigInteger("497");

		System.out.println(Fonctions.pgcd(new BigInteger("24"), new BigInteger(
				"7")));

		System.out.println(Fonctions.Exp(a, b, e));

		System.out.println(Fonctions.isPrime(new BigInteger("89")));

		System.out.println(Fonctions.estPremierRapide(new BigInteger(
				"1602232699497402941653669"), new BigInteger("355")));

		
		// RSA
		BigInteger n = Rsa.genCle("clé.txt", 256);
		String teststring = "bonjour le monde";
		
		System.out.println("Message à crypter: " + teststring);
		System.out.println("Message en Bytes: " + bytesToString(teststring.getBytes()));

		// Chiffrer
		byte[] chiffrer = Rsa.chiffrer(teststring.getBytes(), n);
		System.out.println("Message crypté en Bytes: " + bytesToString(chiffrer));

		// dechiffrer
		byte[] dechiffrer = Rsa.dechiffrer(chiffrer, n);
		System.out.println("Message decrypté en Bytes: "+ bytesToString(dechiffrer));

		System.out.println("Message decrypté: " + new String(dechiffrer));
		

	}
	private static String bytesToString(byte[] chiffre) {
		String test = "";
		for (byte b : chiffre) {
			test += Byte.toString(b);
		}
		return test;
	}
}
