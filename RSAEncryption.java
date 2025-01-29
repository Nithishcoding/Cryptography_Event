import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RSAEncryption {

    private BigInteger p, q, n, phi, e, d;

    public RSAEncryption(BigInteger p, BigInteger q) {
        this.p = p;
        this.q = q;
        if (p.equals(q)) {
            throw new IllegalArgumentException("p and q must be distinct prime numbers.");
        }
        if (!p.isProbablePrime(10) || !q.isProbablePrime(10)) {
            throw new IllegalArgumentException("Both p and q must be prime numbers.");
        }

        n = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        e = BigInteger.valueOf(2);
        while (phi.gcd(e).intValue() != 1) {
            e = e.add(BigInteger.ONE);
        }

        d = e.modInverse(phi);
    }

    public List<BigInteger> encrypt(String plaintext) {
        List<BigInteger> ciphertext = new ArrayList<>();
        for (char letter : plaintext.toUpperCase().toCharArray()) {
            if (Character.isLetter(letter)) {
                int letterValue = letter - 'A';
                ciphertext.add(BigInteger.valueOf(letterValue).modPow(e, n));
            }
        }
        return ciphertext;
    }

    public String decrypt(List<BigInteger> ciphertext) {
        StringBuilder decryptedText = new StringBuilder();
        for (BigInteger value : ciphertext) {
            int decryptedValue = value.modPow(d, n).intValue();
            decryptedText.append((char) (decryptedValue + 'A'));
        }
        return decryptedText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter prime number p: ");
            BigInteger p = scanner.nextBigInteger();

            System.out.print("Enter prime number q: ");
            BigInteger q = scanner.nextBigInteger();

            RSAEncryption rsa = new RSAEncryption(p, q);

            System.out.println("Public Key (e, n): (" + rsa.e + ", " + rsa.n + ")");
            System.out.println("Private Key (d, n): (" + rsa.d + ", " + rsa.n + ")");

            System.out.print("Enter plaintext to encrypt (only letters): ");
            scanner.nextLine(); 
            String plaintext = scanner.nextLine();

            List<BigInteger> ciphertext = rsa.encrypt(plaintext);
            System.out.println("Ciphertext: " + ciphertext);

            String decryptedText = rsa.decrypt(ciphertext);
            System.out.println("Decrypted: " + decryptedText);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

/*Enter prime number p: 47
Enter prime number q: 59
Enter plaintext to encrypt (only letters): RSA
 */
/*output:Public Key (e, n): (3, 2773)
Private Key (d, n): (1779, 2773)
Ciphertext: [2140, 286, 0]
Decrypted: RSA
 */