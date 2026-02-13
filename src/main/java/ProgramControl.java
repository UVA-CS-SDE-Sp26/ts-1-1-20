import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


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
        // get filenames
        List<String> files = fileHandler.listDataFiles();

        //  sort alphabetically (copy)
        List<String> sorted = new ArrayList<>(files);
        Collections.sort(sorted);

        //  format with two-digit numbers
        List<String> numbered = new ArrayList<>();
        for (int i = 0; i < sorted.size(); i++) {
            String number = String.format("%02d", i + 1);
            numbered.add(number + " " + sorted.get(i));
        }

        // return final list
        return numbered;
    }

    public String getFileByNumber(String fileNumber, String keyPathOrNull)
            throws IllegalArgumentException, IOException {

        // validate format: must be exactly two digits
        if (fileNumber == null || !fileNumber.matches("\\d{2}")) {
            throw new IllegalArgumentException("File number must be exactly two digits.");
        }

        //  convert to index (01 -> 0)
        int index = Integer.parseInt(fileNumber) - 1;

        // get + sort files
        List<String> files = fileHandler.listDataFiles();
        List<String> sorted = new java.util.ArrayList<>(files);
        java.util.Collections.sort(sorted);

        // validate range
        if (index < 0 || index >= sorted.size()) {
            throw new IllegalArgumentException("File number out of range.");
        }

        // pick filename and read raw cipher text
        String filename = sorted.get(index);
        //TODO
        // open to changing bc filehandler did not use the correct name

        String cipherText = fileHandler.readFile(filename);

        // pick key path
        String keyPath = (keyPathOrNull == null) ? "ciphers/key.txt" : keyPathOrNull;

        // decipher using CipherService (UNCOMMENT once CipherService matches contract)
        // CipherKey key = cipherService.loadKey(keyPath);
        // return cipherService.decipher(cipherText, key);

        // temporary
        return cipherText;
    }

}
