import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class CipherServiceTest {

    @TempDir
    Path tempDir;

    @Test
    void decipherChangesMappedCharacters_keepsOthers() {
        CipherService service = new CipherService();

        // plain abc -> cipher bca (encipher)
        // decipher inverse: b->a, c->b, a->c
        CipherKey key = new CipherKey("abc", "bca");

        String cipherText = "b c!\n-a";
        String expected  = "a b!\n-c";

        assertEquals(expected, service.decipher(cipherText, key));
    }

    @Test
    void loadKey_validFile_returnsKeyThatDeciphers() throws Exception {
        CipherService service = new CipherService();

        // your real key format (2 lines)
        String line1 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        String line2 = "bcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890a";

        Path keyFile = tempDir.resolve("key.txt");
        Files.writeString(keyFile, line1 + "\n" + line2 + "\n");

        CipherKey key = service.loadKey(keyFile.toString());

        // inverse check: 'b' should decode to 'a', and 'a' should decode to '0'
        assertEquals('a', key.decipherChar('b'));
        assertEquals('0', key.decipherChar('a'));
    }

    @Test
    void loadKey_missingFile_throwsIOException() {
        CipherService service = new CipherService();

        Path missing = tempDir.resolve("nope.txt");
        assertThrows(IOException.class, () -> service.loadKey(missing.toString()));
    }
}
