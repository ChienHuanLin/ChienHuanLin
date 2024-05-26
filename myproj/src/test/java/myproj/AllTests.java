package myproj;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.*;

import models.*;
import services.*;

public class AllTests {

	static List<Account> accounts = new ArrayList<>();
	static AccountService accountService = new AccountServiceImpl(accounts);
	
	
	@Test
	public void createAccount_Test_Success() {
		Account expectedResult = new Account(accounts.size(), AccountType.checkings, "John Doe", 90000);
		Account actualResult = accountService.createAccount(expectedResult);
		assertEquals(expectedResult, actualResult);
		accounts.remove(expectedResult);
	}
	
	@Test
	public void withdraw_Test_Success() {
		Account accountToTest = new Account(accounts.size()+1, AccountType.savings, "Jack Ville", 110);
		accounts.add(accounts.size(), accountToTest);
		
		double initialBalance = accountToTest.getBalance();//110
		double amountToWithdraw = 100;
		double expectedResult = initialBalance - amountToWithdraw;//110-100=10
		
		double actualResult = accountService.withdraw(accountToTest.getAccountID(), amountToWithdraw);//10==10
		double delta = 0.0;
		assertEquals(expectedResult, actualResult, 10.0);
		
		accounts.remove(accountToTest);
	}

	
}
