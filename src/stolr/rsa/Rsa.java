package stolr.rsa;

import java.math.BigInteger;
import java.util.Random;
import java.io.*;

public class Rsa {

	

	public static BigInteger genCle(String nomFichier, int taille) {
		BigInteger p, q, N, phi, publique, prive;

		Random r;

		r = new Random();
		p = BigInteger.probablePrime(taille, r);
		q = BigInteger.probablePrime(taille, r);
		N = p.multiply(q);

		phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		publique = BigInteger.probablePrime(taille / 2, r);

		while (phi.gcd(publique).compareTo(BigInteger.ONE) > 0
				&& publique.compareTo(phi) < 0) {
			publique.add(BigInteger.ONE);
		}
		prive = publique.modInverse(phi);

		System.out.println("valeur de la clé publique :" + publique);
		System.out.println("valeur de la clé prive :" + prive);

		PrintStream publicOut = null;
		try {
			publicOut = new PrintStream(new FileOutputStream(nomFichier));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		publicOut.println(publique + "\n" + prive + "\n");

		return N;
	}

	

	public static byte[] chiffrer(byte[] message, BigInteger N) throws IOException {
		
		InputStream ips = new FileInputStream("clé.txt");
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);
		String ligne;
		ligne = br.readLine();
		System.out.println(ligne);
		BigInteger publique = new BigInteger(ligne);
		
		return (new BigInteger(message)).modPow(publique, N).toByteArray();
	}

	public static byte[] dechiffrer(byte[] message, BigInteger N) throws IOException {
		
		InputStream ips = new FileInputStream("clé.txt");
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);
		String ligne;
		ligne = br.readLine();
		ligne = br.readLine();
		System.out.println(ligne);
		BigInteger prive = new BigInteger(ligne);
		return (new BigInteger(message)).modPow(prive, N).toByteArray();
	}

}