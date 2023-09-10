package ua.javarush.encoder;


public class Runner {
    public void run() {
        CaesarCipher caesarCipher = new CaesarCipher();
        FileService fileService = new FileService(caesarCipher);
        CLI cli = new CLI(fileService, caesarCipher);
        cli.run();
    }
}
