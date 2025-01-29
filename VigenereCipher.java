import java.util.Scanner;

public class VigenereCipher {

    public static String vigenereEncrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        int keyLength = key.length();
        for (int i = 0; i < plaintext.length(); i++) {
            char ch = plaintext.charAt(i);
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                char keyChar = key.charAt(i % keyLength);
                keyChar = Character.toLowerCase(keyChar);
                ciphertext.append((char) ((ch - base + keyChar - 'a') % 26 + base));
            } else {
                ciphertext.append(ch);
            }
        }
        return ciphertext.toString();
    }

    public static String vigenereDecrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();
        int keyLength = key.length();
        for (int i = 0; i < ciphertext.length(); i++) {
            char ch = ciphertext.charAt(i);
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                char keyChar = key.charAt(i % keyLength);
                keyChar = Character.toLowerCase(keyChar);
                plaintext.append((char) ((ch - base - (keyChar - 'a') + 26) % 26 + base));
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

        System.out.print("Enter key: ");
        String key = scanner.nextLine();

        String encrypted = vigenereEncrypt(text, key);
        String decrypted = vigenereDecrypt(encrypted, key);

        System.out.println("Original: " + text);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}
/*Enter plaintext: SECRETMESSAGE
Enter key: KEY
 */
/*output:Original: SECRETMESSAGE
Encrypted: CIXVTNQIWCCIGE
Decrypted: SECRETMESSAGE
 */