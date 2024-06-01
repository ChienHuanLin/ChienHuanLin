package myproj;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.*;

import exceptions.InsufficientFundsException;
import models.*;
import services.*;

///need to import down three
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AllTests {

	static List<Account> accounts = new ArrayList<>();
	static AccountService accountService = new AccountServiceImpl(accounts);
	
	@Before
    public void setUp() {
        accounts.clear();
    }

    @After
    public void tearDown() {
        accounts.clear();
    }
	
	@Test
	///create the account successfully
	public void createAccount_Test_Success() {
		Account expectedResult = new Account(accounts.size(), AccountType.checkings, "Tiffany Lin", 90000);
        Account actualResult = accountService.createAccount(expectedResult);
        assertEquals(expectedResult, actualResult);
        assertEquals(accounts.size(), 1);
    }

	@Test(expected = IllegalArgumentException.class)
	///if the account create fail should use the expected
	public void createAccount_Test_Failure() {
		accountService.createAccount(null);
	}
	
	@Test
	///taking the money out successfully
	public void withdraw_Test_Success() throws InsufficientFundsException {
		Account accountToTest = new Account(accounts.size(), AccountType.savings, "OB", 110);
        accounts.add(accountToTest);

        ///for the calculation
        double initialBalance = accountToTest.getBalance();
        double amountToWithdraw = 100;
        double expectedResult = initialBalance - amountToWithdraw;

        double actualResult = accountService.withdraw(accountToTest.getAccountID(), amountToWithdraw);
        assertEquals(expectedResult, actualResult, 0.0);
	}
	
	@Test(expected = InsufficientFundsException.class)
	///if the amount isn't enough to take out than use expected rule
    public void withdraw_Test_Failure() throws InsufficientFundsException {
        Account accountToTest = new Account(accounts.size(), AccountType.savings, "OB", 50);
        accounts.add(accountToTest);
        accountService.withdraw(accountToTest.getAccountID(), 100);
    }

	@Test
	///delete the account successfully
    public void deleteAccount_Test_Success() {
        Account accountToDelete = new Account(accounts.size(), AccountType.checkings, "OB", 50000);
        accounts.add(accountToDelete);

        ///the account should be delete in the list
        Account deletedAccount = accountService.deleteAccount(accountToDelete.getAccountID());
        assertNotNull(deletedAccount);
        assertEquals(accountToDelete, deletedAccount);
        assertNull(accountService.getAccountByID(accountToDelete.getAccountID()));
    }

    @Test
    ///cannot delete the account that is null
    public void deleteAccount_Test_Failure() {
        Account deletedAccount = accountService.deleteAccount(-1);
        assertNull(deletedAccount);
    }

	@Test
	///update the information in the account successfully
    public void updateAccount_Test_Success() {
		///changing here
        Account accountToUpdate = new Account(accounts.size(), AccountType.checkings, "OB", 50000);
        accounts.add(accountToUpdate);

        ///saving new here
        Account updatedAccount = new Account(accountToUpdate.getAccountID(), AccountType.savings, "CB", 60000);
        accountService.updateAccount(updatedAccount);

        ///update here
        Account retrievedAccount = accountService.getAccountByID(updatedAccount.getAccountID());
        assertNotNull(retrievedAccount);
        assertEquals(updatedAccount, retrievedAccount);
    }

    @Test
    ///if the update isn't success in the account than return back to the null
    public void updateAccount_Test_Failure() {
        Account updatedAccount = new Account(-1, AccountType.savings, "CB", 60000);
        Account result = accountService.updateAccount(updatedAccount);
        assertNull(result);
    }

    @Test
    ///for testing after return there will show up the new account
    public void getAllAccounts_Test_Success() {
        Account account1 = new Account(accounts.size(), AccountType.checkings, "Tiffany", 1000);
        Account account2 = new Account(accounts.size() + 1, AccountType.savings, "Lin", 2000);
        accounts.add(account1);
        accounts.add(account2);
        ///the new account should be list down
        List<Account> allAccounts = accountService.getAllAccounts();
        assertEquals(2, allAccounts.size());
        assertEquals(account1, allAccounts.get(0));
        assertEquals(account2, allAccounts.get(1));
    }

    @Test
    ///return back to the account and it didn't show up anything 
    public void getAllAccounts_Test_Failure() {
        List<Account> allAccounts = accountService.getAllAccounts();
        assertEquals(0, allAccounts.size());
    }

    @Test
    ///test the account that was there already when it return
    public void getAccountByID_Test_Success() {
        Account accountToFind = new Account(accounts.size(), AccountType.checkings, "Charlie", 3000);
        accounts.add(accountToFind);

        ///the account need to be found when it return back
        Account foundAccount = accountService.getAccountByID(accountToFind.getAccountID());
        assertNotNull(foundAccount);
        assertEquals(accountToFind, foundAccount);
    }

    @Test
    ///it shouldn't return back to the account that is not there
    public void getAccountByID_Test_Failure() {
        Account foundAccount = accountService.getAccountByID(-1);
        assertNull(foundAccount);
    }

    @Test
    ///return back and to check the amount in the account
    public void getBalanceByAccountID_Test_Success() {
        Account account = new Account(accounts.size(), AccountType.checkings, "David", 4000);
        accounts.add(account);

        ///the account amount need to be count it again
        double balance = accountService.getBalanceByAccountID(account.getAccountID());
        assertEquals(4000, balance, 0.0);
    }

    @Test
    ///the account wasn't there and the amount should be zero
    public void getBalanceByAccountID_Test_Failure() {
        double balance = accountService.getBalanceByAccountID(-1);
        assertEquals(0, balance, 0.0);
    }
}