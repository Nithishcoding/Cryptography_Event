import java.util.Scanner;

public class AffineCipher {

    public static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    public static String affineEncrypt(String plaintext, int a, int b) {
        StringBuilder ciphertext = new StringBuilder();
        for (char ch : plaintext.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                ciphertext.append((char) ((a * (ch - base) + b) % 26 + base));
            } else {
                ciphertext.append(ch);
            }
        }
        return ciphertext.toString();
    }

    public static String affineDecrypt(String ciphertext, int a, int b) {
        StringBuilder plaintext = new StringBuilder();
        int aInv = 0;
        for (int i = 0; i < 26; i++) {
            if ((a * i) % 26 == 1) {
                aInv = i;
                break;
            }
        }
        for (char ch : ciphertext.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                plaintext.append((char) ((aInv * (ch - base - b + 26)) % 26 + base));
            } else {
                plaintext.append(ch);
            }
        }
        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String text = scanner.nextLine();

        System.out.print("Enter key 'K1' (must be coprime with 26): ");
        int a = scanner.nextInt();

        System.out.print("Enter key 'K2': ");
        int b = scanner.nextInt();

        if (gcd(a, 26) != 1) {
            System.out.println("Error: 'K1' and 26 must be coprime.");
            return;
        }

        String encrypted = affineEncrypt(text, a, b);
        String decrypted = affineDecrypt(encrypted, a, b);

        System.out.println("Original: " + text);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}

/*Enter plaintext: CRYPTOGRAPHY
Enter key 'K1' (must be coprime with 26): 7
Enter key 'K2': 3
 */
/*output:Original: CRYPTOGRAPHY
Encrypted: ZAVFMLZAMLVN
Decrypted: CRYPTOGRAPHY
 */