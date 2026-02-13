import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CipherService {

    // Loads a key file from disk and returns a validated CipherKey object
    public CipherKey loadKey(String keyPath) throws IOException, IllegalArgumentException {
        // 1) validate keyPath is not null/blank
        if (keyPath == null || keyPath.trim().isEmpty()) {
            throw new IllegalArgumentException("Key path is missing");
        }

        // 2) read key file lines
        List<String> lines = Files.readAllLines(Path.of(keyPath));

        // 3) extract first two non-empty lines as line1 + line2
        String line1 = null;
        String line2 = null;
        for (String line : lines) {
            if (line == null) continue;
            String trimmed = line.trim();
            if (trimmed.isEmpty()) continue;

            if (line1 == null) line1 = trimmed;
            else {
                line2 = trimmed;
                break;
            }
        }

        // 4) validate required structure
        if (line1 == null || line2 == null) {
            throw new IllegalArgumentException("Key file must contain at least two non-empty lines");
        }
        if (line1.length() != line2.length()) {
            throw new IllegalArgumentException("Key lines must be the same length");
        }
        if (line1.isEmpty()) {
            throw new IllegalArgumentException("Key lines cannot be empty");
        }

        // - no duplicates in either line
        if (hasDuplicates(line1)) {
            throw new IllegalArgumentException("Key line 1 contains duplicate characters");
        }
        if (hasDuplicates(line2)) {
            throw new IllegalArgumentException("Key line 2 contains duplicate characters");
        }

        // - same character set (line2 is a permutation of line1)
        if (!sameCharacterSet(line1, line2)) {
            throw new IllegalArgumentException("Key lines must contain the same set of characters");
        }

        // 5) build CipherKey (precomputes decipher map)
        // 6) return CipherKey
        return new CipherKey(line1, line2);
    }

    private boolean hasDuplicates(String s) {
        Set<Character> seen = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!seen.add(c)) return true;
        }
        return false;
    }

    private boolean sameCharacterSet(String a, String b) {
        // because we already checked lengths and duplicates, set equality is enough
        Set<Character> sa = new HashSet<>();
        Set<Character> sb = new HashSet<>();
        for (int i = 0; i < a.length(); i++) sa.add(a.charAt(i));
        for (int i = 0; i < b.length(); i++) sb.add(b.charAt(i));
        return sa.equals(sb);
    }

    // Converts cipher text into readable text using the provided key
    public String decipher(String cipherText, CipherKey key) {
        // 1) validate cipherText != null
        if (cipherText == null) {
            throw new IllegalArgumentException("Cipher text is missing");
        }

        // 2) validate key != null
        if (key == null) {
            throw new IllegalArgumentException("Cipher key is missing");
        }

        // 3) substitute characters using the decipher map
        StringBuilder out = new StringBuilder(cipherText.length());
        for (int i = 0; i < cipherText.length(); i++) {
            char ch = cipherText.charAt(i);
            out.append(key.decipherChar(ch));
        }

        // 4) return deciphered string
        return out.toString();
    }
}
