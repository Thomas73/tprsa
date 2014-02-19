package stolr.rsa;

import java.math.BigInteger;

import java.util.Random;


public class Fonctions {

	// fonction PGCD
	public static BigInteger pgcd(BigInteger m, BigInteger n) {
		while (n != BigInteger.ZERO) {
			BigInteger r = m.mod(n);
			m = n;
			n = r;
		}
		return m;
	}

	// End fonction PGCD

	public static Boolean isPrime(BigInteger num) {
		BigInteger deux = new BigInteger("2");
		BigInteger trois = new BigInteger("3");
		if (num.mod(deux).equals(0)) {
			return (num.equals(deux));
		}

		BigInteger m = sqrt(num);

		for (BigInteger i = trois; m.compareTo(i) == 1; i = i.add(deux)) {
			BigInteger mod = num.mod(i);
			if (mod.equals(BigInteger.ZERO)) {
				return (false);
			}
		}
		return (true);

	}

	public static BigInteger Exp(BigInteger b, BigInteger c, BigInteger n) {

		BigInteger result = BigInteger.ONE;
		while (c != BigInteger.ZERO) {
			if ((c.and(BigInteger.ONE)).equals(BigInteger.ONE)) {
				result = ((result.multiply(b)).mod(n));
			}

			c = c.shiftRight(1); // divise par 2
			b = (b.multiply(b)).mod(n);
		}

		return result;

	}


	public static BigInteger modInv(BigInteger a, BigInteger b) {

		BigInteger r0 = a;
		BigInteger r1 = b;
		BigInteger t0 = BigInteger.ZERO;
		BigInteger t = BigInteger.ONE;
		BigInteger q = b.divide(r0);
		BigInteger r = r1.subtract(q.multiply(r0));

		while (r.compareTo(BigInteger.ZERO) > 0) {

			BigInteger biginttemp = t0.subtract(q.multiply(t));

			if (biginttemp.compareTo(BigInteger.ZERO) > 0)
				biginttemp = biginttemp.mod(b);
			if (biginttemp.compareTo(BigInteger.ZERO) < 0)
				biginttemp = b.subtract(biginttemp.negate().mod(b));

			t0 = t;
			t = biginttemp;
			r1 = r0;
			r0 = r;
			q = r1.divide(r0);
			r = r1.subtract(q.multiply(r0));

		}
		
		if (!r0.equals(BigInteger.ONE))
			return null;

		
		else
			return t.mod(b);
	}


	public static BigInteger sqrt(BigInteger n) {
		BigInteger a = BigInteger.ONE;
		BigInteger b = new BigInteger(n.shiftRight(5).add(new BigInteger("8"))
				.toString());
		while (b.compareTo(a) >= 0) {
			BigInteger mid = new BigInteger(a.add(b).shiftRight(1).toString());
			if (mid.multiply(mid).compareTo(n) > 0)
				b = mid.subtract(BigInteger.ONE);
			else
				a = mid.add(BigInteger.ONE);
		}
		return a.subtract(BigInteger.ONE);
	}


	private static boolean estProbablementPremier(BigInteger n, Random s) {

		// Ensures that temp > 1 and temp < n.
		BigInteger temp = BigInteger.ZERO;
		do {
			temp = new BigInteger(n.bitLength() - 1, s);
		} while (temp.compareTo(BigInteger.ONE) <= 0);

		// Screen out n if our random number happens to share a factor with n.
		if (!n.gcd(temp).equals(BigInteger.ONE))
			return false;

		BigInteger base = n.subtract(BigInteger.ONE);
		BigInteger TWO = new BigInteger("2");

		int k = 0;
		while ((base.mod(TWO)).equals(BigInteger.ZERO)) {
			base = base.divide(TWO);
			k++;
		}

		BigInteger curValue = temp.modPow(base, n);
		if (curValue.equals(BigInteger.ONE))
			return true;

		for (int i = 0; i < k; i++) {

			if (curValue.equals(n.subtract(BigInteger.ONE)))
				return true;
			else
				curValue = curValue.modPow(TWO, n);
		}
		return false;
	}

	public static Boolean estPremierRapide(BigInteger n, BigInteger k) {

		Random rnd = new Random();

		while (k.compareTo(BigInteger.ZERO) == 1) {
			if (!estProbablementPremier(n, rnd))
				return false;
			k = k.subtract(BigInteger.ONE);
		}
		return true;
	}


}
