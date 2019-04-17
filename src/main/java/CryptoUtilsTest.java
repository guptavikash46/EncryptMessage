import java.io.*;
import java.util.Scanner;


public class CryptoUtilsTest {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String key = "Mary has one cat";
        File inputFile = new File("document.txt");

        System.out.print("Enter any text to encrypt: ");
        String text = scanner.nextLine();

        FileOutputStream outputStream = new FileOutputStream(inputFile);
        outputStream.write(text.getBytes());

        File encryptedFile = new File("document.encrypted");

        try {
            CryptoUtils.encrypt(key, inputFile, encryptedFile);
        } catch (CryptoException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        File file = new File("document.encrypted");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null)
            System.out.println(st);
    }
}
