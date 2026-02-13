# TopSecret

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

## How to Run the Program

All commands must be executed from the project root directory.

### List Available Files
