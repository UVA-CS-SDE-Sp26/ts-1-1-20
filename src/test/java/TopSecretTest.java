import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class TopSecretTest {

    // Helper method to capture console output
    private String captureOutput(Runnable runnable) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        System.setOut(new PrintStream(outputStream));
        runnable.run();
        System.setOut(originalOut);

        return outputStream.toString();
    }

    @Test
    void testNoArgsPrintsFileList() {
        ProgramControl mockController = mock(ProgramControl.class);

        when(mockController.getNumberedFileList())
                .thenReturn(List.of("01 fileA.txt", "02 fileB.txt"));

        TopSecret app = new TopSecret(mockController);

        String output = captureOutput(() ->
                app.run(new String[]{}));

        assertTrue(output.contains("01 fileA.txt"));
        assertTrue(output.contains("02 fileB.txt"));

        verify(mockController).getNumberedFileList();
    }

    @Test
    void testOneArgUsesDefaultKey() throws Exception {
        ProgramControl mockController = mock(ProgramControl.class);

        when(mockController.getFileByNumber("01", null))
                .thenReturn("Decrypted mission text");

        TopSecret app = new TopSecret(mockController);

        String output = captureOutput(() ->
                app.run(new String[]{"01"}));

        assertTrue(output.contains("Decrypted mission text"));

        verify(mockController).getFileByNumber("01", null);
    }

    @Test
    void testTwoArgsUsesCustomKey() throws Exception {
        ProgramControl mockController = mock(ProgramControl.class);

        when(mockController.getFileByNumber("01", "mykey.txt"))
                .thenReturn("Secret message");

        TopSecret app = new TopSecret(mockController);

        String output = captureOutput(() ->
                app.run(new String[]{"01", "mykey.txt"}));

        assertTrue(output.contains("Secret message"));

        verify(mockController).getFileByNumber("01", "mykey.txt");
    }

    @Test
    void testTooManyArgsPrintsError() {
        ProgramControl mockController = mock(ProgramControl.class);

        TopSecret app = new TopSecret(mockController);

        String output = captureOutput(() ->
                app.run(new String[]{"01", "key.txt", "extra"}));

        assertTrue(output.contains("Error"));

        verifyNoInteractions(mockController);
    }

    @Test
    void testControllerThrowsExceptionHandledGracefully() throws Exception {
        ProgramControl mockController = mock(ProgramControl.class);

        when(mockController.getFileByNumber("99", null))
                .thenThrow(new IllegalArgumentException("Out of range"));

        TopSecret app = new TopSecret(mockController);

        String output = captureOutput(() ->
                app.run(new String[]{"99"}));

        assertTrue(output.contains("Error"));
        assertTrue(output.contains("Out of range"));

        verify(mockController).getFileByNumber("99", null);
    }
}