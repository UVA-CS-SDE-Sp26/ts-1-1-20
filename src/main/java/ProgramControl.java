import java.io.IOException;
import java.util.List;

public class ProgramControl {

    // storing references to different classes to allow calls
    private final FileHandler fileHandler;
    private final CipherService cipherService;

    // creates ProgramControl objects
    public ProgramControl(FileHandler fileHandler, CipherService cipherService) {
        this.fileHandler = fileHandler;
        this.cipherService = cipherService;
    }

    // Method that asks FileHandler for filenames and then sorts and formats
    public List<String> getNumberedFileList() {
        // TODO
        // should return strings like:
        // "01 filea.txt"
        // "02 fileb.txt"
        return null;
    }

    public String getFileByNumber(String fileNumber, String keyPathOrNull)
            throws IllegalArgumentException, IOException {
        // TODO
        return null;
    }
}
