package com.banking.mapper;

import com.banking.dto.AccountDto;
import com.banking.entity.Account;

public class AccountMapper {

	public static Account mapToAccount(AccountDto accountDto) {
		Account account = new Account(accountDto.getAccountHolderName(), accountDto.getBalance());
		return account;
	}

	public static AccountDto mapToAccountDto(Account account) {
		AccountDto accountDto = new AccountDto(account.getId(), account.getAccountHolderName(), account.getBalance());
		return accountDto;
	}
}
