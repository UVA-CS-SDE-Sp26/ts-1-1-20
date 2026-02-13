import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTest {
    @TempDir
    Path tempDir;

    @Test
    void listDataFiles_returnsSortedFileNames() throws IOException {
        Files.writeString(tempDir.resolve("b.txt"), "B");
        Files.writeString(tempDir.resolve("a.txt"), "A");
        Files.createDirectory(tempDir.resolve("folder"));

        FileHandler fh = new FileHandler(tempDir);

        assertEquals(List.of("a.txt", "b.txt"), fh.listDataFiles());
    }

    @Test
    void readFile_readsContent_andRejectsMissingFile() throws IOException {
        Files.writeString(tempDir.resolve("filea.txt"), "Hello");

        FileHandler fh = new FileHandler(tempDir);

        assertEquals("Hello", fh.readFile("filea.txt"));
        assertThrows(IllegalArgumentException.class, () -> fh.readFile("missing.txt"));
    }

    @Test
    void readKeyFile_readsLines_andKeyExistsWorks() throws IOException {
        Path keyFile = tempDir.resolve("key.txt");
        Files.writeString(keyFile, "abc\ndef\n");

        FileHandler fh = new FileHandler(tempDir);

        assertEquals(List.of("abc", "def"), fh.readKeyFile(keyFile.toString()));
        assertTrue(fh.keyExists(keyFile.toString()));
        assertFalse(fh.keyExists(tempDir.resolve("nope.txt").toString()));
    }
}
