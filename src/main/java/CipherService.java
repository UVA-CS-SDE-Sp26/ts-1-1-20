import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CipherService {

    // Loads a key file from disk and returns a validated CipherKey object
    public CipherKey loadKey(String keyPath) throws IOException, IllegalArgumentException {
        // TODO
        // 1) validate keyPath is not null/blank
        // 2) read key file lines
        // 3) extract first two non-empty lines as line1 + line2
        // 4) validate:
        //      - both lines exist and are non-empty
        //      - same length
        //      - no duplicate chars in either line
        //      - same character set (line2 is a permutation of line1)
        // 5) build CipherKey (should precompute decipher map)
        // 6) return CipherKey
        return null;
    }

    // Converts cipher text into readable text using the provided key
    public String decipher(String cipherText, CipherKey key) {
        // TODO
        // 1) validate cipherText != null
        // 2) validate key != null
        // 3) for each character:
        //      - if character exists in key's decipher map, substitute it
        //      - otherwise keep it unchanged (spaces/newlines/punctuation)
        // 4) return deciphered string
        return null;
    }
}
