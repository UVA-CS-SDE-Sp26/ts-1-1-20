TopSecret documentation starter file

## TopSecret - Homework 2 (Team API Contracts)

This project is a command-line utility:
- `java topsecret` lists available data files with numbers (01, 02, ...)
- `java topsecret NN [optionalKeyPath]` displays the contents of the selected file

## Folder Structure
- `/data` contains mission text files
- `/ciphers` contains cipher key files (default: `ciphers/key.txt`)
- `/docs` contains design + test documentation
- `/src/main/java` contains Java source (Gradle project)

## How it Runs- This is a command-line program
   acceptable commands:
       *java topsecret
       * java topsecret 01
       * java topsecret 01 mykey.txt
   What each command does:
       * 'java topsecret' returns
            01 filea.txt
            02 fileb.txt
            03 filec.txt
       * 'java topsecret 01' returns
            prints 01.filea.txt using the default decipher key
       * 'java topsecret 01 mykey.txt'
            prints first file with a specific key
## roles

    Team Member A- Command Line Interface
        * Creates:
            src/main/java/TopSecret.java - THIS IS THE MAIN CLASS WE RUN
        * responsibilies:
            public static void main(String[] args)
    Team Member B- File Access
        * creates:
            src/main/java/FileHandler.java- YOU ONLY READ THE FILES IN /data
        *required methods for API(Open to changing but must reinsert here)
            List<String> listDataFiles();
            String readFile(String filename) throws IOException;
        * Responsbilites:
            return file names from /data
            return raw file contents
    Team Member C- Program control
        *creates
            src/main/java/ProgramControl.java- YOU ONlY RETURN WHAT B and D GIVE YOU
        * required methods:
            List<String> getNumberedFileList();

            String getFileByNumber(String fileNumber, String keyPathOrNull)
                throws IllegalArgumentException, IOException;
        * responsbilies
            call FileHandler
            call CipherService
            return Final Readable text

    Team Member D- Cipher
        *creates:
            src/main/java/CipherService.java
            src/main/java/CipherKey.java
        *requird methods
            CipherKey loadKey(String keyPath) throws IOException, IllegalArgumentException;
            String decipher(String cipherText, CipherKey key);
        *responsbilites
            load key file
            validate key
            convert cipher text to readable text



## Numbering Rule (must match across code and tests)

- File list is sorted alphabetically before numbering.
- "01" → index 0
- "02" → index 1
- "03" → index 2
- Always two digits.

ProgramControl performs sorting and numbering.

---

## Default Cipher Key

- If no second argument is provided, ProgramControl uses:
  ciphers/key.txt

- If a second argument is provided, that path is used instead.

---

## Output Responsibility

- ProgramControl NEVER prints to console.
- ProgramControl only returns Strings or Lists.
- TopSecret is the ONLY class that prints output and exits.

---

## Error Behavior (graceful exit required)

Program must print an error and exit cleanly for:

- invalid argument count
- invalid file number format (not two digits)
- file number out of range
- missing or unreadable data file
- missing or invalid cipher key
- deciphering failure

No crashes.

---

## Program Flow (simple)

TopSecret.main(args)
→ ProgramControl called
→ ProgramControl calls FileHandler
→ ProgramControl calls CipherService
→ ProgramControl returns results
→ TopSecret prints results and exits

---

## Testing Expectations

Each team member writes unit tests for their own module.

ProgramControl tests will use stub or mock FileHandler and CipherService.

Minimum ProgramControl tests:

- listing works
- valid selection works
- invalid format fails
- out of range fails
- alternate key is used
- file read failure handled

---

## Team Progress Checklist (update as work is completed)

### Team Member A – TopSecret (CLI) (Ridhi)
- [X] TopSecret.java created
- [X] main method implemented
- [X] Argument parsing complete
- [X] Printing output implemented
- [ ] Unit tests written (if applicable)

### Team Member B – FileHandler
- [ ] FileHandler.java created
- [ ] listDataFiles() implemented
- [ ] readFile() implemented
- [ ] Handles missing files gracefully
- [ ] Unit tests written

### Team Member C – ProgramControl (Ansa)
- [X] ProgramControl.java created
- [ X] getFileByNumber() implemented
- [X ] getFileContentsByNumber() implemented
- [X ] Default key logic added
- [ X] Error handling added
- [ X] Unit tests written

### Team Member D – Cipher
- [x] CipherService.java created
- [x] CipherKey.java created
- [ ] loadKey() implemented
- [ ] decipher() implemented
- [ ] Key validation implemented
- [ ] Unit tests written

## FINAL CHECK LIST
-[ ] ensure all tests work
-[ ] ensure topSecret runs