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

    // Helpful for unit tests (pass a temp directory)
    public FileHandler(Path dataDirectory) {
        this.dataDirectory = dataDirectory;
    }


    public List<String> listDataFiles() {
        if (!Files.exists(dataDirectory) || !Files.isDirectory(dataDirectory)) {
            throw new IllegalStateException("Missing data folder: " + dataDirectory.toAbsolutePath());
        }

        try (Stream<Path> stream = Files.list(dataDirectory)) {
            return stream
                    .filter(Files::isRegularFile)
                    .map(p -> p.getFileName().toString())
                    .sorted(Comparator.naturalOrder())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalStateException("Unable to list data files: " + e.getMessage(), e);
        }
    }


    public String readFile(String filename) throws IOException {
        if (filename == null || filename.trim().isEmpty()) {
            throw new IllegalArgumentException("Filename cannot be empty.");
        }

        Path filePath = dataDirectory.resolve(filename);

        if (!Files.exists(filePath) || !Files.isRegularFile(filePath)) {
            throw new IllegalArgumentException("Data file not found: " + filePath.toAbsolutePath());
        }

        return Files.readString(filePath, StandardCharsets.UTF_8);
    }


    public List<String> readKeyFile(String keyPath) throws IOException {
        if (keyPath == null || keyPath.trim().isEmpty()) {
            throw new IllegalArgumentException("Key path cannot be empty.");
        }

        Path keyFile = Paths.get(keyPath);

        if (!Files.exists(keyFile) || !Files.isRegularFile(keyFile)) {
            throw new IllegalArgumentException("Cipher key file not found: " + keyFile.toAbsolutePath());
        }

        return Files.readAllLines(keyFile, StandardCharsets.UTF_8);
    }


    public boolean keyExists(String keyPath) {
        if (keyPath == null || keyPath.trim().isEmpty()) return false;
        Path keyFile = Paths.get(keyPath);
        return Files.exists(keyFile) && Files.isRegularFile(keyFile);
    }
}
