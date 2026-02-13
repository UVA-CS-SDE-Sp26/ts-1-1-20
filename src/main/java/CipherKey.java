import java.util.HashMap;
import java.util.Map;

public class CipherKey {

    // store the two alphabets directly (from file)
    private final String fromAlphabet;   // key file line 1
    private final String toAlphabet;     // key file line 2

    // used for deciphering: maps cipherChar -> plainChar (inverse of encipher)
    private final Map<Character, Character> decipherMap;

    // creates CipherKey objects
    public CipherKey(String fromAlphabet, String toAlphabet) {
        if (fromAlphabet == null || toAlphabet == null) {
            throw new IllegalArgumentException("Key alphabets cannot be null");
        }
        if (fromAlphabet.isEmpty() || toAlphabet.isEmpty()) {
            throw new IllegalArgumentException("Key alphabets cannot be empty");
        }
        if (fromAlphabet.length() != toAlphabet.length()) {
            throw new IllegalArgumentException("Key alphabet lines must have the same length");
        }

        this.fromAlphabet = fromAlphabet;
        this.toAlphabet = toAlphabet;
        this.decipherMap = new HashMap<>();

        // build inverse mapping: cipherChar -> plainChar
        for (int i = 0; i < fromAlphabet.length(); i++) {
            char plain = fromAlphabet.charAt(i);
            char cipher = toAlphabet.charAt(i);
            decipherMap.put(cipher, plain);
        }
    }

    // returns the plain character for a given cipher character,
    // or returns the original character if it is not in the map
    public char decipherChar(char cipherChar) {
        Character mapped = decipherMap.get(cipherChar);
        return (mapped != null) ? mapped : cipherChar;
    }

    // helper for CipherService.decipher()
    public Map<Character, Character> getDecipherMap() {
        return decipherMap;
    }

    // getters
    public String getFromAlphabet() {
        return fromAlphabet;
    }

    public String getToAlphabet() {
        return toAlphabet;
    }
}
