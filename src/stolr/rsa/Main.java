package stolr.rsa;

import java.math.BigInteger;

public class Main {

	public static void main(String[] args) {

		BigInteger a = new BigInteger("4");
		BigInteger b = new BigInteger("13");
		BigInteger e = new BigInteger("497");
		
		System.out.println(Fonctions.pgcd(new BigInteger("24"), new BigInteger("7")));
		
		System.out.println(Fonctions.Exp(a, b, e));
		

		System.out.println(Fonctions.isPrime(new BigInteger("89")));
		
		System.out.println(Fonctions.estPremierRapide(new BigInteger("1602232699497402941653669"), new BigInteger("355")));
		



	}

}
