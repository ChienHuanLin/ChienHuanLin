package services;

import java.util.List;

import exceptions.InsufficientFundsException;
import models.Account;

public class AccountServiceImpl implements AccountService {

	private List<Account> accounts;

	public AccountServiceImpl(List<Account> accounts) {
		super();
		this.accounts = accounts;
	}

	@Override
	public List<Account> getAllAccounts() {
		return accounts;
	}

	@Override
	public Account getAccountByID(int id) {
		for (Account account : accounts) {
			if (account.getAccountID() == id) {
				return account;
			}
		}
		return null;
	}

	@Override
	public double getBalanceByAccountID(int id) {
		Account account = getAccountByID(id);
        return (account != null) ? account.getBalance() : 0;
	}

	@Override
	public Account createAccount(Account account) {
		if (account == null) {
			throw new IllegalArgumentException("account can't be empty");
		}
		account.setAccountID(accounts.size());
		accounts.add(account); 
		return account; 
	}

	@Override
	public Account deleteAccount(int accountID) {
		for (Account account : accounts) {
			if (account.getAccountID() == accountID) {
				accounts.remove(account);
				return account;
			}
		}
		return null;
	}

	@Override
	public Account updateAccount(Account account) {
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getAccountID() == account.getAccountID()) {
				accounts.set(i, account);
				return account;
			}
		}
		return null;
	}

	
	///need to make some changes in public InsufficientFundsException() to add string string in the ()
	@Override
	public double withdraw(int accountID, double amount) throws InsufficientFundsException {
		double updatedBalance = 0.0;

		Account account = getAccountByID(accountID);
		if (account != null) {
			if (amount <= account.getBalance()) {
				account.setBalance(account.getBalance() - amount);
				updatedBalance = account.getBalance();
			} else {
				throw new InsufficientFundsException("Insufficient funds for account ID " + accountID);
			}
		}
		return updatedBalance;
	}
}