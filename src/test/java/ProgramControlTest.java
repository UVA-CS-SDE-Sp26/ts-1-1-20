import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProgramControlTest {

    // create Mock of Filehandler
    @Mock
    FileHandler fileHandler;

    // create Mock of CipherService
    @Mock
    CipherService cipherService;

    // use actual ProgramControl.java (with mocks injected)
    @InjectMocks
    ProgramControl programControl;

    @Test
    void getNumberedFileListSortandNumbers() {
        when(fileHandler.listDataFiles())
                .thenReturn(List.of("fileb.txt", "filea.txt", "filec.txt"));

        List<String> result = programControl.getNumberedFileList();

        assertEquals(
                List.of("01 filea.txt", "02 fileb.txt", "03 filec.txt"),
                result
        );

        verify(fileHandler).listDataFiles();
        verifyNoInteractions(cipherService);
    }

    /*
    ==========================================================
   Can't do cipher tests
    ==========================================================

    @Test
    void getFileByNumberValidUseDefaultKey() throws Exception {

        when(fileHandler.listDataFiles())
                .thenReturn(List.of("b.txt", "a.txt"));

        when(fileHandler.readFile("a.txt"))
                .thenReturn("CIPHER");

        CipherKey key = mock(CipherKey.class);

        when(cipherService.loadKey("ciphers/key.txt"))
                .thenReturn(key);

        when(cipherService.decipher("CIPHER", key))
                .thenReturn("PLAIN");

        String result = programControl.getFileByNumber("01", null);

        assertEquals("PLAIN", result);

        verify(fileHandler).listDataFiles();
        verify(fileHandler).readFile("a.txt");
        verify(cipherService).loadKey("ciphers/key.txt");
        verify(cipherService).decipher("CIPHER", key);
    }

    @Test
    void getFileByNumberValidUseAlternateKey() throws Exception {

        when(fileHandler.listDataFiles())
                .thenReturn(List.of("a.txt"));

        when(fileHandler.readFile("a.txt"))
                .thenReturn("CIPHER");

        CipherKey key = mock(CipherKey.class);

        when(cipherService.loadKey("mykey.txt"))
                .thenReturn(key);

        when(cipherService.decipher("CIPHER", key))
                .thenReturn("PLAIN");

        String result = programControl.getFileByNumber("01", "mykey.txt");

        assertEquals("PLAIN", result);

        verify(cipherService).loadKey("mykey.txt");
    }

    @Test
    void getFileByNumberKeyLoadFailureThrowsIOException() throws Exception {

        when(fileHandler.listDataFiles()).thenReturn(List.of("a.txt"));
        when(fileHandler.readFile("a.txt")).thenReturn("CIPHER");

        when(cipherService.loadKey("ciphers/key.txt"))
                .thenThrow(new IOException("missing key"));

        assertThrows(
                IOException.class,
                () -> programControl.getFileByNumber("01", null)
        );

        verify(fileHandler).listDataFiles();
        verify(fileHandler).readFile("a.txt");
        verify(cipherService).loadKey("ciphers/key.txt");
        verifyNoMoreInteractions(cipherService);
    }

    ==========================================================
    END DISABLED BLOCK
    ==========================================================
    */

    @Test
    void getFileByNumberInvalidFormatThrows() {
        assertThrows(
                IllegalArgumentException.class,
                () -> programControl.getFileByNumber("1", null)
        );

        verifyNoInteractions(fileHandler);
        verifyNoInteractions(cipherService);
    }

    @Test
    void getFileByNumberOutOfRangeThrows() {
        when(fileHandler.listDataFiles())
                .thenReturn(List.of("a.txt"));

        assertThrows(
                IllegalArgumentException.class,
                () -> programControl.getFileByNumber("02", null)
        );

        verify(fileHandler).listDataFiles();
        verifyNoInteractions(cipherService);
    }

    @Test
    void getFileByNumberFileReadFailureThrowsIOException() throws Exception {
        when(fileHandler.listDataFiles())
                .thenReturn(List.of("a.txt"));

        when(fileHandler.readFile("a.txt"))
                .thenThrow(new IOException("missing"));

        assertThrows(
                IOException.class,
                () -> programControl.getFileByNumber("01", null)
        );

        verify(fileHandler).listDataFiles();
        verify(fileHandler).readFile("a.txt");
        verifyNoInteractions(cipherService);
    }
}
