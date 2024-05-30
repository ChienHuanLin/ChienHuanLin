package services;

import java.util.List;

import exceptions.InsufficientFundsException;
import models.Account;

public class AccountServiceImpl implements AccountService {

	// Currently all the methods are: TODO Auto-generated method stub

	// Steps
	// 1: Right Click -> myproj -> Maven -> Update Project
	// 2: From the top menu bar -> Run -> Run Configuration ->Type jUnit in the
	// search bar ->Select jUnit -> + New Configuration -> Run All Tests 
	// 3: Right Click -> myproj -> RunAs ->jUnit Test
	// 4: Watch the Tests Fail as all service methods below are mere stubs
	// 5: Make the changes in CreateAccount() to make the
	// createAccount_Test_Success() pass when tests are re-run
	// 6: Write other test cases for CreateAccount(): failureCases, edgeCases etc
	// and test it
	// 7: Write test cases(success, failure, edge cases) for all other methods
	// defined in this file, and make them pass by replacing stub code with actual
	// code here

	// Notes:
	// A: Write a separate test case to test separately each success/failure/edge
	// case being tested
	// B: make test methods names meaningful and relevant to what scenario the
	// test-method is testing
	// C: make sure to pass all Tests and ensure sufficient test coverage(All
	// methods in this class should have atleast 1 success and 1 failure test-case)
	// D: Use a wide variety of jjunit annotations, understand them and be ready to
	// explain them to your faculty

	private List<Account> accounts;

	public AccountServiceImpl(List<Account> accounts) {
		super();
		this.accounts = accounts;
	}

	@Override
	public List<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		//return null;
		
		///starts from here
		///return back for the account list
		///and use getallaccounts to return back to all
		return accounts;
	}

	@Override
	public Account getAccountByID(int id) {
		
		///starts from here
		///go to the right account by checking account id
		for (Account account : accounts) {
			if (account.getAccountID() == id) {
				///use getaccountid by check the id to return
				return account;
			}
		}
		return null;
	}

	@Override
	public double getBalanceByAccountID(int id) {
		// TODO Auto-generated method stub
		//return 0;
		
		///starts from here
		///to get the account balance by checking if the account have any money
		///if no than 0
		Account account = getAccountByID(id);
        return (account != null) ? account.getBalance() : 0;
	}

	@Override
	public Account createAccount(Account account) {
		//if (accounts.add(account)) {
			//return account;
		//}
		//return null;
		
		///starts from here
		///for the validation check
		if (account == null) {
			throw new IllegalArgumentException("account can't be empty");
		}
		///set account id for using the form for now
		account.setAccountID(accounts.size());
		///add the new account to the form
		accounts.add(account); 
		///return back to the new account
		return account; 
	}

	@Override
	public Account deleteAccount(int accountID) {
		// TODO Auto-generated method stub
		//return null;
		
		///starts from here
		//delete the account by checking the id
		for (Account account : accounts) {
			if (account.getAccountID() == accountID) {
				accounts.remove(account);
				///after all will return back to the account
				return account;
			}
		}
		return null;
	}

	@Override
	public Account updateAccount(Account account) {
		// TODO Auto-generated method stub
		//return null;
		
		
		///starts from here
		///update the account by checking the account id
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getAccountID() == account.getAccountID()) {
				accounts.set(i, account);
				///after all will return back to the account
				return account;
			}
		}
		return null;
	}

	public double withdraw(int accountID, double amount) {
		double updatedBalance = 0.0;

		if (amount > this.getBalanceByAccountID(accountID)) {
			this.getAccountByID(accountID).setBalance(this.getBalanceByAccountID(accountID) - amount);
			updatedBalance = this.getBalanceByAccountID(accountID);
		}else {
			try {
				throw new InsufficientFundsException();
			}catch (InsufficientFundsException ife) {
				ife.printStackTrace();
			}
		}
		 
		return updatedBalance;
		
}

}
