import org.example.RBI_Test;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RBITest {

    private final ByteArrayOutputStream outContent;
    private final ByteArrayOutputStream errContent;
    private final PrintStream originalOut;
    private final PrintStream originalErr;

    public RBITest(){
        outContent = new ByteArrayOutputStream();
        errContent = new ByteArrayOutputStream();
        originalOut=System.out;
        originalErr=System.err;
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void depositeMoney(){
        float expectedBalance = 3000F, returnedBalance = RBI_Test.depositMoney(1000F,2000F);
        assertEquals(expectedBalance,returnedBalance);
    }

    @Test
    public void WithdrawMoney(){
        float expectedBalance= 2000F,returnedBalance= RBI_Test.withdrawMoney(1000F,3000F,0,1000F,3);
        assertEquals(expectedBalance,returnedBalance);
    }

}
