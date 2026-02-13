# TopSecret


## How to Run the Program
Note: This project is executed using Gradle as required by the assignment.
The equivalent command `java topsecret` shown in the narrative represents
the program conceptually, but execution is performed using:

.\gradlew run


All commands must be executed from the project root directory.

## Alternative Execution (Direct Java Command)

Although the project is designed to run using Gradle, the program can also be executed directly using the Java command line.

First compile the project using Gradle:
    .\gradlew classes

Then run the main class directly:
    java -cp build\classes\java\main TopSecret
Run with arguments:
    java -cp build\classes\java\main TopSecret 01

This mirrors the assignment narrative example (`java topsecret`) while still using the Gradle build output.

---
## Overview

TopSecret is a command-line Java application that allows agents to view mission files stored in the `/data` directory.
If the project includes a cipher module, file contents are deciphered using a key stored in `/ciphers/key.txt`.

The program follows a modular design where each team member is responsible for a specific component:

- TopSecret (CLI) – Handles user input and console output
- ProgramControl – Connects modules and controls program logic
- FileHandler – Reads files from the filesystem
- CipherService / CipherKey – Validates and deciphers ciphered text

The program is built and executed using Gradle.

---

## Folder Structure

/data        → mission files
/ciphers     → cipher keys
/docs        → design + documentation
/src/main/java → Java source code

---


## Testing Approach

The project was developed using a modular testing strategy aligned with the team roles.

### Manual Integration Verification

The following commands were executed to verify full program flow:



.\gradlew run
.\gradlew run --args="01"
.\gradlew run --args="01 ciphers/key.txt"
.\gradlew run --args="01 a b"
.\gradlew run --args="1"
.\gradlew run --args="aa"
.\gradlew run --args="01 ciphers/doesnotexist.txt"


These tests confirm:

- File listing works correctly
- Alphabetical numbering is applied
- Default cipher key is used when not provided
- Alternate cipher keys are accepted
- Invalid input exits gracefully
- Errors do not crash the program

---

## Unit Testing Strategy

Each module was tested independently:

ProgramControl
- Sorting and numbering logic
- File number validation
- Out-of-range detection
- Default vs alternate key handling

FileHandler
- File listing
- File reading
- Missing file detection

CipherService / CipherKey
- Key validation rules
- Decipher mapping behavior
- Character substitution correctness

---

## Program Flow

TopSecret (CLI)
↓
ProgramControl
↓
FileHandler → CipherService → CipherKey

- TopSecret is the only class that prints to the console.
- ProgramControl coordinates logic and returns results.
- FileHandler performs all filesystem access.
- CipherService handles cipher validation and deciphering.

---

## Error Handling

The program exits gracefully when:

- Invalid arguments are provided
- File numbers are incorrectly formatted
- Files are missing
- Cipher keys are missing or invalid

Errors are printed without crashing the application.

---

## Build Notes

- Gradle is used for compilation and testing.
- IntelliJ is used only as an editor; Gradle manages execution.
- JVM warnings during Gradle execution do not affect program behavior.

---

## Final Status

✔ Program runs from TopSecret class
✔ Cipher feature implemented
✔ Error handling verified
✔ Unit tests executed successfully
✔ Integration behavior verified via CLI commands
