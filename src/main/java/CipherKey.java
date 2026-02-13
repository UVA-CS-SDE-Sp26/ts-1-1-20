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
        // TODO
        // store fields
        // build decipherMap:
        //   for i in 0..length-1:
        //      plain = fromAlphabet.charAt(i)
        //      cipher = toAlphabet.charAt(i)
        //      decipherMap.put(cipher, plain)
        this.fromAlphabet = null;
        this.toAlphabet = null;
        this.decipherMap = new HashMap<>();
    }

    // returns the plain character for a given cipher character,
    // or returns the original character if it is not in the map
    public char decipherChar(char cipherChar) {
        // TODO
        // if decipherMap contains cipherChar -> return mapped char
        // else return cipherChar unchanged
        return cipherChar;
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
