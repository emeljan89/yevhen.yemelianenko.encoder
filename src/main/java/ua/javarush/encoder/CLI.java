package ua.javarush.encoder;

import java.io.IOException;
import java.util.Scanner;


public class CLI {
    public static void main(String[] args) {
        Runner runner = new Runner();
        runner.run();
    }
    private FileService fileService;
    private CaesarCipher caesarCipher;

    public CLI(FileService fileService, CaesarCipher caesarCipher) {
        this.fileService = fileService;
        this.caesarCipher = caesarCipher;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter command (EN (encrypt),DE (decrypt),BF (brute force): ");
        String command = scanner.nextLine().toUpperCase();
        System.out.println("Enter file path: ");
        String filePath = scanner.nextLine();
        int key = 0;
        if (command.equals("EN") || command.equals("DE")) {
            System.out.println("Enter key: ");
            key = scanner.nextInt();
        }
        try {
            String text = fileService.readFile(filePath);
            switch (command) {
                case "EN":
                    String encryptedContent = caesarCipher.encrypt(text, key);
                    System.out.println(encryptedContent);
                    fileService.writeFile(getEncryptedFileName(filePath), encryptedContent);
                    System.out.println("Encrypt completed.");
                    break;

                case "DE":
                    System.out.println("Enter key:");
                    key = scanner.nextInt();
                    String decryptedContent = caesarCipher.decrypt(text, key);
                    System.out.println(decryptedContent);
                    fileService.writeFile(getDecryptedFileName(filePath), decryptedContent);
                    System.out.println("Decrypt completed.");
                    break;

                case "BF":
                    FrequencyAnalyzer.analyzeFrequency(filePath);
                    break;
                default:
                    System.out.println("Invalid command.");
            }
        } catch (IOException e) {
            System.out.println("File not read. Try a different file path");
        }
    }

    private String getEncryptedFileName(String filePath) {
        return filePath.replace(".txt", "[ENCRYPTED].txt");
    }

    private String getDecryptedFileName(String filePath) {
        return filePath.replace("[ENCRYPTED].txt", "[DECRYPTED].txt");
    }
}
