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
    void decipher_worksAndKeepsPunctuation() {
        CipherService service = new CipherService();

        CipherKey key = new CipherKey("abc", "bca");

        String cipherText = "b c!\n-a";
        String expected = "a b!\n-c";

        assertEquals(expected, service.decipher(cipherText, key));
    }

    @Test
    void decipher_nullText_throws() {
        CipherService service = new CipherService();
        CipherKey key = new CipherKey("abc", "bca");

        assertThrows(IllegalArgumentException.class, () -> service.decipher(null, key));
    }

    @Test
    void loadKey_validFile_loadsAndDeciphers() throws Exception {
        CipherService service = new CipherService();

        String line1 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        String line2 = "bcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890a";

        Path keyFile = tempDir.resolve("key.txt");
        Files.writeString(keyFile, line1 + "\n" + line2 + "\n");

        CipherKey key = service.loadKey(keyFile.toString());

        assertEquals('a', key.decipherChar('b'));
        assertEquals('0', key.decipherChar('a'));
    }

    @Test
    void loadKey_missingFile_throwsIOException() {
        CipherService service = new CipherService();

        Path missing = tempDir.resolve("missing.txt");

        assertThrows(IOException.class, () -> service.loadKey(missing.toString()));
    }
}
