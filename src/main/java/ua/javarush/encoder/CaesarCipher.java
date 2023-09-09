package ua.javarush.encoder;

import ua.javarush.encoder.alphabets.Alphabet_EN;
import ua.javarush.encoder.alphabets.Alphabet_UA;


import java.util.List;
public class CaesarCipher {
    public static String encrypt(String text, int key) {
        StringBuilder encryptedText = new StringBuilder();
        for (char ch : text.toCharArray()) {
            List<Character> alphabet = getAlphabet(ch);
            if (alphabet != null) {
                char encryptedChar;
                int encryptCharPosition = alphabet.indexOf(ch);
                int newEncryptCharPosition = (encryptCharPosition + key) % alphabet.size();
                encryptedChar = alphabet.get(newEncryptCharPosition);
                encryptedText.append(encryptedChar);
            } else {
                encryptedText.append(ch);
            }
        }
        return encryptedText.toString();
    }

    public static String decrypt(String text, int key) {
        StringBuilder decryptedText = new StringBuilder();
        for (char ch : text.toCharArray()) {
            List<Character> alphabet = getAlphabet(ch);
            if (alphabet != null) {
                char decryptedChar;
                int encryptCharPosition = alphabet.indexOf(ch);

                int sizeAlphabet = alphabet.size();
                int decryptCharPosition = (encryptCharPosition - key + sizeAlphabet) % sizeAlphabet;
                if (decryptCharPosition < 0) {
                    decryptCharPosition += sizeAlphabet;
                }
                decryptedChar = alphabet.get(decryptCharPosition);

                decryptedText.append(decryptedChar);
            } else {
                decryptedText.append(ch);
            }
        }
        return decryptedText.toString();
    }

    public static List<Character> getAlphabet(char ch) {
        if (Alphabet_EN.ALPHABET_EN.contains(ch)) {
            return Alphabet_EN.ALPHABET_EN;
        } else if (Alphabet_UA.ALPHABET_UA.contains(ch)) {
            return Alphabet_UA.ALPHABET_UA;
        }
        return null;
    }
}
