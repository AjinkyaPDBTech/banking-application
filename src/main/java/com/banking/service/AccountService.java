package com.banking.service;

import java.util.List;

import com.banking.dto.AccountDto;

public interface AccountService {

	AccountDto createAccount(AccountDto accountDto);
	
	AccountDto getAccountById(Long id);
	
	List<AccountDto> getAllAccounts();
	
	AccountDto deposit(Long id,double amount);
	
	AccountDto withdraw(Long id,double amount);
	
	void deleteAccountById(Long id);
}
