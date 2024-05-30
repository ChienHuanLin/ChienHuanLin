package myproj;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.*;

import models.*;
import services.*;

public class AllTests {

	static List<Account> accounts = new ArrayList<>();
	static AccountService accountService = new AccountServiceImpl(accounts);
	
	///set up for account
	@Before
    public void setUp() {
        accounts.clear();
    }

	///tear down for account
    @After
    public void tearDown() {
        accounts.clear();
    }
	
	@Test
	public void createAccount_Test_Success() {
		//Account expectedResult = new Account(accounts.size(), AccountType.checkings, "John Doe", 90000);
		//Account actualResult = accountService.createAccount(expectedResult);
		//assertEquals(expectedResult, actualResult);
		//accounts.remove(expectedResult);
		
		
		Account expectedResult = new Account(accounts.size(), AccountType.checkings, "John Doe", 90000);
        Account actualResult = accountService.createAccount(expectedResult);
        ///over here to check after run createaccount can return back to the new account
        assertEquals(expectedResult, actualResult);
        ///to check the account had been add into the list successfully
        assertEquals(accounts.size(),1	);
        }
	
	@Test
	public void withdraw_Test_Success() {
		//Account accountToTest = new Account(accounts.size()+1, AccountType.savings, "Jack Ville", 110);
		//accounts.add(accounts.size(), accountToTest);
		
		//double initialBalance = accountToTest.getBalance();//110
		//double amountToWithdraw = 100;
		//double expectedResult = initialBalance - amountToWithdraw;//110-100=10
		
		//double actualResult = accountService.withdraw(accountToTest.getAccountID(), amountToWithdraw);//10==10
		//double delta = 0.0;
		//assertEquals(expectedResult, actualResult, 10.0);
		
		//accounts.remove(accountToTest);
		Account accountToTest = new Account(accounts.size() + 1, AccountType.savings, "Jack Ville", 110);
        accounts.add(accountToTest);

        double initialBalance = accountToTest.getBalance();// 110
        double amountToWithdraw = 100;
        double expectedResult = initialBalance - amountToWithdraw;// 110-100=10

        double actualResult = accountService.withdraw(accountToTest.getAccountID(), amountToWithdraw);// 10==10
        assertEquals(expectedResult, actualResult, 0.0);
	}
	@Test
    public void deleteAccount_Test_Success() {
        Account accountToDelete = new Account(accounts.size() + 1, AccountType.checkings, "Jane Doe", 50000);
        accounts.add(accountToDelete);

        Account deletedAccount = accountService.deleteAccount(accountToDelete.getAccountID());
        assertNotNull(deletedAccount);
        assertEquals(accountToDelete, deletedAccount);
        assertNull(accountService.getAccountByID(accountToDelete.getAccountID()));
    }

    private void assertNull(Account accountByID) {
		// TODO Auto-generated method stub
		
	}

	private void assertNotNull(Account deletedAccount) {
		// TODO Auto-generated method stub
		
	}

	@Test
    public void updateAccount_Test_Success() {
        Account accountToUpdate = new Account(accounts.size() + 1, AccountType.checkings, "Jane Doe", 50000);
        accounts.add(accountToUpdate);

        Account updatedAccount = new Account(accountToUpdate.getAccountID(), AccountType.savings, "Jane Smith", 60000);
        accountService.updateAccount(updatedAccount);

        Account retrievedAccount = accountService.getAccountByID(updatedAccount.getAccountID());
        assertNotNull(retrievedAccount);
        assertEquals(updatedAccount, retrievedAccount);
    }

	
}
