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
        Assertions.assertEquals(3000F,RBI_Test.depositMoney(1000F,2000F));
        Assertions.assertEquals(8000F,RBI_Test.depositMoney(3000F,5000F));
    }

    @Test
    public void WithdrawMoney(){
        Assertions.assertEquals(2000F,RBI_Test.withdrawMoney(1000F,3000F,0,1000F,3));
        Assertions.assertEquals(500F,RBI_Test.withdrawMoney(1000F,500F,1,1000F,3));
        Assertions.assertEquals(4990F,RBI_Test.withdrawMoney(1000F,6000F,3,1000F,3));
    }

    @Test
    public void applyCreditTest(){
        Assertions.assertEquals("eligible",RBI_Test.applyCreditCard(1000F,400F));
        Assertions.assertEquals("not eligible",RBI_Test.applyCreditCard(1000F,1000F));
    }

    @Test
    public void appyLoanTest(){
        Assertions.assertEquals("Not eligible",RBI_Test.applyLoan(5F,10000F,3,1000F,10000F));
        Assertions.assertEquals("1576.25",RBI_Test.applyLoan(5F,10000F,3,1000F,400F));
    }

}
