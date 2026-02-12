import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProgramControlTest {

    //create Mock of Filehandler

    @Mock
    FileHandler fileHandler;

    //create Mock of CipherService
    @Mock
    CipherService cipherService;

    //use actual programControl.java

    @InjectMocks
    ProgramControl programControl;
    //every test will be called like programControl.method name
}