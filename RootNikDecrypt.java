import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;

public class RootNikDecrypt {
    public static void main(String[] args) throws Exception {
    // The password is now fixed and does not need to be prompted for
    String password = "#xaJ&kl+";

    // Prompt the user for the file path
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter file path: ");
    String filePath = sc.nextLine();

    // Replace backslashes with forward slashes
    filePath = filePath.replace("\\", "/");

    // Read the encrypted file
    FileInputStream in = new FileInputStream(filePath);
    CipherInputStream cis = new CipherInputStream(in, getCipher(Cipher.DECRYPT_MODE, password));

    // Write the decrypted file
    FileOutputStream out = new FileOutputStream("Decrypted.zip");
    byte[] buffer = new byte[1024];
    int length;
    while ((length = cis.read(buffer)) > 0) {
        out.write(buffer, 0, length);
    }

    // Close the streams
    cis.close();
    in.close();
    out.close();
}

private static Cipher getCipher(int mode, String password) throws Exception {
    // Create a key
    Key key = new SecretKeySpec(password.getBytes(), "DES");

    // Create a cipher
    Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
    cipher.init(mode, key);

    return cipher;
}
}