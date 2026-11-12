import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BankAccountTest {

    BankAccount account;

    @BeforeMethod
    public void setUp() {
        account = new BankAccount(1000.0);
    }

    @Test
    public void balanceEnquiryTest() {
        double currentBalance = account.getBalance();
        System.out.println("Current Balance: " + currentBalance);
        Assert.assertEquals(currentBalance, 1000.0);
    }

    @Test
    public void depositTest() {
        account.deposit(500.0);
        double updatedBalance = account.getBalance();
        System.out.println("Balance after deposit: " + updatedBalance);
        Assert.assertEquals(updatedBalance, 1500.0);
    }

    @Test
    public void withdrawalTest() {
        account.withdraw(400.0);
        double updatedBalance = account.getBalance();
        System.out.println("Balance after withdrawal: " + updatedBalance);
        Assert.assertEquals(updatedBalance, 600.0);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void insufficientBalanceTest() {
        System.out.println("Attempting withdrawal exceeding balance...");
        account.withdraw(1500.0);
    }
}