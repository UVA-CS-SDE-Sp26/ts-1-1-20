import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileHandler {
    private final Path dataDirectory;


    public FileHandler() {
        this(Paths.get("data"));
    }


    public FileHandler(Path dataDirectory) {
        this.dataDirectory = dataDirectory;
    }


    public List<String> listDataFiles() {

        if (!Files.exists(dataDirectory) || !Files.isDirectory(dataDirectory)) {
            throw new IllegalStateException(
                    "Missing data folder: " + dataDirectory.toAbsolutePath());
        }

        try (Stream<Path> stream = Files.list(dataDirectory)) {

            return stream
                    .filter(Files::isRegularFile)
                    .map(path -> path.getFileName().toString())
                    .sorted(Comparator.naturalOrder())
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Unable to list data files: " + e.getMessage(), e);
        }
    }


    public String readDataFile(String filename) {

        if (filename == null || filename.trim().isEmpty()) {
            throw new IllegalArgumentException("Filename cannot be empty.");
        }

        Path filePath = dataDirectory.resolve(filename);

        if (!Files.exists(filePath) || !Files.isRegularFile(filePath)) {
            throw new IllegalStateException(
                    "Data file not found: " + filePath.toAbsolutePath());
        }

        try {
            return Files.readString(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException(
                    "Unable to read file: " + filename, e);
        }
    }


    public List<String> readKeyFile(String keyPath) {

        Path keyFile = Paths.get(keyPath);

        if (!Files.exists(keyFile) || !Files.isRegularFile(keyFile)) {
            throw new IllegalStateException(
                    "Cipher key file not found: " + keyFile.toAbsolutePath());
        }

        try {
            return Files.readAllLines(keyFile, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException(
                    "Unable to read cipher key file: " + keyPath, e);
        }
    }


    public boolean keyExists(String keyPath) {
        Path keyFile = Paths.get(keyPath);
        return Files.exists(keyFile) && Files.isRegularFile(keyFile);
    }
}
