package ua.javarush.encoder;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileService {

    private CaesarCipher caesarCipher;

    public FileService(CaesarCipher caesarCipher) {
        this.caesarCipher = caesarCipher;
    }

    public String readFile(String filePath) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader
                (filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }
        }
        return stringBuilder.toString();

    }

    public void writeFile(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter
                (new FileWriter(filePath))) {
            writer.write(content);
        }
    }
}
