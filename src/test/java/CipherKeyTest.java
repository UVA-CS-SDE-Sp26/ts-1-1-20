import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CipherKeyTest {

    @Test
    void inverseMappingWorks_smallKey() {
        // from (plain) -> to (cipher)
        // abc123  ->  bc123a
        // decipher is inverse: b->a, c->b, 1->c, 2->1, 3->2, a->3
        CipherKey key = new CipherKey("abc123", "bc123a");

        assertEquals('a', key.decipherChar('b'));
        assertEquals('b', key.decipherChar('c'));
        assertEquals('c', key.decipherChar('1'));
        assertEquals('1', key.decipherChar('2'));
        assertEquals('2', key.decipherChar('3'));
        assertEquals('3', key.decipherChar('a'));
    }

    @Test
    void unknownCharacterStaysSame() {
        CipherKey key = new CipherKey("abc", "bca");

        assertEquals(' ', key.decipherChar(' '));
        assertEquals('!', key.decipherChar('!'));
        assertEquals('\n', key.decipherChar('\n'));
    }
}
